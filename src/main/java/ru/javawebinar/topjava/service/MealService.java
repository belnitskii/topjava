package ru.javawebinar.topjava.service;

import org.springframework.stereotype.Service;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.repository.MealRepository;

import java.util.ArrayList;
import java.util.List;

import static ru.javawebinar.topjava.util.ValidationUtil.checkNotFound;

@Service
public class MealService {

    private MealRepository repository;

    public MealService(MealRepository repository) {
        this.repository = repository;
    }

    public Meal create(Meal meal, int userId) {
        return repository.save(meal, userId);
    }

    public void delete(int id, int userId) {
        checkNotFound(repository.delete(id, userId), userId);
    }

    public Meal get(int id, int userId) {
        return checkNotFound(repository.get(id, userId), userId);
    }

    public List<Meal> getAll(int userId) {
        return new ArrayList<>(repository.getAll());
    }

    public void update(Meal meal, int userId) {
        checkNotFound(repository.save(meal, userId), userId);
    }
}