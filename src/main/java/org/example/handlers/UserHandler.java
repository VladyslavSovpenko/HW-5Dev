package org.example.handlers;

import org.example.service.HttpActions;
import org.example.service.Supplier;

import java.util.Scanner;


public class UserHandler extends AbstractHandler {

    private Scanner scanner;
    private Supplier supplier;
    private HttpActions httpActions;

    public UserHandler(Scanner scanner) {
        this.scanner = scanner;
        this.supplier = Supplier.getInstance();
        this.httpActions = HttpActions.getInstance();
    }


    @Override
    protected String getTemplateName() {
        return "user";
    }
}
