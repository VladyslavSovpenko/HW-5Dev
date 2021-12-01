package org.example.service;

import org.example.MainInput;

public class Supplier {

    private static Supplier instance;

    public Supplier() {
    }

    public static Supplier getInstance() {
        if (instance == null) {
            instance = new Supplier();
        }
        return instance;
    }

    public void errorMsg(String message) {
        System.err.println(message);
    }

    public void continueQuestion(String contAnswer) {

        if (contAnswer.equalsIgnoreCase("yes")) {
            MainInput.run();
        } else {
            System.out.println("Bye");
        }
    }

}

