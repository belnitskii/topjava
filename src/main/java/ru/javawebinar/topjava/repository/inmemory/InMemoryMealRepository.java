package ru.javawebinar.topjava.repository.inmemory;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.repository.MealRepository;
import ru.javawebinar.topjava.util.DateTimeUtil;
import ru.javawebinar.topjava.util.MealsUtil;

import java.time.LocalDate;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Predicate;
import java.util.stream.Collectors;

@Repository
public class InMemoryMealRepository implements MealRepository {
    private static final Logger log = LoggerFactory.getLogger(InMemoryMealRepository.class);

    private final Map<Integer, Map<Integer, Meal>> userMealsMap = new ConcurrentHashMap<>();
    private final AtomicInteger counter = new AtomicInteger(0);

    {
        MealsUtil.meals.forEach(meal -> save(meal, 1));
        MealsUtil.adminMeals.forEach(meal -> save(meal, 2));
    }

    @Override
    public Meal save(Meal meal, int userId) {
        Map<Integer, Meal> meals = userMealsMap.computeIfAbsent(userId, mealsMap -> new ConcurrentHashMap<>());
        if (meal.isNew()) {
            meal.setId(counter.incrementAndGet());
            meal.setUserId(userId);
            log.info("save {}", meal);
            meals.put(meal.getId(), meal);
            return meal;
        }
        log.info("update {}", meal);
        return meals.computeIfPresent(meal.getId(), (k, v) -> v) == meal ? meal : null;
    }

    @Override
    public boolean delete(int id, int userId) {
        Map<Integer, Meal> meals = userMealsMap.get(userId);
        if (meals == null) {
            return false;
        }
        Meal meal = meals.get(id);
        log.info("delete {}", id);
        return meal != null && meals.remove(id, meal);
    }

    @Override
    public Meal get(int id, int userId) {
        Map<Integer, Meal> meals = userMealsMap.get(userId);
        if (meals == null) {
            return null;
        }
        log.info("get {}", id);
        return meals.get(id);
    }

    @Override
    public List<Meal> getAll(int userId) {
        log.info("getAll for user {}", userId);
        return filterByPredicate(userMealsMap.get(userId), meal -> true);
    }

    @Override
    public List<Meal> getAllFilteredByDate(int userId, LocalDate startDate, LocalDate endDate) {
        log.info("getAllFiltered for user {}, start date: {}, end date: {} ", userId, startDate, endDate);
        return filterByPredicate(userMealsMap.get(userId), meal -> DateTimeUtil.isBetweenClosedInterval(meal.getDate(), startDate, endDate));
    }

    private List<Meal> filterByPredicate(Map<Integer, Meal> meals, Predicate<Meal> filter) {
        if (meals == null) {
            return Collections.emptyList();
        }
        return meals.values().stream().filter(filter).
                sorted(Comparator.comparing(Meal::getDateTime).reversed()).collect(Collectors.toList());
    }
}

