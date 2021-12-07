package org.example.service;

import com.google.gson.Gson;
import org.example.MainInput;
import org.example.model.pet.Pet;
import org.example.model.pet.Pets;

import java.net.http.HttpResponse;

public class Supplier {

    private static Supplier instance;

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

    public void ordinaryMsg(String message){
        System.out.println(message);
    }

    public void continueQuestion(String contAnswer) {

        if (contAnswer.equalsIgnoreCase("yes")) {
            MainInput.run();
        } else {
            System.out.println("Bye");
        }
    }
    public Pet collectPet(HttpResponse response){
        Gson gson = new Gson();
        Pet pet = gson.fromJson(String.valueOf(response.body()),Pet.class);
        return pet;
    }
    public Pets collectPets(HttpResponse response){
        Gson gson =  new Gson();
        Pets pets = gson.fromJson(String.valueOf(response.body()), Pets.class);
        return pets;
    }

}

