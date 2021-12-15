package org.example.service;

import org.apache.log4j.Logger;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URI;

import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Map;
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
            supplier.ordinaryMsg("Record with id = " + answer + " was deleted");
        } else {
            supplier.ordinaryMsg("Record with id = " + answer + " was not deleted");
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
        } catch (IOException | InterruptedException e) {
            LOGGER.error("Problem with get response", e);
        }
        return response;
    }

    public HttpResponse post(String templateName) {

        String url = "https://petstore.swagger.io/v2/%s";

        HttpRequest request = null;
        try {
            if (templateName.equals("store")) {
                url = url + "/order";
            }
            request = HttpRequest.newBuilder()
                    .uri(URI.create(String.format(url, templateName)))
                    .header("Content-Type", "application/json")
                    .POST(HttpRequest.BodyPublishers.ofFile(Path.of("pet.json")))
                    .build();
        } catch (FileNotFoundException e) {
            LOGGER.error("File not found or URI is wrong", e);
        }

        HttpResponse<Stream<String>> response = null;
        try {
            response = client.send(request, HttpResponse.BodyHandlers.ofLines());
        } catch (IOException | InterruptedException e) {
            LOGGER.error("Problem with get response", e);
        }
        if (response.statusCode() == 200) {
            supplier.ordinaryMsg("Record was created/updated");
        }
        return response;
    }

    public HttpResponse postImage(String params,  Map<Object, Object> data) {
        String url = "https://petstore.swagger.io/v2/pet/%s";
        String boundary = "-------------oiawn4tp89n4e9p5";

        HttpRequest request = null;
        try {
            request = HttpRequest.newBuilder()
                    .uri(URI.create(String.format(url, params)))
                    .POST(oMultipartData(data, boundary))
                    .headers("Content-Type",
                            "multipart/form-data; boundary=" + boundary)
                    .build();
        } catch (IOException e) {
            LOGGER.error("Image not post", e);
        }

        HttpResponse<String> response = null;
        try {
            response = client.send(request,
                    HttpResponse.BodyHandlers.ofString());
        } catch (IOException | InterruptedException e) {
            LOGGER.error("Response problem", e);
        }
        if (response.statusCode() == 200) {
            supplier.ordinaryMsg("Image was sent");
        } else {supplier.ordinaryMsg("Image was not sent");}
        return response;
    }

    public static HttpRequest.BodyPublisher oMultipartData(Map<Object, Object> data,
                                                           String boundary) throws IOException {
        var byteArrays = new ArrayList<byte[]>();
        byte[] separator = ("--" + boundary
                + "\r\nContent-Disposition: form-data; name=")
                .getBytes(StandardCharsets.UTF_8);
        for (Map.Entry<Object, Object> entry : data.entrySet()) {
            byteArrays.add(separator);

            if (entry.getValue() instanceof Path) {
                var path = (Path) entry.getValue();
                String mimeType = Files.probeContentType(path);
                byteArrays.add(("\"" + entry.getKey() + "\"; filename=\""
                        + path.getFileName() + "\"\r\nContent-Type: " + mimeType
                        + "\r\n\r\n").getBytes(StandardCharsets.UTF_8));
                byteArrays.add(Files.readAllBytes(path));
                byteArrays.add("\r\n".getBytes(StandardCharsets.UTF_8));
            } else {
                byteArrays.add(
                        ("\"" + entry.getKey() + "\"\r\n\r\n" + entry.getValue()
                                + "\r\n").getBytes(StandardCharsets.UTF_8));
            }
        }
        byteArrays
                .add(("--" + boundary + "--").getBytes(StandardCharsets.UTF_8));
        return HttpRequest.BodyPublishers.ofByteArrays(byteArrays);
    }
}
