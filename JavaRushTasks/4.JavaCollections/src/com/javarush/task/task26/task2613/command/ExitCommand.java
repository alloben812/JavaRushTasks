package com.javarush.task.task26.task2613.command;

import com.javarush.task.task26.task2613.ConsoleHelper;
import com.javarush.task.task26.task2613.exception.InterruptOperationException;

/**
 * Created by NazarenkoDS on 09.06.2017.
 */
class ExitCommand implements Command {
    @Override
    public void execute() throws InterruptOperationException{
        ConsoleHelper.writeMessage("exit.question.y.n");
        if (ConsoleHelper.readString().equalsIgnoreCase("y"))
            ConsoleHelper.writeMessage("thank.message");
    }
}
