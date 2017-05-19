package com.javarush.task.task27.task2712.kitchen;

import com.javarush.task.task27.task2712.ConsoleHelper;
import com.javarush.task.task27.task2712.Tablet;

import java.io.IOException;
import java.util.List;

/**
 * Created by NazarenkoDS on 19.05.2017.
 */
public class Order {
    private List<Dish> dishes;
    private final Tablet tablet;

    public Order(Tablet tablet) throws IOException {
        this.tablet = tablet;
        this.dishes = ConsoleHelper.getAllDishesForOrder();
    }

    @Override
    public String toString() {
        if(dishes.isEmpty()||dishes.size()==0)return "";
        else
        return "Your order: "+ dishes +
                " of " + tablet;
    }
}
