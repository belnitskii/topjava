package ru.javawebinar.topjava.storage;


import ru.javawebinar.topjava.model.Meal;

import java.util.List;

public interface MealRepository {

    Meal save(Meal meal);

    Meal get(int id);

    Meal delete(int id);

    List<Meal> getAll();
}
