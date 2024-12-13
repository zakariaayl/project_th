package org.example;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Date;

public class Main {
    public static void DeleteFile(String path){
            File myObj = new File(path);
            if (myObj.delete()) {
                System.out.println("Deleted the file: " + myObj.getName());
            } else {
                System.out.println("Failed to delete the file.");
            }

    }
    public static void CreateFle(String path){
        try {
            File myObj = new File(path);
            if (myObj.createNewFile()) {
                System.out.println("File created: " + myObj.getName());
            } else {
                System.out.println("File already exists.");
            }
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws IOException, SQLException {
        File folder =new File("src/main/java/org/example/input");

        if (folder!=null && folder.isDirectory()) {
            int numfile =1;

            for (File file:folder.listFiles()) {
                if (file.isFile()) {
                    StringBuilder json=new StringBuilder();
                    try (BufferedReader b=new BufferedReader(new FileReader(file))) {
                        String line;
                        while ((line =b.readLine())!=null) {
                            json.append(line.trim());
                        }
                    }

                    System.out.println("File Number: "+numfile);
                    System.out.println("File Name: "+file.getName());
                    System.out.println(json.toString());
                    numfile++;
                    JsonArray jsonArray =JsonParser.parseString(json.toString()).getAsJsonArray();

                    for (com.google.gson.JsonElement element : jsonArray) {
                        JsonObject object =element.getAsJsonObject();
                        String customerName =object.get("customerName").getAsString();
                        String date =object.get("date").getAsString();
                        float price =Float.parseFloat(object.get("price").getAsString());
                        String status =object.get("status").getAsString();
                        String item =object.get("item").getAsString();
                        String addresse =object.get("shippingAddress").getAsString();
                        Order order=new Order(date,new customer(customerName),item,addresse,status,price);
                        boolean b=order.saveOrder();
                        if(b){
                            System.out.println("nice");
                            String PATH="src/main/java/org/example/output/"+item;
                            CreateFle(PATH);
                        }else{
                            System.out.println("that's not nice");
                            String PATH="src/main/java/org/example/error/"+item;
                            CreateFle(PATH);
                        }
                        String pathtodlete="src/main/java/org/example/output/"+file.getName();
                        DeleteFile(pathtodlete);
                    }
                }
            }
        } else {
            System.out.println("walo");
        }
    }
}
