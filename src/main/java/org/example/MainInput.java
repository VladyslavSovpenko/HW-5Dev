package org.example;

import org.example.handlers.AbstractHandler;
import org.example.handlers.PetHandler;
import org.example.handlers.StoreHandler;
import org.example.handlers.UserHandler;

import java.util.*;


public class MainInput {


    public static final Map<String, AbstractHandler> handlers = new HashMap<>();


    public static void run() {
        Scanner scanner = new Scanner(System.in);
        handlers.put("pet", new PetHandler());
        handlers.put("store", new StoreHandler(scanner));
        handlers.put("user", new UserHandler(scanner));

        String input1 = getSelect(scanner, "Select pet/store/user");

        if (!handlers.containsKey(input1)) {
            System.out.println("Invalid input. Please try again");
            input1 = getSelect(scanner, "Select pet/store/user");
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

    private static String getSelect(Scanner scanner, String text) {
        System.out.println(text);
        return scanner.nextLine().toLowerCase().trim();
    }


}
