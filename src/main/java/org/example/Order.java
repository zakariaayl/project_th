package org.example;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Date;

public class Order {
    int id;
    String dateS;

    customer c;
    String status;
    String item;
    String addresse ;
    float price;
    public Order(String date, customer d, String item, String addresse, String status, float price) {

        this.dateS=date;
        this.c=d;
        this.addresse=addresse;
        this.status=status;
        this.item = item;
        this.price=price;
    }
    boolean saveOrder() throws SQLException, IOException {
       boolean r =this.c.check();
       if(r==true){
            String url="jdbc:mysql://localhost:3306/data";
           String user="root";
            String passwd="root";
           Connection conn= DriverManager.getConnection(url,user,passwd);
           String sql="INSERT INTO `order` VALUES(null,?,?,?,?,?,?)";
           PreparedStatement stmt=conn.prepareStatement(sql);
           stmt.setString(1,this.dateS);
           stmt.setFloat(2,this.price);
           stmt.setString(3,this.status);
           stmt.setString(4,this.c.getName());
           stmt.setString(5,this.item);
           stmt.setString(6,this.addresse);
           stmt.executeUpdate();
           System.out.println("worked");
           return true;
       }
       else{
           System.out.println("there is no custumer in that name");
           return false;
       }
    }

}
