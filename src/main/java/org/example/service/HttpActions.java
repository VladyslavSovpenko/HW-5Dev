package org.example.service;

import org.apache.log4j.Logger;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URI;

import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.file.Path;
import java.util.stream.Stream;


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

    public HttpResponse post(){

        String url = "https://petstore.swagger.io/v2/pet";

        HttpRequest request = null;
        try {
            request = HttpRequest.newBuilder()
                    .uri(new URI(url))
                    .header("Content-Type", "application/json")
                    .POST(HttpRequest.BodyPublishers.ofFile(Path.of("pet.json")))
                    .build();
        } catch (FileNotFoundException | URISyntaxException e) {
            LOGGER.error("File not found or URI is wrong", e);
        }

        HttpResponse<Stream<String>> response = null;
        try {
            response = client.send(request, HttpResponse.BodyHandlers.ofLines());
        } catch (IOException | InterruptedException e) {
            LOGGER.error("Problem with get response", e);
        }
        if (response.statusCode()==200){
            supplier.ordinaryMsg("Record was created/updated");
        }
        return response;
    }
}
