package ru.javawebinar.topjava.repository.inmemory;

import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.repository.MealRepository;
import ru.javawebinar.topjava.util.MealsUtil;

import java.util.Collection;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

public class InMemoryMealRepository implements MealRepository {
    private final static MealRepository mealRepository = new InMemoryMealRepository();
    private final Map<Integer, Meal> repository = new ConcurrentHashMap<>();
    private final AtomicInteger counter = new AtomicInteger(0);

    {
        MealsUtil.meals.forEach(this::save);
    }

    private InMemoryMealRepository() {
    }

    public static MealRepository getRepository() {
        return mealRepository;
    }

    @Override
    public Meal save(Meal meal) {
        if (meal.isNew()) {
            meal.setId(counter.incrementAndGet());
            repository.put(meal.getId(), meal);
            return meal;
        }
        // handle case: update, but not present in storage
        return repository.computeIfPresent(meal.getId(), (id, oldMeal) -> meal);
    }

    @Override
    public boolean delete(int id) {
        return repository.remove(id) != null;
    }

    @Override
    public Meal get(int id, Integer userId) {

        return Optional.ofNullable(get(id))
                .filter(meal -> meal.getUser().getId().equals(userId))
                .orElse(null);
    }

    private Meal get(int id) {
        return repository.get(id);
    }


    @Override
    public Collection<Meal> getAll() {
        return repository.values();
    }

    @Override
    public Collection<Meal> getByUserId(Integer userId) {
        return repository.values().stream().filter(meal -> meal.getUser() != null)
                .filter(meal -> meal.getUser().getId()
                        .equals(userId)).collect(Collectors.toList());
    }
}

