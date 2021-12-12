package org.example.service;

import com.google.gson.Gson;
import org.apache.log4j.Logger;
import org.example.MainInput;
import org.example.model.pet.Category;
import org.example.model.pet.Pet;
import org.example.model.pet.Pets;
import org.example.model.pet.Tag;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.http.HttpResponse;
import java.util.List;
import java.util.Scanner;

public class Supplier {

    public static final Logger LOGGER = Logger.getLogger(Supplier.class);

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

    public Pets collectPets(HttpResponse response) {
        Gson gson = new Gson();
        Pets pets = gson.fromJson(String.valueOf(response.body()), Pets.class);
        return pets;
    }

    public void createPets(Scanner scanner) {
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

        File file=new File("pet.json");
        try {
            file.createNewFile();
        } catch (IOException e) {
            LOGGER.error("File not created",e);
        }
        FileWriter fileWriter = null;
        try {
            fileWriter = new FileWriter(file);
            fileWriter.write(gson.toJson(pet));
            fileWriter.flush();
            fileWriter.close();
        } catch (IOException e) {
            LOGGER.error("File no writed", e);
        }



    }
}

