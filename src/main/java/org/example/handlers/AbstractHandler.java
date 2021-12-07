package org.example.handlers;

import org.apache.commons.lang3.math.NumberUtils;
import org.example.model.pet.Pet;
import org.example.model.pet.Pets;
import org.example.service.HttpActions;
import org.example.service.Supplier;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpRequest;
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

        supplier.ordinaryMsg("Get by status or get by id?");
        String answer = scanner.next().trim().toLowerCase();

        switch (answer) {
            case "id":
                getById();
                break;
            case "status":
                getByStatus();
                break;
            default:
                System.out.println("Please enter correct info");
                get();
        }
    }

    protected void getByStatus() {
        String params = "findByStatus?status=";
        supplier.ordinaryMsg("Print "+ getTemplateName() + " status. Available status: sold, pending, available ");
        String statusOrId = scanner.next().toLowerCase().trim();
        HttpResponse response = httpActions.get(getTemplateName(), params, statusOrId);
        Pets pets = supplier.collectPets(response);
                for (Pet pet: pets){
                    supplier.ordinaryMsg(pet.toString());
                }
        supplier.ordinaryMsg("Continue? Yes/No");
        supplier.continueQuestion(scanner.next().trim());

    }


    protected void getById() {
        String params = "";
        supplier.ordinaryMsg("Print "+ getTemplateName() + " id");
        String statusOrId = scanner.next().toLowerCase().trim();
        HttpResponse response = httpActions.get(getTemplateName(), params, statusOrId);
        Pet pet = supplier.collectPet(response);
        supplier.ordinaryMsg (pet.toString());
        supplier.ordinaryMsg("Continue? Yes/No");
        supplier.continueQuestion(scanner.next().trim());

    }

    protected void post() {

    }

    protected void put() {

    }

    protected void delete() {

        System.out.println("Print " + getTemplateName() + " id");
        String answer = scanner.next().trim();

        if (NumberUtils.isDigits(answer)) {
            httpActions.delete(answer, getTemplateName());
        } else {
            supplier.errorMsg("Print correct pets id");
        }

        supplier.ordinaryMsg("Continue? Yes/No");
        supplier.continueQuestion(scanner.next().trim());
    }
}


