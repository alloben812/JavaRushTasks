package com.javarush.task.task26.task2613;

import com.javarush.task.task26.task2613.command.CommandExecutor;
import com.javarush.task.task26.task2613.exception.InterruptOperationException;

import java.util.Locale;

/**
 * Created by NazarenkoDS on 09.06.2017.
 */
public class CashMachine {
    public static void main(String[] args) {
        Locale.setDefault(Locale.ENGLISH);
        try {
            Operation operation;
            do
            {
                ConsoleHelper.writeMessage("\n" + "choose.operation" + " \n" +
                        "operation.INFO" + ": 1;\n" +
                        "operation.DEPOSIT" + ": 2;\n" +
                        "operation.WITHDRAW" + ": 3;\n" +
                        "operation.EXIT" + ": 4");
                operation = ConsoleHelper.askOperation();
                CommandExecutor.execute(operation);
            }
            while (operation != Operation.EXIT);
        } catch (InterruptOperationException e) {
              ConsoleHelper.writeMessage("Пока");
        }
    }
}
