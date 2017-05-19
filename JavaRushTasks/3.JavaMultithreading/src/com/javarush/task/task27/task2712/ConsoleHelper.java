package com.javarush.task.task27.task2712;

import com.javarush.task.task27.task2712.kitchen.Dish;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by NazarenkoDS on 19.05.2017.
 */
public class ConsoleHelper {
    private static final BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

    public static void writeMessage(String message) {
        System.out.println(message);
    }

    public static String readString() throws IOException {
        return reader.readLine();
    }

    public static List<Dish> getAllDishesForOrder() throws IOException {
        List<Dish> list = new ArrayList<>();
        if (Dish.values().length > 0) {
            writeMessage("Available dishes: " + Dish.allDishesToString());
            writeMessage("Enter a dish or type 'exit':");
            String dish = null;
            while (!"exit".equalsIgnoreCase(dish = readString())) {
                try {
                    list.add(Dish.valueOf(dish));
                } catch (IllegalArgumentException e) {
                    writeMessage("There is no such a dish");
                }
            }
        }
        return list;
    }
}
