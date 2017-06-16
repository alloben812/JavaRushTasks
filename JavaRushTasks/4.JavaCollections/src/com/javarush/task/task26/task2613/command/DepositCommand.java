package com.javarush.task.task26.task2613.command;

import com.javarush.task.task26.task2613.ConsoleHelper;
import com.javarush.task.task26.task2613.CurrencyManipulatorFactory;
import com.javarush.task.task26.task2613.exception.InterruptOperationException;

/**
 * Created by NazarenkoDS on 09.06.2017.
 */
class DepositCommand implements Command {
    @Override
    public void execute() throws InterruptOperationException {
        ConsoleHelper.writeMessage("start");
        String currencyCode = ConsoleHelper.askCurrencyCode();
        String[] array = ConsoleHelper.getValidTwoDigits(currencyCode);
        CurrencyManipulatorFactory.getManipulatorByCurrencyCode(currencyCode).addAmount(Integer.parseInt(array[0]),Integer.parseInt(array[1]));
        ConsoleHelper.writeMessage(String.valueOf(CurrencyManipulatorFactory.getManipulatorByCurrencyCode(currencyCode).getTotalAmount()));
    }
}
