package ru.javawebinar.topjava.web.meal;

import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.model.User;
import ru.javawebinar.topjava.service.MealService;
import ru.javawebinar.topjava.to.MealTo;

public class MealRestController {
    //@Autowired
    private MealService service = new MealService();

    public void doPost(MealTo mealTo, User user) {
        Meal meal = new Meal(mealTo.getId(),
                mealTo.getDateTime(),
                mealTo.getDescription(),
                mealTo.getCalories(),
                user);
        service.save(meal);
    }

}