package com.javarush.task.task26.task2613.command;

import com.javarush.task.task26.task2613.ConsoleHelper;
import com.javarush.task.task26.task2613.CurrencyManipulator;
import com.javarush.task.task26.task2613.CurrencyManipulatorFactory;
import com.javarush.task.task26.task2613.exception.InterruptOperationException;

import java.util.Collection;
import java.util.Iterator;

/**
 * Created by NazarenkoDS on 09.06.2017.
 */
class InfoCommand implements Command {
    @Override
    public void execute() throws InterruptOperationException {

        Collection map = CurrencyManipulatorFactory.getAllCurrencyManipulators();
        if (map.size()==0 || map.isEmpty())
            ConsoleHelper.writeMessage("No money available.");
        else {
            int count = 0;
            for (Iterator<CurrencyManipulator> iterator = map.iterator(); iterator.hasNext(); )
            {
                CurrencyManipulator pair =  iterator.next();
                if (pair.hasMoney())
                {
                    count++;
                    ConsoleHelper.writeMessage( pair.getCurrencyCode()+ " - " + pair.getTotalAmount());
                }
            }
            if (count == 0)
                ConsoleHelper.writeMessage("No money available.");
        }
    }
}
