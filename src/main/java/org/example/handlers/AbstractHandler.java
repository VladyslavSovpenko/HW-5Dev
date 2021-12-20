package org.example.handlers;

import com.google.gson.Gson;
import org.apache.commons.lang3.math.NumberUtils;
import org.example.model.ApiResponse;
import org.example.service.HttpActions;
import org.example.service.Supplier;
import java.net.http.HttpResponse;
import java.util.Scanner;

public abstract class AbstractHandler {

    Supplier supplier = Supplier.getInstance();
    HttpActions httpActions = new HttpActions();
    Scanner scanner = new Scanner(System.in);

    protected AbstractHandler() {
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
        System.out.println("Print " + getTemplateName() + " id");
        String answer = scanner.next().trim();
        HttpResponse response = null;
        if (NumberUtils.isDigits(answer)) {
            if (getTemplateName().equals("store")) {
                answer = "order/" + answer;
            }
            response = httpActions.delete(answer, getTemplateName());
        } else {
            supplier.errorMsg("Print correct pets id");
        }

        ApiResponse apiResponse = supplier.collectApiResponse(response);
        supplier.ordinaryMsg(apiResponse.toString());

        supplier.ordinaryMsg("Continue? Yes/No");
        supplier.continueQuestion(scanner.next().trim());

    }
}

