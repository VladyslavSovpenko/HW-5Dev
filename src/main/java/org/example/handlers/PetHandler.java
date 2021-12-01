package org.example.handlers;

import org.apache.commons.lang3.math.NumberUtils;
import org.example.service.HttpActions;
import org.example.service.Supplier;

import java.util.Scanner;

public class PetHandler extends AbstractHandler {

    private Scanner scanner;
    private Supplier supplier;
    private HttpActions httpActions;

    public PetHandler(Scanner scanner) {
        this.scanner = scanner;
        this.supplier = Supplier.getInstance();
        this.httpActions = HttpActions.getInstance();
    }

    @Override
    protected String getTemplateName() {
        return "pet";
    }

//    @Override
//    protected void delete() {
//
//        System.out.println("Print pet id");
//        String answer = scanner.next().trim();
//
//        if (NumberUtils.isDigits(answer)) {
//            httpActions.delete(answer);
//        } else {
//            supplier.errorMsg("Print correct pets id");
//        }
//
//        System.out.println("Continue? Yes/No");
//        supplier.continueQuestion(scanner.next().trim());
//    }

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
    @Override
    protected void get() {

        System.out.println("Get by status or get by id?");
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

    private void getById() {

    }

    private void getByStatus() {

    }
//
//private void getByStatus()throws URISyntaxException{
//        System.out.println("Available status:  available, pending, sold");
//        String answer=scanner.next().trim();
//        String add=null;
//        switch(answer){
//        case"available":
//        add="available";
//        break;
//        case"pending":
//        add="pending";
//        break;
//        case"sold":
//        add="sold";
//        break;
//        }
//        String url="https://petstore.swagger.io/v2/pet/findByStatus?status=%s";
//
//        HttpClient client=HttpClient.newBuilder().build();
//        HttpRequest request=HttpRequest.newBuilder()
//        .uri(new URI(String.format(url,add)))
//        .build();
//        try{
//        HttpResponse response=client.send(request,HttpResponse.BodyHandlers.ofString());
//        System.out.println(response.statusCode());
//        Gson gson=new Gson();
//        Pets pets=gson.fromJson(String.valueOf(response.body()),Pets.class);
//        for(Pet pet:pets){
//        System.out.println(pet);
//        }
//
//        System.out.println("Continue? Yes/No");
//        String contAnswer=scanner.next().trim();
//        Supplier.continueQuestion(contAnswer);
//
//        }catch(IOException|InterruptedException e){
//        Supplier.errorMsg("problem with response");
//        }
//
//        }
//
//private void getById(){
//        String url="https://petstore.swagger.io/v2/pet/";
//        System.out.println("Print pet id");
//
//        String answer=scanner.next().trim();
//
//        if(NumberUtils.isDigits(answer)){
//        HttpClient client=HttpClient.newBuilder().build();
//        HttpRequest request=null;
//        try{
//        request=HttpRequest.newBuilder()
//        .uri(new URI(url+answer))
//        .build();
//        }catch(URISyntaxException e){
//        e.printStackTrace();
//        }
//        HttpResponse<Stream<String>>response=null;
//
//        try{
//        response=client.send(request,HttpResponse.BodyHandlers.ofLines());
//        System.out.println(response.statusCode());
//        response.body().forEach(System.out::println);
//
//        System.out.println("Continue? Yes/No");
//        String contAnswer=scanner.next().trim();
//        Supplier.continueQuestion(contAnswer);
//
//        }catch(IOException|InterruptedException e){
//        Supplier.errorMsg("Problem with catching by id");
//        }
//        }else{
//        System.out.println("Print correct id");
//        getById();
//        }
//
//
//        }
//

}
