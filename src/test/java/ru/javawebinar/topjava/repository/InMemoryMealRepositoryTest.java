package ru.javawebinar.topjava.repository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import ru.javawebinar.topjava.model.Meal;

import java.time.LocalDateTime;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class InMemoryMealRepositoryTest {
    private Meal meal_1;
    private Meal meal_2;
    private Meal meal_3;

    protected InMemoryMealRepository storage = new InMemoryMealRepository();

    private static Meal copyOf(Meal meal) {
        return new Meal(meal.getId(), meal.getDateTime(), meal.getDescription(), meal.getCalories());
    }

    @BeforeEach
    void setUp() {
        storage.clear();
        meal_1 = storage.save(new Meal(LocalDateTime.now(), "Breakfast", 800));
        meal_2 = storage.save(new Meal(LocalDateTime.now(), "Lunch", 1600));
        meal_3 = storage.save(new Meal(LocalDateTime.now(), "Dinner", 800));
    }

    @Test
    void getAll() {
        assertTrue(storage.getAll().containsAll(
                Stream.of(meal_1, meal_2, meal_3)
                        .map(InMemoryMealRepositoryTest::copyOf).collect(Collectors.toList())));
    }

    @Test
    void save() {
        Meal meal = storage.save(new Meal(LocalDateTime.now(), "New Meal", 500));
        assertEquals(4, storage.size());
        assertEquals(copyOf(meal), storage.get(meal.getId()));
    }

    @Test
    void update() {
        Meal updated = new Meal(meal_1.getId(), LocalDateTime.MIN, "Updated", 1000);
        storage.save(updated);
        Meal actual = storage.get(updated.getId());
        assertEquals(3, storage.size());
        assertEquals(LocalDateTime.MIN, actual.getDateTime());
        assertEquals("Updated", actual.getDescription());
        assertEquals(1000, actual.getCalories());
        assertEquals(updated.getId(), actual.getId());
    }

    @Nested
    class Get {
        @Test
        void get() {
            assertEquals(copyOf(meal_1), storage.get(meal_1.getId()));
        }

        @Test
        void getNotExist() {
            assertNull(storage.get(Integer.MAX_VALUE));
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
            assertFalse(storage.delete(Integer.MAX_VALUE));
        }
    }
}