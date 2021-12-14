package org.example.handlers;

import org.apache.log4j.Logger;
import org.example.model.pet.Pet;
import org.example.model.pet.Pets;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.http.HttpResponse;


public class PetHandler extends AbstractHandler {

    public static final Logger LOGGER = Logger.getLogger(PetHandler.class);

    @Override
    protected String getTemplateName() {
        return "pet";
    }

    @Override
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

    private void getByStatus() {
        String params = "findByStatus?status=";
        supplier.ordinaryMsg("Print " + getTemplateName() + " status. Available status: sold, pending, available ");
        String statusOrId = scanner.next().toLowerCase().trim();
        HttpResponse response = httpActions.get(getTemplateName(), params, statusOrId);
        Pets pets = supplier.collectPets(response);
        for (Pet pet : pets) {
            supplier.ordinaryMsg(pet.toString());
        }
        supplier.ordinaryMsg("Continue? Yes/No");
        supplier.continueQuestion(scanner.next().trim());
    }


    private void getById() {
        String params = "";
        supplier.ordinaryMsg("Print " + getTemplateName() + " id");
        String statusOrId = scanner.next().toLowerCase().trim();
        HttpResponse response = httpActions.get(getTemplateName(), params, statusOrId);
        Pet pet = supplier.collectPet(response);
        supplier.ordinaryMsg(pet.toString());
        supplier.ordinaryMsg("Continue? Yes/No");
        supplier.continueQuestion(scanner.next().trim());
    }

    @Override
    protected void post() {
        supplier.ordinaryMsg("Add new pet in store(print new), uploads an image(print image), update a pet in the store(print update)?");
        String answer = scanner.next().trim().toLowerCase();

        switch (answer) {
            case "new":
                newPet();
                break;
            case "image":
                addImage();
                break;
            case "update":
                updatePet();
                break;
            default:
                System.out.println("Please enter correct info");
                get();
        }
    }

    private void updatePet() {
        supplier.ordinaryMsg("Print pet id");
        HttpResponse response = httpActions.get(getTemplateName(), "", String.valueOf(scanner.nextInt()));
        Pet pet = supplier.collectPet(response);
        supplier.ordinaryMsg("Print new pet name");
        pet.setName(scanner.next());
        supplier.ordinaryMsg("Print new pet status. Available status: sold, pending, available");
        pet.setStatus(scanner.next());

        supplier.saveToFile(pet);

        httpActions.post(getTemplateName());

    }

    private void addImage() {

    }

    private void newPet() {
        Pet pet = supplier.createPets(scanner);
        supplier.saveToFile(pet);
        httpActions.post(getTemplateName());
        supplier.ordinaryMsg("Continue? Yes/No");
        supplier.continueQuestion(scanner.next().trim());
    }

    @Override
    protected void put() {
        
    }
}
