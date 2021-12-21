package org.example.service;

import com.google.gson.Gson;
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
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;


public class HttpActions {

    Supplier supplier = Supplier.getInstance();
    public static final Logger LOGGER = Logger.getLogger(HttpActions.class);
    HttpClient client = HttpClient.newHttpClient();
    Gson gson = new Gson();

    public HttpResponse delete(String answer, String handlerName) {

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
        return response;
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

    public HttpResponse post(String templateName, Object object) {

        String url = "https://petstore.swagger.io/v2/%s";
        if (templateName.equals("store")) {
            url = url + "/order";
        }
        HttpRequest httpRequest = HttpRequest.newBuilder()
                .uri(URI.create(String.format(url, templateName)))
                .headers("Content-Type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(gson.toJson(object)))
                .build();
        HttpResponse<String> response = null;
        try {
            response = client.send(httpRequest, HttpResponse.BodyHandlers.ofString());
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }

        if (response.statusCode() == 200) {
            supplier.ordinaryMsg("Record was created/updated");
        } else {
            supplier.ordinaryMsg("Record not posted");
        }
        return response;
    }

    public HttpResponse put(String templateName, Object object) {

        String url = "https://petstore.swagger.io/v2/%s";
        if (templateName.equals("store")) {
            url = url + "/order";
        }
        HttpRequest httpRequest = HttpRequest.newBuilder()
                .uri(URI.create(String.format(url, templateName)))
                .headers("Content-Type", "application/json")
                .PUT(HttpRequest.BodyPublishers.ofString(gson.toJson(object)))
                .build();
        HttpResponse<String> response = null;
        try {
            response = client.send(httpRequest, HttpResponse.BodyHandlers.ofString());
        } catch (IOException | InterruptedException e) {
            LOGGER.error("Put method problem", e);
        }
        return response;
    }


    public HttpResponse postImage(String params, Map<Object, Object> data) {
        String url = "https://petstore.swagger.io/v2/pet/%s";
        String boundary = "-------------oiawn4tp89n4e9p5";

        HttpRequest request = null;
        try {
            request = HttpRequest.newBuilder().uri(URI.create(String.format(url,params)))
                    .headers("accept", "application/json",
                            "Content-Type", "multipart/form-data; boundary=" + boundary)
                    .POST(oMultipartData(data, boundary))
                    .build();
        } catch (IOException e) {
            e.printStackTrace();
        }

        HttpResponse<String> response = null;
        try {
            response = client.send(request,
                    HttpResponse.BodyHandlers.ofString());
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }

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
