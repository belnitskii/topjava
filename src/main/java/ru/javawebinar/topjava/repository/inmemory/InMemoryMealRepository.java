package ru.javawebinar.topjava.repository.inmemory;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.repository.MealRepository;
import ru.javawebinar.topjava.util.MealsUtil;
import ru.javawebinar.topjava.web.SecurityUtil;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

@Repository
public class InMemoryMealRepository implements MealRepository {
    private static final Logger log = LoggerFactory.getLogger(InMemoryMealRepository.class);
    private final Map<Integer, Meal> repository = new ConcurrentHashMap<>();
    private final AtomicInteger counter = new AtomicInteger(0);

    {
        for (Meal meal : MealsUtil.meals) {
            save(meal, SecurityUtil.authUserId());
        }
    }

    @Override
    public Meal save(Meal meal, int userId) {
        log.info("save {}", meal);
        if (meal.isNew()) {
            meal.setId(counter.incrementAndGet());
            repository.put(meal.getId(), meal);
            return meal;
        }
        return meal.getUserId() == userId ? repository.computeIfPresent(meal.getId(), (id, oldMeal) -> meal) : null;
    }

//    @Override
//    public Meal save(Meal meal, int userId) {
//        log.info("save {}", meal);
//        if (repository.containsKey(meal.getId()) && repository.get(meal.getId()).getUserId() == userId){
//            return repository.put(meal.getId(), meal);
//        }
//        if (meal.isNew()) {
//            meal.setId(counter.incrementAndGet());
//            if (repository.containsKey(meal.getId())) {
//                return null;
//            }
//            repository.put(meal.getId(), meal);
//            return meal;
//        }
//
//        return null;
//    }

    @Override
    public boolean delete(int id, int userId) {
        log.info("delete {}", id);
        if (repository.containsKey(id)) {
            return repository.get(id).getUserId() == userId && repository.remove(id) != null;
        }
        return false;
    }

    @Override
    public Meal get(int id, int userId) {
        log.info("get {}", id);
        if (repository.containsKey(id)) {
            return repository.get(id).getUserId() == userId ? repository.get(id) : null;
        }
        return null;
    }

    @Override
    public List<Meal> getAll(int userId) {
        log.info("getAll");
        return repository.values().stream()
                .filter(meal -> meal.getUserId() == userId)
                .sorted(Comparator.comparing(Meal::getDate).reversed())
                .collect(Collectors.toList());
    }
}

