package org.example.service;

import org.apache.log4j.Logger;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Scanner;


public class HttpActions {

    private static HttpActions instance;

    private Scanner scanner;
    public static final Logger LOGGER = Logger.getLogger(HttpActions.class);

    private HttpActions() {
    }

    HttpClient client = HttpClient.newHttpClient();

    public static HttpActions getInstance() {
        if (instance == null) {
            instance = new HttpActions();
        }
        return instance;
    }

    public void delete(String answer) {

        String url = "https://petstore.swagger.io/v2/pet/%s";

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(String.format(url, answer)))
                .DELETE()
                .build();

        HttpResponse response = null;
        try {
            response = client.send(request, HttpResponse.BodyHandlers.ofString());
        } catch (IOException | InterruptedException e) {
            LOGGER.error("Delete action problem", e);
        }

        if (response.statusCode() == 200) {
            System.out.println("Record with id= " + answer + " was deleted");
        } else {
            System.out.println("Record with id= " + answer + " was not deleted");
        }
    }
}
