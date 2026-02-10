package ru.javawebinar.topjava.web;

import org.slf4j.Logger;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.repository.InMemoryMealRepository;
import ru.javawebinar.topjava.repository.MealRepository;
import ru.javawebinar.topjava.util.MealsUtil;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;

import static org.slf4j.LoggerFactory.getLogger;

public class MealServlet extends HttpServlet {
    private static final Logger log = getLogger(MealServlet.class);

    private static final String MEAL_SERVLET = "meals";
    private static final String INSERT_OR_EDIT = "/meal.jsp";
    private static final String LIST_MEALS = "/meals.jsp";

    private MealRepository mealRepository;

    @Override
    public void init() {
        mealRepository = new InMemoryMealRepository();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        if (action == null) {
            action = "default";
        }
        switch (action) {
            case "delete":
                int deleteId = Integer.parseInt(req.getParameter("id"));
                mealRepository.delete(deleteId);
                log.debug("Try to delete meal with id:{}, redirect to {}", deleteId, MEAL_SERVLET);
                resp.sendRedirect(MEAL_SERVLET);
                break;
            case "edit":
                int editId = Integer.parseInt(req.getParameter("id"));
                req.setAttribute("meal", mealRepository.get(editId));
                log.debug("Try to edit meal with id:{}, redirect to {}", editId, INSERT_OR_EDIT);
                req.getRequestDispatcher(INSERT_OR_EDIT).forward(req, resp);
                break;
            case "insert":
                log.debug("Try to add new meal, redirect to {}", INSERT_OR_EDIT);
                req.getRequestDispatcher(INSERT_OR_EDIT).forward(req, resp);
                break;
            default:
                req.setAttribute("meals", MealsUtil.filteredByStreams(mealRepository.getAll(), MealsUtil.CALORIES_PER_DAY));
                log.debug("Redirect to {}", LIST_MEALS);
                req.getRequestDispatcher(LIST_MEALS).forward(req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        String idParam = req.getParameter("id");
        LocalDateTime localDateTime = LocalDateTime.parse((req.getParameter("datetime")));
        String description = String.valueOf(req.getParameter("description"));
        int calories = Integer.parseInt(req.getParameter("calories"));
        if (idParam == null || idParam.isEmpty()) {
            log.debug("Try to save new meal");
            mealRepository.save(new Meal(localDateTime, description, calories));
        } else {
            int id = Integer.parseInt(idParam);
            log.debug("Try to update meal {}", id);
            mealRepository.save(new Meal(id, localDateTime, description, calories));
        }
        log.debug("Meal was saved or updated, redirect to {}", MEAL_SERVLET);
        resp.sendRedirect(MEAL_SERVLET);
    }
}
