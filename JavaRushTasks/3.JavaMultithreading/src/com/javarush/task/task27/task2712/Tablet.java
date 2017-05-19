package com.javarush.task.task27.task2712;

import com.javarush.task.task27.task2712.kitchen.Order;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by NazarenkoDS on 19.05.2017.
 */
public class Tablet {
    final int number;
    public Tablet(int number) {
        this.number = number;
    }


    static Logger logger = Logger.getLogger(Tablet.class.getName());

    public void createOrder(){
        try {
            Order order = new Order(this);
            ConsoleHelper.writeMessage(order.toString());
        } catch (IOException e) {
            logger.log(Level.SEVERE, "Console is unavailable");
        }
    }

    @Override
    public String toString() {
        return "Tablet{" +
                "number=" + number +
                '}';
    }
}
