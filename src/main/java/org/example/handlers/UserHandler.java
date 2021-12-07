package org.example.handlers;

import org.example.service.HttpActions;
import org.example.service.Supplier;

import java.util.Scanner;


public class UserHandler extends AbstractHandler {

    private Scanner scanner;

    public UserHandler(Scanner scanner) {
        this.scanner = scanner;
    }


    @Override
    protected String getTemplateName() {
        return "user";
    }
}
