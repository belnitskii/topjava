package ru.javawebinar.topjava;

import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.model.Role;
import ru.javawebinar.topjava.model.User;

import java.time.LocalDateTime;
import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;
import static ru.javawebinar.topjava.model.AbstractBaseEntity.START_SEQ;

public class MealTestData {
    public static final int USER_ID = START_SEQ;
    public static final int ADMIN_ID = START_SEQ + 1;
    public static final int GUEST_ID = START_SEQ + 2;
    public static final int MEAL_1_ID = START_SEQ + 3;
    public static final int MEAL_2_ID = START_SEQ + 4;
    public static final int MEAL_3_ID = START_SEQ + 5;
    public static final int MEAL_4_ID = START_SEQ + 6;
    public static final int MEAL_5_ID = START_SEQ + 7;
    public static final int NOT_FOUND = 10;

    public static final User user = new User(USER_ID, "User", "user@yandex.ru", "password", Role.USER);
    public static final User admin = new User(ADMIN_ID, "Admin", "admin@gmail.com", "admin", Role.ADMIN);
    public static final User guest = new User(GUEST_ID, "Guest", "guest@gmail.com", "guest");

    public static final Meal meal_1 = new Meal(MEAL_1_ID, LocalDateTime.of(2026, 2, 21, 8, 0, 0), "breakfast", 600);
    public static final Meal meal_2 = new Meal(MEAL_2_ID, LocalDateTime.of(2026, 2, 22, 12, 0, 0), "lunch", 1200);
    public static final Meal meal_3 = new Meal(MEAL_3_ID, LocalDateTime.of(2026, 2, 23, 18, 20, 35), "dinner", 900);
    public static final Meal meal_4 = new Meal(MEAL_4_ID, LocalDateTime.of(2026, 2, 23, 12, 0, 0), "coffee", 140);
    public static final Meal meal_5 = new Meal(MEAL_5_ID, LocalDateTime.of(2026, 2, 23, 12, 15, 17), "gyros", 1000);

    public static Meal getNew() {
        return new Meal(null, LocalDateTime.of(2000, 02, 20, 12, 0, 0), "New", 123);
    }

    public static Meal getUpdated() {
        Meal updated = new Meal(meal_1);
        updated.setDateTime(LocalDateTime.of(2011, 1, 1, 1, 1, 1));
        updated.setDescription("Updated");
        updated.setCalories(111);
        return updated;
    }


    public static void assertMatch(Meal actual, Meal expected) {
        assertThat(actual).usingRecursiveComparison().isEqualTo(expected);
    }

    public static void assertMatch(Iterable<Meal> actual, Meal... expected) {
        assertMatch(actual, Arrays.asList(expected));
    }

    public static void assertMatch(Iterable<Meal> actual, Iterable<Meal> expected) {
        assertThat(actual).usingRecursiveFieldByFieldElementComparator().isEqualTo(expected);
    }
}
