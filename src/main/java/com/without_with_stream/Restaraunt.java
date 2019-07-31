package com.without_with_stream;

import java.util.List;

public class Restaraunt {
   private  List<Dish> menu;

    public Restaraunt(List<Dish> menu) {
        this.menu = menu;
    }

    public List<Dish> getMenu() {
        return menu;
    }

    public void setMenu(List<Dish> menu) {
        this.menu = menu;
    }

    public void addDish(List<Dish> menu, Dish dish){
        menu.add(dish);
    }
}