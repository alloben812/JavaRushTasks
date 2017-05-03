package com.javarush.task.task29.task2909.car;

/**
 * Created by NazarenkoDS on 03.05.2017.
 */
public class Truck extends Car {
    private final int MAX_TRUCK_SPEED = 80;
    public Truck(int numberOfPassengers){
        super(TRUCK,numberOfPassengers);
    }

    @Override
    public int getMaxSpeed() {
        return MAX_TRUCK_SPEED;
    }
}
