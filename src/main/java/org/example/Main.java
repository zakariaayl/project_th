package org.example;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.*;
import java.sql.SQLException;
import java.util.Date;

public class Main extends Thread {
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
    @Override
    public void run() {
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
                    } catch (FileNotFoundException e) {
                        throw new RuntimeException(e);
                    } catch (IOException e) {
                        throw new RuntimeException(e);
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
                        boolean b= false;
                        try {
                            b = order.saveOrder();
                        } catch (SQLException e) {
                            throw new RuntimeException(e);
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }
                        if(b){
                            System.out.println("nice");
                            String PATH="src/main/java/org/example/output/"+item;
                            CreateFle(PATH);
                        }else{
                            System.out.println("that's not nice");
                            String PATH="src/main/java/org/example/error/"+item;
                            CreateFle(PATH);
                        }

                    }
                }
                file.delete();
            }
        } else {
            System.out.println("walo");
        }

    }
    public static void main(String[] args) throws InterruptedException {
        Main thread=new Main();
        thread.sleep(3600*1000);
        thread.start();
    }
    }


