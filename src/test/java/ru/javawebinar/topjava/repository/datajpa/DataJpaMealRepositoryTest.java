package ru.javawebinar.topjava.repository.datajpa;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import ru.javawebinar.topjava.Profiles;
import ru.javawebinar.topjava.UserTestData;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.service.MealService;
import ru.javawebinar.topjava.service.MealServiceTest;

import static ru.javawebinar.topjava.MealTestData.*;
import static ru.javawebinar.topjava.UserTestData.ADMIN_ID;
import static ru.javawebinar.topjava.UserTestData.USER_MATCHER;

@ActiveProfiles(profiles = Profiles.DATAJPA)
public class DataJpaMealRepositoryTest extends MealServiceTest {
    @Autowired
    private MealService service;

    @Test
    public void getWithUser() {
       Meal actual = service.getWithUser(ADMIN_MEAL_ID, ADMIN_ID);
       MEAL_MATCHER.assertMatch(actual, adminMeal1);
       USER_MATCHER.assertMatch(actual.getUser(), UserTestData.admin);
    }
}