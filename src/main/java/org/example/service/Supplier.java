package org.example.service;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.apache.log4j.Logger;
import org.example.MainInput;
import org.example.model.ApiResponse;
import org.example.model.pet.Category;
import org.example.model.pet.Pet;
import org.example.model.pet.Pets;
import org.example.model.pet.Tag;
import org.example.model.store.Store;
import org.example.model.user.User;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.http.HttpResponse;
import java.text.SimpleDateFormat;
import java.util.*;

public class Supplier {

    public static final Logger LOGGER = Logger.getLogger(Supplier.class);
    private final TypeToken<HashMap<String, Integer>> inventoryTypeToken = new TypeToken<>() {
    };

    private static Supplier instance;
    Gson gson = new Gson();

    private Supplier() {
    }

    public static Supplier getInstance() {
        if (instance == null) {
            instance = new Supplier();
        }
        return instance;
    }

    public void errorMsg(String message) {
        System.err.println(message);
    }

    public void ordinaryMsg(String message) {
        System.out.println(message);
    }

    public void continueQuestion(String contAnswer) {

        if (contAnswer.equalsIgnoreCase("yes")) {
            MainInput.run();
        } else {
            System.out.println("Bye");
        }
    }

    public Pet collectPet(HttpResponse response) {
        Pet pet = gson.fromJson(String.valueOf(response.body()), Pet.class);
        return pet;
    }

    public Store collectStore(HttpResponse response) {
        Store store = gson.fromJson(String.valueOf(response.body()), Store.class);
        return store;
    }

    public User collectUser(HttpResponse response){
        User user = gson.fromJson(String.valueOf(response.body()), User.class);
        return user;
    }

    public HashMap<String, Integer> inventoryStoreMap(HttpResponse response) {
        HashMap<String, Integer> o = gson.fromJson(String.valueOf(response.body()), inventoryTypeToken.getType());
        return o;
    }

    public Pets collectPets(HttpResponse response) {

        Pets pets = gson.fromJson(String.valueOf(response.body()), Pets.class);
        return pets;
    }

    public ApiResponse collectApiResponse(HttpResponse response){
       ApiResponse apiResponse= gson.fromJson(String.valueOf(response.body()), ApiResponse.class);
       return apiResponse;
    }

    public Pet createPets(Scanner scanner) {

        Pet pet = new Pet();
        ordinaryMsg("Please, print pet id");
        pet.setId(scanner.nextInt());
        ordinaryMsg("Please, print pet name");
        pet.setName(scanner.next());
        ordinaryMsg("Please, print pet status: available, pending, sold");
        pet.setStatus(scanner.next());

        ordinaryMsg("Please, print pet tag id");
        Tag tag = new Tag();
        tag.setId(scanner.nextInt());
        ordinaryMsg("Please, print pet tag name");
        tag.setName(scanner.next());
        List<Tag> tags = List.of(tag);

        pet.setTags(tags);

        ordinaryMsg("Please, print pet category id");
        Category cat = new Category();
        cat.setId(scanner.nextInt());
        ordinaryMsg("Please, print pet category name");
        cat.setName(scanner.next());
        pet.setCategory(cat);

       return pet;
    }

    public Store createStore(Scanner scanner) {

        Store store = new Store();
        ordinaryMsg("Please, print store id");
        store.setId(scanner.nextInt());
        ordinaryMsg("Please, print petId id");
        store.setPetId(scanner.nextInt());
        ordinaryMsg("Please, print quantity id");
        store.setQuantity(scanner.nextInt());
        ordinaryMsg("Please, print status");
        store.setStatus(scanner.next());
        ordinaryMsg("Please, print complete. True/False");
        store.setComplete(scanner.nextBoolean());
        ordinaryMsg("Please, date.");
        store.setShipDate(scanner.next());

        return store;
    }

    public User createUser(Scanner scanner){
        User user = new User();
        ordinaryMsg("Please, print user id");
        user.setId(scanner.nextLong());
        ordinaryMsg("Please, print username");
        user.setUsername(scanner.next());
        ordinaryMsg("Please, print lastName");
        user.setLastName(scanner.next());
        ordinaryMsg("Please, print email");
        user.setEmail(scanner.next());
        ordinaryMsg("Please, print password");
        user.setPassword(scanner.next());
        ordinaryMsg("Please, print phone");
        user.setPhone(scanner.next());
        ordinaryMsg("Please, print userStatus");
        user.setUserStatus(scanner.nextLong());

        return user;
    }

     public void saveToFile(Object object){
         File file = new File("result.json");
         try {
             file.createNewFile();
         } catch (IOException e) {
             LOGGER.error("File not created", e);
         }
         FileWriter fileWriter = null;
         try {
             fileWriter = new FileWriter(file);
             fileWriter.write(gson.toJson(object));
             fileWriter.flush();
             fileWriter.close();
         } catch (IOException e) {
             LOGGER.error("File not wrote", e);
         }
     }
}

