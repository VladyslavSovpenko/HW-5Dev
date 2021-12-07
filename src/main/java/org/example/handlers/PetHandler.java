package org.example.handlers;

import org.apache.commons.lang3.math.NumberUtils;
import org.apache.log4j.Logger;
import org.example.service.HttpActions;
import org.example.service.Supplier;

import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Scanner;

public class PetHandler extends AbstractHandler {

    public static final Logger LOGGER = Logger.getLogger(PetHandler.class);

    @Override
    protected String getTemplateName() {
        return "pet";
    }


    //private void put(){
////        public String put(String url) throws IOException, InterruptedException {
////            HttpClient client=HttpClient.newHttpClient();
////            HttpRequest request = HttpRequest.newBuilder()
////                    .uri(URI.create(url))
////                    .header("Content-Type", "application/json")
////                    .PUT(HttpRequest
////                            .BodyPublishers
////                            .ofFile(Path.of("C:\\\\Users\\\\Vlad\\\\IdeaProjects\\\\HW-13\\\\src\\\\TaskOne\\\\user.json")))
////                    .build();
////            HttpResponse response = client.send(request,HttpResponse.BodyHandlers.ofString());
////
////            return String.valueOf(response.body());
//        }
//
//
//private void post(){
//
//        String url="https://petstore.swagger.io/v2/pet";
//
//        System.out.println("Please specify the path to a user json file");
//        String path=scanner.nextLine().toLowerCase();
//
//
//        HttpClient client=HttpClient.newHttpClient();
//        HttpRequest request=null;
//        try{
//        request=HttpRequest.newBuilder()
//        .uri(URI.create(url))
//        .header("Content-Type","application/json")
//        .POST(HttpRequest.BodyPublishers.ofFile(Path.of(path)))
//
//        .build();
//        }catch(FileNotFoundException e){
//        Supplier.getInstance().errorMsg("File not found");
//        }
//        try{
//        HttpResponse<String> response=
//        client.send(request,HttpResponse.BodyHandlers.ofString());
//
//        if(response.statusCode()==200){
//        System.out.println("Record was created");
//        }else{
//        System.out.println("Record was not created");
//        }
//
//        System.out.println("Continue? Yes/No");
//        String contAnswer=scanner.next().trim();
//        Supplier.getInstance().continueQuestion(contAnswer);
//
//        }catch(IOException|InterruptedException e){
//        Supplier.errorMsg("Problem with post");
//        }
//        }
//
//

}
