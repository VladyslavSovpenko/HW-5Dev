package org.example.handlers;

import org.example.service.HttpActions;
import org.example.service.Supplier;

import java.util.Scanner;

public class StoreHandler extends AbstractHandler {

    private Scanner scanner;
    private Supplier supplier;
    private HttpActions httpActions;

    public StoreHandler(Scanner scanner) {
        this.scanner = scanner;
        this.supplier = Supplier.getInstance();
        this.httpActions = HttpActions.getInstance();
    }


    @Override
    protected String getTemplateName() {
        return "store";
    }
}
