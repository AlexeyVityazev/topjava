package ru.javawebinar.topjava.service;

import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.repository.MealRepository;
import ru.javawebinar.topjava.repository.inmemory.InMemoryMealRepository;

public class MealService {
    //@Autowired
    private MealRepository repository = InMemoryMealRepository.getRepository();

    public void save(Meal meal) {
        repository.save(meal);
    }

}