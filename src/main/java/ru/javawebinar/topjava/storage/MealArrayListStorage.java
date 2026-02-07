package ru.javawebinar.topjava.storage;

import ru.javawebinar.topjava.exception.ExistStorageException;
import ru.javawebinar.topjava.exception.NotExistStorageException;
import ru.javawebinar.topjava.model.Meal;

import java.util.ArrayList;
import java.util.List;

public class MealArrayListStorage implements MealStorage {
    protected final List<Meal> storage = new ArrayList<>();

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
        return new ArrayList<>(storage);
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