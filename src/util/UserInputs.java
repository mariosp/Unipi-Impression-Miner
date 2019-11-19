package util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.InputMismatchException;
import java.util.Scanner;

public class UserInputs {

    public int onInputNumber() {
        // Declare the variables
        int number = 0;
        try {
            // Create Scanner object
            Scanner s = new Scanner(System.in);

            // Read the next integer from the screen
            number = s.nextInt();

        } catch (InputMismatchException e) {
            // accept integer only.
            System.out.println("--- Invalid Input. Please Input a number ---");
            number = onInputNumber();
        }

        if (number == 0 ) {
            System.out.println("--- Please Input a number bigger than 0 ---");
            number = onInputNumber();
        }
        return number;
    }


    public String onInputString() {
        String line= "";
        try{
            // reading user input line by line
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            line = reader.readLine();

        }catch(NumberFormatException nfe){
            System.err.println("Invalid Format!");
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return line;
    }
}
