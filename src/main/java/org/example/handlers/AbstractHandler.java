package org.example.handlers;

import org.apache.commons.lang3.math.NumberUtils;
import org.example.service.HttpActions;
import org.example.service.Supplier;

import java.util.Scanner;

public abstract class AbstractHandler {

    private Scanner scanner;
    private Supplier supplier;
    private HttpActions httpActions;

    public AbstractHandler() {
        this.supplier = Supplier.getInstance();
        this.httpActions = HttpActions.getInstance();
    }

    protected abstract String getTemplateName();

    public void handler(String params) {

        switch (params.toLowerCase().trim()) {
            case "get":
                get();
                break;
            case "post":
                post();
                break;
            case "put":
                put();
                break;
            case "delete":
                delete();
                break;
        }
    }


    protected void get() {

    }

    protected void post() {

    }

    protected void put() {

    }

    protected void delete() {

        System.out.println("Print pet id");
        String answer = scanner.next().trim();

        if (NumberUtils.isDigits(answer)) {
            httpActions.delete(answer);
        } else {
            supplier.errorMsg("Print correct pets id");
        }

        System.out.println("Continue? Yes/No");
        supplier.continueQuestion(scanner.next().trim());
    }
}


