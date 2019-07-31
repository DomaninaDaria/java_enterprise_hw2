package com.without_with_stream;

import java.util.*;


public class MainWithoutStream {
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

        System.out.println(lowCaloriesDishes(menu).toString());//low calorie
//
//        System.out.println(top3Nutrient(menu).toString());//3 most nutritious
//
//        menu.sort(new DishComparatorType().thenComparing(new DishComparatorName()));//sort by type, name
//        for (Dish dish : menu) {
//            System.out.println(dish.getName());
//        }
//
//        HashMap<DishType, Double> dishTypeDoubleHashMap = averageCalories((menu));//food type and average calorie
//        System.out.println(dishTypeDoubleHashMap.toString());
//
//        System.out.println(groupByTypeAndDishes(menu).toString());//grouping by type and dishes
//
//        System.out.println(groupByTypeAndBIODishes(menu).toString());//grouping by type and name of bio dishes
    }


    public static List<String> lowCaloriesDishes(List<Dish> dishes) {
        ArrayList<String> lowCaloriesDishes = new ArrayList<String>();
        for (Dish dish : dishes) {
            if (dish.getCalories() <= 350)
                lowCaloriesDishes.add(dish.getName());
        }
        return lowCaloriesDishes;
    }


    public static List<String> top3Nutrient(List<Dish> dishes) {
        ArrayList<String> top3Nutrient = new ArrayList<String>();

        dishes.sort(new DishComparatorCalories());
        top3Nutrient.addAll(Arrays.asList(dishes.get(0).getName(), dishes.get(1).getName(), dishes.get(2).getName()));
        for (int i = 3; i < dishes.size(); i++) {
            if (dishes.get(2).getCalories().equals(dishes.get(i).getCalories())) {
                top3Nutrient.add(dishes.get(i).getName());
            }
        }
        return top3Nutrient;
    }


    public static HashMap<DishType, Double> averageCalories(List<Dish> dishes) {
        HashMap<DishType, Double> dishTypeDoubleHashMap = new HashMap<DishType, Double>();
        DishType[] values = DishType.values();
        for (int i = 0; i < values.length; i++) {
            int counter = 0;
            double calories = 0;
            for (Dish dish : dishes) {
                if (values[i].equals(dish.getType())) {
                    counter++;
                    calories += dish.getCalories();
                }
            }
            dishTypeDoubleHashMap.put(values[i], Math.round(calories / counter * 100d) / 100d);
        }
        return dishTypeDoubleHashMap;
    }

    public static Map<DishType, List<Dish>> groupByTypeAndDishes(List<Dish> dishes) {
        List<Dish> dishesByDishType;
        Map<DishType, List<Dish>> dishTypeDieshes = new HashMap<DishType, List<Dish>>();
        DishType[] values = DishType.values();
        for (int i = 0; i < values.length; i++) {
            dishesByDishType = new ArrayList<Dish>();
            for (Dish dish : dishes) {
                if (values[i].name().equals(dish.getType().name())) {
                    dishesByDishType.add(dish);
                }
            }
            dishTypeDieshes.put(values[i], dishesByDishType);
        }

        return dishTypeDieshes;
    }

    public static Map<DishType, List<String>> groupByTypeAndBIODishes(List<Dish> dishes) {
        Map<DishType, List<String>> dishTypeNameDish = new HashMap<DishType, List<String>>();
        List<String> nameOfDishes;
        ListIterator<Dish> iterator = dishes.listIterator();
        while (iterator.hasNext()) {
            int i = iterator.nextIndex();
            if (!iterator.next().getBIO()) {
                iterator.remove();
            }
        }
        DishType[] values = DishType.values();
        for (int i = 0; i < values.length; i++) {
            nameOfDishes = new ArrayList<String>();
            for (Dish dish : dishes) {
                if (values[i].name().equals(dish.getType().name())) {
                    nameOfDishes.add(dish.getName());
                }
            }
            dishTypeNameDish.put(values[i], nameOfDishes);
        }
        return dishTypeNameDish;
    }
}