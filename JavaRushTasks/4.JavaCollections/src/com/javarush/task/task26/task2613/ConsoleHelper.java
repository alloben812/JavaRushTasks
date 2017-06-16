package com.javarush.task.task26.task2613;

import com.javarush.task.task26.task2613.exception.InterruptOperationException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created by NazarenkoDS on 09.06.2017.
 */
public class ConsoleHelper {
    private static BufferedReader bis = new BufferedReader(new InputStreamReader(System.in));

    public static String readString() throws InterruptOperationException{
        String message = "";
        try
        {
            message = bis.readLine();
            if (message.equalsIgnoreCase("EXIT"))
                throw new InterruptOperationException();
        }
        catch (IOException ignored)
        {
        }

        return message;
    }
    public static void writeMessage(String message){
        System.out.println(message);
    }

    public static String askCurrencyCode() throws InterruptOperationException{
        String s;
        while (true){
            writeMessage("choose.currency.code");
            s = readString();
            if (s.length() != 3) {
                writeMessage("invalid.data");
            }
            else {
                s = s.toUpperCase();
                break;

            }

        }
        return s;
    }

    public static String[] getValidTwoDigits(String currencyCode) throws InterruptOperationException {
        String[] array;
        writeMessage("Insert denomination and count :");
        while(true){
            String s = readString();
            array = s.split(" ");
            int k=0,l=0;
            try {
                k=Integer.parseInt(array[0]);
                l=Integer.parseInt(array[1]);
            }
            catch (Exception e){
                writeMessage("Invalid data, try again:");
                continue;
            }

                if (k <= 0 || l <= 0 || array.length > 2)
                {
                    writeMessage("invalid.data");
                    continue;
                }
             break;
        }
        return array;
    }

    public static Operation askOperation() throws InterruptOperationException{
        while(true){
            String line = readString();
            try {
                if (Integer.parseInt(line) > 0 && Integer.parseInt(line) < 5)
                    return Operation.getAllowableOperationByOrdinal(Integer.parseInt(line));
                else
                    writeMessage("invalid data, try again:");
            } catch (NumberFormatException e) {
                writeMessage("invalid data, try again:");
            }
        }
    }
}

