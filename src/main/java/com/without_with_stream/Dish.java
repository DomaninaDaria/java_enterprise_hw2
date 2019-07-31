package com.without_with_stream;

import java.util.Comparator;

public class Dish {
    private final String name;
    private final Integer calories;
    private final Boolean isBIO;
    private final DishType type;

    public String getName() {
        return name;
    }


    public Integer getCalories() {
        return calories;
    }


    public Boolean getBIO() {
        return isBIO;
    }

    public DishType getType() {
        return type;
    }

    public Dish(String name, Integer calories, Boolean isBIO, DishType type) {

        this.name = name;
        this.calories = calories;
        this.isBIO = isBIO;
        this.type = type;
    }

    @Override
    public String toString() {
        return "Dish{" +
                "name='" + name + '\'' +
                ", calories=" + calories +
                ", isBIO=" + isBIO +
                ", type=" + type +
                '}';
    }
}

class DishComparatorCalories implements Comparator<Dish> {
    public int compare(Dish o1, Dish o2) {
        return -(o1.getCalories() - o2.getCalories());
    }
}

class DishComparatorType implements Comparator<Dish> {

    @Override
    public int compare(Dish o1, Dish o2) {
        return o1.getType().name().compareTo(o2.getType().name());
    }
}

class DishComparatorName implements Comparator<Dish> {

    @Override
    public int compare(Dish o1, Dish o2) {
        return o1.getName().compareTo(o2.getName());
    }
}
