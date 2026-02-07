package ru.javawebinar.topjava.storage;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import ru.javawebinar.topjava.exception.ExistStorageException;
import ru.javawebinar.topjava.exception.NotExistStorageException;
import ru.javawebinar.topjava.model.Meal;

import java.time.LocalDateTime;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class MealArrayListStorageTest {
    private static final Meal meal_1 = new Meal(LocalDateTime.now(), "Some description", 100);
    private static final Meal meal_2 = new Meal(LocalDateTime.now(), "Some description", 200);
    private static final Meal meal_3 = new Meal(LocalDateTime.now(), "Some description", 300);
    private static final Meal meal_4 = new Meal(LocalDateTime.now(), " ", 10000);
    protected MealStorage storage = new MealArrayListStorage();

    @BeforeEach
    void setUp() {
        storage.clear();
        storage.save(meal_1);
        storage.save(meal_2);
        storage.save(meal_3);
    }

    @Test
    void clear() {
        storage.clear();
        assertEquals(0, storage.size());
    }

    @Test
    void getAll() {
        assertTrue(storage.getAll().containsAll(Arrays.asList(meal_1, meal_2, meal_3))
                && storage.size() == 3);
    }

    @Test
    void size() {
        assertEquals(3, storage.size());
    }

    @Nested
    class Save {
        @Test
        void save() {
            storage.clear();
            storage.save(meal_1);
            assertEquals(1, storage.size());
            assertEquals(meal_1, storage.get(meal_1.getId()));
        }

        @Test
        void saveExist() {
            assertThrows(ExistStorageException.class, () -> {
                storage.save(meal_1);
            });
        }
    }

    @Nested
    class Update {
        @Test
        void update() {
            Meal meal = storage.get(meal_1.getId());
            Meal updatedMeal = new Meal(meal.getId(), LocalDateTime.now(), "Some another food", 10000);
            storage.update(updatedMeal);
            assertNotEquals(meal, storage.get(meal_1.getId()));
        }

        @Test
        void updateNotExist() {
            assertThrows(NotExistStorageException.class, () -> {
                storage.update(meal_4);
            });
        }
    }

    @Nested
    class Get {
        @Test
        void get() {
            assertEquals(meal_1, storage.get(meal_1.getId()));
        }

        @Test
        void getNotExist() {
            assertThrows(NotExistStorageException.class, () -> {
                storage.get(meal_4.getId());
            });
        }
    }

    @Nested
    class Delete {
        @Test
        void delete() {
            storage.delete(meal_1.getId());
            assertEquals(2, storage.size());
            assertFalse(storage.getAll().stream().anyMatch(m -> m.equals(meal_1)));
        }

        @Test
        void deleteNotExist() {
            assertThrows(NotExistStorageException.class, () -> {
                storage.delete(meal_4.getId());
            });
        }
    }
}