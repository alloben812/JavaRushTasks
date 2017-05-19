package com.javarush.task.task27.task2712.kitchen;

import java.util.Arrays;

/**
 * Created by NazarenkoDS on 19.05.2017.
 */
public enum Dish {
    Fish, Steak, Soup, Juice, Water;
    public static String allDishesToString(){
        StringBuilder sb = new StringBuilder(Arrays.toString(values()));
        sb.delete(sb.length() - 1, sb.length());
        sb.delete(0, 1);
        return sb.toString();
    }
}
