package org.example;

import java.util.List;
import java.util.Scanner;

public class SecondaryInput {

    public static final List<String> methods = List.of("get", "put", "delete", "post");

    public static void run(){

    }
    String input2 = getSelect(scanner, "Available action:  get, put, post, delete");

    input2 = secondMenu(scanner, input2);

        handlers.get(input1).handler(input2);
}

    private static String secondMenu(Scanner scanner, String input2) {
        if (!methods.contains(input2)) {
//            repeatSecondMenu(scanner, input2);
            run();
        }
        return input2;
    }

    private static String repeatSecondMenu(Scanner scanner, String input2) {
        System.out.println("Invalid input. Please try again");
        input2 = getSelect(scanner, "Available action:  get, put, post, delete");
        return input2;
    }
}
