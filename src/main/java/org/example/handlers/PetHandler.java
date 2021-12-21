package org.example.handlers;

import org.apache.log4j.Logger;
import org.example.model.ApiResponse;
import org.example.model.pet.Pet;
import org.example.model.pet.Pets;
import java.net.http.HttpResponse;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;


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
                post();
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
        httpActions.post(getTemplateName(),pet);
        supplier.ordinaryMsg("Continue? Yes/No");
        supplier.continueQuestion(scanner.next().trim());

    }

    private void addImage() {
        supplier.ordinaryMsg("Print pet id");
        String params = scanner.next() + "/uploadImage";
        supplier.ordinaryMsg("Print image name");
        String name = scanner.next();

        Map<Object, Object> data = new HashMap<>();
        data.put("additionalMetadata", "MyPet");
        data.put("file", Paths.get(name));

        HttpResponse response = httpActions.postImage(params, data);
        ApiResponse apiResponse = supplier.collectApiResponse(response);
        supplier.ordinaryMsg(apiResponse.toString());

        supplier.ordinaryMsg("Continue? Yes/No");
        supplier.continueQuestion(scanner.next().trim());
    }

    private void newPet() {
        Pet pet = supplier.createPets(scanner);
        httpActions.post(getTemplateName(), pet);
        supplier.ordinaryMsg("Continue? Yes/No");
        supplier.continueQuestion(scanner.next().trim());
    }

    @Override
    protected void put() {

        Pet pet = supplier.createPets(scanner);
        HttpResponse response = httpActions.put(getTemplateName(), pet);
        supplier.ordinaryMsg(String.valueOf(supplier.collectPet(response)));
        supplier.ordinaryMsg("Continue? Yes/No");
        supplier.continueQuestion(scanner.next().trim());
    }
}
