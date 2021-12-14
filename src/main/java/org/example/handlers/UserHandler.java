package org.example.handlers;

import org.example.model.store.Store;
import org.example.model.user.User;
import org.example.service.HttpActions;
import org.example.service.Supplier;

import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;
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

    @Override
    protected void get() {
        supplier.ordinaryMsg("Login(1), logout(2) or get by id(3)?");
        String answer = scanner.next().trim().toLowerCase();

        switch (answer) {
            case "3":
                getUserById();
                break;
            case "2":
                getUserLogOut();
                break;
            case "1":
                getUserLogin();
                break;
            default:
                System.out.println("Please enter correct info");
                get();
        }
    }

    private void getUserLogin() {
        supplier.ordinaryMsg("Print login");
        String param1 = "login?username=" + scanner.next();
        supplier.ordinaryMsg("Print password");
        param1 = param1 + "&password=" + scanner.next();
        HttpResponse response = httpActions.get(getTemplateName(), param1, "");

        if (response.statusCode() == 200) {
            supplier.ordinaryMsg("Login success");
        }
        supplier.ordinaryMsg("Continue? Yes/No");
        supplier.continueQuestion(scanner.next().trim());
    }

    private void getUserLogOut() {
        HttpResponse response = httpActions.get(getTemplateName(), "logout", "");
        if (response.statusCode() == 200) {
            supplier.ordinaryMsg("Logout success");
        }
        supplier.ordinaryMsg("Continue? Yes/No");
        supplier.continueQuestion(scanner.next().trim());
    }

    private void getUserById() {
        String params = "";
        supplier.ordinaryMsg("Print " + getTemplateName() + " id");
        String statusOrId = scanner.next().toLowerCase().trim();
        HttpResponse response = httpActions.get(getTemplateName(), params, statusOrId);

        if (response.statusCode() == 200) {
            User user = supplier.collectUser(response);
            supplier.ordinaryMsg(user.toString());
        }

        supplier.ordinaryMsg("Continue? Yes/No");
        supplier.continueQuestion(scanner.next().trim());
    }

    @Override
    protected void post() {
        supplier.ordinaryMsg("Create user(1) or create with list(2) or array(3)?");
        String answer = scanner.next().trim().toLowerCase();

        switch (answer) {
            case "1":
                userCreate();
                break;
            case "2":
                postList();
                break;
            case "3":
                postArray();
            default:
                System.out.println("Please enter correct info");
                get();
        }
    }

    private void userCreate() {
        supplier.createUser(scanner);
        httpActions.post(getTemplateName());
        supplier.ordinaryMsg("Continue? Yes/No");
        supplier.continueQuestion(scanner.next().trim());
    }

    private void postArray() {
        supplier.ordinaryMsg("How many users you want to add?");
        ArrayList<User> users = new ArrayList<>();
        int result = scanner.nextInt();
        for (int i = 0; i < result; i++) {
            users.add(supplier.createUser(scanner));
        }
        supplier.saveToFile(users);
        httpActions.post(getTemplateName());
    }

    private void postList() {
        supplier.ordinaryMsg("How many users you want to add?");
        List<User> users = new ArrayList<>();
        int result = scanner.nextInt();
        for (int i = 0; i < result; i++) {
            users.add(supplier.createUser(scanner));
        }
        supplier.saveToFile(users);
        httpActions.post(getTemplateName());
    }

    @Override
    protected void put() {
//        'https://petstore.swagger.io/v2/user/string'
        supplier.ordinaryMsg("Print user name");
        httpActions.get(getTemplateName(),"", scanner.next());
    }
}
