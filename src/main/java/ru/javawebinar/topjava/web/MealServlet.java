package ru.javawebinar.topjava.web;

import org.slf4j.Logger;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.model.MealTo;
import ru.javawebinar.topjava.storage.MealArrayListStorage;
import ru.javawebinar.topjava.util.MealsUtil;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;

import static org.slf4j.LoggerFactory.getLogger;

public class MealServlet extends HttpServlet {
    private static final Logger log = getLogger(MealServlet.class);

    private static final MealArrayListStorage mealArrayListStorage = new MealArrayListStorage();
    private static final String INSERT_OR_EDIT = "/meal.jsp";
    private static final String LIST_MEALS = "/meals.jsp";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String forward = "";
        String action = req.getParameter("action");

        if (action.equalsIgnoreCase("meals")) {
            forward = LIST_MEALS;
            log.debug("redirect to {}", forward);
            List<MealTo> meals = MealsUtil.filteredByStreams(mealArrayListStorage.getAll(), MealsUtil.CALORIES_PER_DAY);
            req.setAttribute("meals", meals);
            req.getRequestDispatcher(forward).forward(req, resp);
        }
        if (action.equalsIgnoreCase("delete")) {
            forward = LIST_MEALS;
            String id = req.getParameter("id");
            log.debug("try to delete meal {}", id);
            mealArrayListStorage.delete(Integer.parseInt(id));
            List<MealTo> meals = MealsUtil.filteredByStreams(mealArrayListStorage.getAll(), MealsUtil.CALORIES_PER_DAY);
            req.setAttribute("meals", meals);
            log.debug("redirect to {}", forward);
            req.getRequestDispatcher(forward).forward(req, resp);
        }
        if (action.equalsIgnoreCase("edit")) {
            forward = INSERT_OR_EDIT;
            int id = Integer.parseInt(req.getParameter("id"));
            log.debug("try to edit meal {}", id);
            req.setAttribute("meal", MealsUtil.filteredByStreamsAndId(mealArrayListStorage.getAll(), MealsUtil.CALORIES_PER_DAY, id));
            log.debug("redirect to {}", forward);
            req.getRequestDispatcher(forward).forward(req, resp);
        }
        if (action.equalsIgnoreCase("insert")) {
            log.debug("try to add new meal");
            forward = INSERT_OR_EDIT;
            log.debug("redirect to {}", forward);
            req.getRequestDispatcher(forward).forward(req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String forward = LIST_MEALS;
        req.setCharacterEncoding("UTF-8");
        String idParam = req.getParameter("id");
        LocalDateTime localDateTime = LocalDateTime.parse((req.getParameter("datetime")));
        String description = String.valueOf(req.getParameter("description"));
        int calories = Integer.parseInt(req.getParameter("calories"));
        Meal meal;
        if (idParam == null || idParam.isEmpty()) {
            log.debug("try to save new meal");
            meal = new Meal(localDateTime, description, calories);
            mealArrayListStorage.save(meal);
        } else {
            int id = Integer.parseInt(idParam);
            meal = new Meal(id, localDateTime, description, calories);
            log.debug("try to update meal {}", id);
            mealArrayListStorage.update(meal);
        }
        RequestDispatcher view = req.getRequestDispatcher(forward);
        List<MealTo> meals = MealsUtil.filteredByStreams(mealArrayListStorage.getAll(), MealsUtil.CALORIES_PER_DAY);
        req.setAttribute("meals", meals);
        log.debug("redirect to {}", forward);
        view.forward(req, resp);
    }
}
