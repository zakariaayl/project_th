package org.example;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
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
                        System.out.println(customerName);
                    }
                }
            }
        } else {
            System.out.println("walo");
        }
    }
}
