package ru.javawebinar.topjava;

import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.model.Role;
import ru.javawebinar.topjava.model.User;
import ru.javawebinar.topjava.web.SecurityUtil;
import ru.javawebinar.topjava.web.meal.MealRestController;
import ru.javawebinar.topjava.web.user.AdminRestController;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Arrays;

public class SpringMain {
    public static void main(String[] args) {
        // java 7 automatic resource management (ARM)
        try (ConfigurableApplicationContext appCtx = new ClassPathXmlApplicationContext("spring/spring-app.xml")) {
            System.out.println("Bean definition names: " + Arrays.toString(appCtx.getBeanDefinitionNames()));
            MealRestController mealController = appCtx.getBean(MealRestController.class);
            mealController.create(new Meal(LocalDateTime.now(), "dinner", SecurityUtil.authUserCaloriesPerDay()));
//            System.out.println(mealController.getAll());
            System.out.println("test");

            mealController.create(new Meal(LocalDateTime.now(), "dinner", SecurityUtil.authUserCaloriesPerDay()));

            System.out.println("kek");
            System.out.println(mealController.getAllFiltered(LocalDate.of(2020, 1, 30), LocalTime.of(9, 0), LocalDate.of(2020, 1, 30), LocalTime.of(11, 0)));
        }

        try (ConfigurableApplicationContext appCtx = new ClassPathXmlApplicationContext("spring/spring-app.xml")) {
            System.out.println("Bean definition names: " + Arrays.toString(appCtx.getBeanDefinitionNames()));
            AdminRestController adminUserController = appCtx.getBean(AdminRestController.class);
            adminUserController.create(new User(null, "userName", "email@mail.ru", "password", Role.ADMIN));
        }
    }
}
