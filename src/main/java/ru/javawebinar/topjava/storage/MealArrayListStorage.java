package ru.javawebinar.topjava.storage;

import ru.javawebinar.topjava.exception.ExistStorageException;
import ru.javawebinar.topjava.exception.NotExistStorageException;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.util.MealsUtil;

import java.util.Comparator;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.stream.Collectors;

public class MealArrayListStorage implements MealStorage {
    public static final List<Meal> storage = new CopyOnWriteArrayList<>(MealsUtil.meals);

    @Override
    public void clear() {
        storage.clear();
    }

    @Override
    public void save(Meal meal) {
        int index = getNotExistedSearchKey(meal.getId());
        storage.add(meal);
    }

    @Override
    public void update(Meal meal) {
        int index = getExistedSearchKey(meal.getId());
        storage.set(index, meal);
    }

    @Override
    public Meal get(int id) {
        int index = getExistedSearchKey(id);
        return storage.get(index);
    }

    @Override
    public void delete(int id) {
        int index = getExistedSearchKey(id);
        storage.remove(index);
    }

    @Override
    public List<Meal> getAll() {
        return storage.stream().sorted(Comparator.comparing(Meal::getDateTime)).collect(Collectors.toList());
    }

    @Override
    public int size() {
        return storage.size();
    }

    private boolean isExist(int index) {
        return index >= 0;
    }

    private int getExistedSearchKey(int mealId) {
        int searchKey = getSearchKey(mealId);
        if (!isExist(searchKey)) {
            throw new NotExistStorageException(String.valueOf(mealId));
        }
        return searchKey;
    }

    private int getNotExistedSearchKey(int mealId) {
        int searchKey = getSearchKey(mealId);
        if (isExist(searchKey)) {
            throw new ExistStorageException(String.valueOf(mealId));
        }
        return searchKey;
    }

    private int getSearchKey(int index) {
        for (int i = 0; i < storage.size(); i++) {
            if (storage.get(i).getId().equals(index)) {
                return i;
            }
        }
        return -1;
    }
}