package ru.javawebinar.topjava.repository.datajpa;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import ru.javawebinar.topjava.Profiles;
import ru.javawebinar.topjava.UserTestData;
import ru.javawebinar.topjava.model.User;
import ru.javawebinar.topjava.service.UserService;
import ru.javawebinar.topjava.service.UserServiceTest;
import ru.javawebinar.topjava.util.exception.NotFoundException;

import java.util.Collections;

import static org.junit.Assert.assertThrows;
import static ru.javawebinar.topjava.UserTestData.*;

@ActiveProfiles(profiles = Profiles.DATAJPA)
public class DataJpaUserRepositoryTest extends UserServiceTest {
    @Autowired
    private UserService service;

    @Test
    public void getWithMeals() {
        User user = service.getWithMeals(USER_ID);
        USER_MATCHER_WITH_MEALS.assertMatch(user, UserTestData.getUserWithMeals());
    }

    @Test
    public void getWithOutMeals() {
        User user = service.getWithMeals(GUEST_ID);
        User guestCopy = new User(guest);
        guestCopy.setMeals(Collections.emptyList());
        USER_MATCHER_WITH_MEALS.assertMatch(user, guestCopy);
    }

    @Test
    public void getNotFound() {
        assertThrows(NotFoundException.class, () -> service.getWithMeals(NOT_FOUND));
    }
}