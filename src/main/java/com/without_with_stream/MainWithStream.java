package com.without_with_stream;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class MainWithStream {
    public static void main(String[] args) {
        List<Dish> dishes = new ArrayList<Dish>();
        dishes.add(new Dish("Vegetable Salad", 150, false, DishType.VEGETABLES));
        dishes.add(new Dish("Steak", 350, true, DishType.BEEF));
        dishes.add(new Dish("Meat Dumplings", 650, true, DishType.BEEF));
        dishes.add(new Dish("Chicken soup ", 400, true, DishType.CHICKEN));
        dishes.add(new Dish("Meat Skewers", 400, true, DishType.BEEF));
        dishes.add(new Dish("Chicken wings", 400, false, DishType.CHICKEN));
        dishes.add(new Dish("Tomato soup", 250, false, DishType.VEGETABLES));
        Restaraunt restaraunt = new Restaraunt(dishes);
        List<Dish> menu = restaraunt.getMenu();

        //low calorie
        menu.stream().filter(c -> c.getCalories() <= 350).map(c -> c.getName()).forEach(c -> System.out.println(c));

        //3 most nutritious
        menu.stream().sorted(new DishComparatorCalories()).limit(3).forEach(s -> System.out.println(s.getName()));


        //sort by type, name
        menu.stream().sorted(new DishComparatorType().thenComparing(new DishComparatorName())).
                forEach(s -> System.out.println(s.getName()));


        // food type and average calorie
        Map<DishType, Double> dishTypeDoubleMap = menu.stream().collect(
                Collectors.groupingBy(Dish::getType, Collectors.averagingDouble(Dish::getCalories)));
        System.out.println(dishTypeDoubleMap.toString());


        //grouping by type and dish
        Map<DishType, List<Dish>> dishTypeDieshes = menu.stream().collect(
                Collectors.groupingBy(Dish::getType));
        System.out.println(dishTypeDieshes.toString());

        //grouping by type and name of bio dishes
        Map<DishType, List<Dish>> dishTypeNameDish = menu.stream().filter(s -> s.getBIO().equals(true)).
                collect(Collectors.groupingBy(Dish::getType));
        System.out.println(dishTypeNameDish.toString());
    }
}
