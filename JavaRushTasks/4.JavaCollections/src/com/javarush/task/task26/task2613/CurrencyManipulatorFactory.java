package com.javarush.task.task26.task2613;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by NazarenkoDS on 09.06.2017.
 */
public class CurrencyManipulatorFactory {
    private static Map<String, CurrencyManipulator> map = new HashMap<>();


    public static CurrencyManipulator getManipulatorByCurrencyCode(String currencyCode)
    {
        CurrencyManipulator current;
        if (map.containsKey(currencyCode.toUpperCase()))
            return map.get(currencyCode.toUpperCase());
        else {
            current = new CurrencyManipulator(currencyCode);
            map.put(currencyCode.toUpperCase(), current);
            return current;
        }
    }

    private CurrencyManipulatorFactory()
    {
    }

    public static Collection<CurrencyManipulator> getAllCurrencyManipulators()
    {
        return map.values();
    }
}
