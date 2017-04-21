package com.javarush.task.task04.task0412;

/* 
Положительное и отрицательное число
*/

import java.io.*;

public class Solution {
    public static void main(String[] args) throws Exception {
        //напишите тут ваш код
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        int num = Integer.parseInt(reader.readLine());

        if(num >0)
            {num = num*2;System.out.println(num);}
                else if(num <0)
                {System.out.println(++num);}
                    else if(num==0)System.out.println(0);
    }

}