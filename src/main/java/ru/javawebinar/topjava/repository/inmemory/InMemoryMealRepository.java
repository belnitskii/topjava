package ru.javawebinar.topjava.repository.inmemory;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.repository.MealRepository;
import ru.javawebinar.topjava.util.MealsUtil;
import ru.javawebinar.topjava.web.SecurityUtil;

import java.util.Collection;
import java.util.Comparator;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

@Repository
public class InMemoryMealRepository implements MealRepository {
    private static final Logger log = LoggerFactory.getLogger(InMemoryMealRepository.class);

    private final Map<Integer, Meal> mealsMap = new ConcurrentHashMap<>();
    private final AtomicInteger counter = new AtomicInteger(0);

    {
        MealsUtil.meals.forEach(meal -> save(meal, SecurityUtil.authUserId()));
    }

    @Override
    public Meal save(Meal meal, int userId) {
        log.info("save {}", meal);
        if (meal.isNew()) {
            meal.setId(counter.incrementAndGet());
            meal.setUserId(userId);
            mealsMap.putIfAbsent(meal.getId(), meal);
            return meal;
        }
        log.info("update {}", meal);
        return mealsMap.computeIfPresent(meal.getId(), (k, v) -> {
            if (v.getUserId() == userId) {
                return meal;
            }
            return v;
        }) == meal ? meal : null;
    }

    @Override
    public boolean delete(int id, int userId) {
        log.info("delete {}", id);
        Meal meal = mealsMap.get(id);
        return meal != null && meal.getUserId() == userId && mealsMap.remove(id, meal);
    }

    @Override
    public Meal get(int id, int userId) {
        log.info("get {}", id);
        Meal meal = mealsMap.get(id);
        return (meal != null && meal.getUserId() == userId) ? meal : null;
    }

    @Override
    public Collection<Meal> getAll() {
        log.info("getAll");
        return mealsMap.values().stream().sorted(Comparator.comparing(Meal::getDateTime).reversed()).collect(Collectors.toList());
    }
}

