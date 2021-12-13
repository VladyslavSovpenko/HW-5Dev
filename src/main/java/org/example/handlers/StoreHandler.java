package org.example.handlers;

import org.example.model.pet.Pet;
import org.example.model.store.Store;

import java.net.http.HttpResponse;
import java.util.*;

public class StoreHandler extends AbstractHandler {

    private Scanner scanner;

    public StoreHandler(Scanner scanner) {
        this.scanner = scanner;
    }

    @Override
    protected String getTemplateName() {
        return "store";
    }

    @Override
    protected void post() {
        supplier.createStore(scanner);
        httpActions.post(getTemplateName());
        supplier.ordinaryMsg("Continue? Yes/No");
        supplier.continueQuestion(scanner.next().trim());
    }

    @Override
    protected void get() {
        supplier.ordinaryMsg("Get by id or inventory?");
        String answer = scanner.next().trim().toLowerCase();

        switch (answer) {
            case "id":
                getStoreById();
                break;
            case "inventory":
                getByInventory();
                break;
            default:
                System.out.println("Please enter correct info");
                get();
        }

    }

    private void getByInventory() {
        String params = "inventory";

        HttpResponse response = httpActions.get(getTemplateName(), params, "");
        HashMap<String, Integer> inventoryStoreMap = supplier.inventoryStoreMap(response);
        inventoryStoreMap.entrySet().forEach(entry -> {
            supplier.ordinaryMsg(entry.getKey()+ " "+entry.getValue());
        });
    }

    private void getStoreById() {
        String params = "order/";
        supplier.ordinaryMsg("Print " + getTemplateName() + " id");
        String statusOrId = scanner.next().toLowerCase().trim();
        HttpResponse response = httpActions.get(getTemplateName(), params, statusOrId);

        Store store = supplier.collectStore(response);
        supplier.ordinaryMsg(store.toString());

        supplier.ordinaryMsg("Continue? Yes/No");
        supplier.continueQuestion(scanner.next().trim());
    }
}
