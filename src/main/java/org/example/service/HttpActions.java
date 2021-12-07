package org.example.service;

import org.apache.log4j.Logger;

import org.example.model.pet.Pet;
import org.example.model.pet.Pets;

import java.io.IOException;
import java.net.URI;

import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;


public class HttpActions {

    Supplier supplier = Supplier.getInstance();

    public static final Logger LOGGER = Logger.getLogger(HttpActions.class);

    HttpClient client = HttpClient.newHttpClient();

    public void delete(String answer, String handlerName) {

        String url = "https://petstore.swagger.io/v2/%s/%s";

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(String.format(url, handlerName, answer)))
                .DELETE()
                .build();

        HttpResponse response = null;
        try {
            response = client.send(request, HttpResponse.BodyHandlers.ofString());
        } catch (IOException | InterruptedException e) {
            LOGGER.error("Delete action problem", e);
        }
        if (response.statusCode() == 200) {
            supplier.ordinaryMsg("Record with id= " + answer + " was deleted");
        } else {
            supplier.ordinaryMsg("Record with id= " + answer + " was not deleted");
        }
    }

    public HttpResponse get(String templateName, String params, String statusOrId) {

        String url = "https://petstore.swagger.io/v2/%s/%s%s";

        HttpRequest request = HttpRequest.newBuilder()
                .GET()
                .uri(URI.create(String.format(url, templateName, params, statusOrId)))
                .build();
        HttpResponse response = null;
        try {
            response = client.send(request, HttpResponse.BodyHandlers.ofString());
            if (response.statusCode() == 200) {
                supplier.ordinaryMsg("Record with params " + statusOrId + " are get");

            } else {
                supplier.ordinaryMsg("Record with params \"" + statusOrId + "\" were not received ");
            }
        } catch (IOException | InterruptedException e) {
            LOGGER.error("Problem with get response", e);
        }
        return response;
    }
}
