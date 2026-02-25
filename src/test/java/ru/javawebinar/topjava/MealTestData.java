package ru.javawebinar.topjava;

import ru.javawebinar.topjava.model.Meal;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;
import static ru.javawebinar.topjava.model.AbstractBaseEntity.START_SEQ;

public class MealTestData {
    public static final int USER_MEAL_1_ID = START_SEQ + 3;
    public static final int USER_MEAL_2_ID = START_SEQ + 4;
    public static final int USER_MEAL_3_ID = START_SEQ + 5;
    public static final int USER_MEAL_START_DAY_ID = START_SEQ + 6;
    public static final int USER_MEAL_4_ID = START_SEQ + 7;
    public static final int USER_MEAL_5_ID = START_SEQ + 8;
    public static final int USER_MEAL_6_ID = START_SEQ + 9;
    public static final int ADMIN_MEAL_1_ID = START_SEQ + 10;
    public static final int ADMIN_MEAL_2_ID = START_SEQ + 11;
    public static final LocalDate FIRST_DAY = LocalDate.of(2026, 2, 21);
    public static final LocalDate SECOND_DAY = LocalDate.of(2026, 2, 22);

    public static final Meal user_meal_1 = new Meal(USER_MEAL_1_ID, FIRST_DAY.atTime(8, 0, 0), "breakfast", 500);
    public static final Meal user_meal_2 = new Meal(USER_MEAL_2_ID, FIRST_DAY.atTime(12, 0, 0), "lunch", 1000);
    public static final Meal user_meal_3 = new Meal(USER_MEAL_3_ID, FIRST_DAY.atTime(18, 0, 0), "dinner", 500);
    public static final Meal user_meal_by_start_day =
            new Meal(USER_MEAL_START_DAY_ID, SECOND_DAY.atStartOfDay(), "meal on borderline cases", 10);
    public static final Meal user_meal_4 = new Meal(USER_MEAL_4_ID, SECOND_DAY.atTime(10, 0, 0), "breakfast", 490);
    public static final Meal user_meal_5 = new Meal(USER_MEAL_5_ID, SECOND_DAY.atTime(13, 0, 0), "lunch", 1000);
    public static final Meal user_meal_6 = new Meal(USER_MEAL_6_ID, SECOND_DAY.atTime(22, 0, 0), "dinner", 500);
    public static final Meal admin_meal_1 = new Meal(ADMIN_MEAL_1_ID, FIRST_DAY.atTime(8, 0, 0), "breakfast", 500);
    public static final Meal admin_meal_2 = new Meal(ADMIN_MEAL_2_ID, SECOND_DAY.atTime(12, 0, 0), "lunch", 1000);

    public static Meal getNew() {
        return new Meal(null, SECOND_DAY.plusDays(1).atTime(12, 0, 0), "New", 123);
    }

    public static Meal getUpdatedUserMeal1() {
        Meal updated = new Meal(user_meal_1);
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
