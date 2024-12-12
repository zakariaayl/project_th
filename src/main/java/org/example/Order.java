package org.example;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Order {
    int id;
    Date dateS;
    float amount;
    customer c;
    String status;
    String item;
    String addresse ;
    float price;
    public Order(int id,Date date,float amount,customer d,String item,String addresse,String status,float price) {
        this.id=id;
        this.dateS=date;
        this.amount=amount;
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
           String sql="INSERT INTO order VALUES(null,?,?,?,?,?,?)";
           PreparedStatement stmt=conn.prepareStatement(sql);
           stmt.setDate(1,(java.sql.Date)this.dateS);
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
           System.out.println("yawdi yawdi 3la l 9raya");
           return false;
       }
    }

}
