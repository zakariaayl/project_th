package org.example;

import java.io.IOException;
import java.sql.*;

public class customer {
    private int id;

    private String name;
    public customer(int id,String name){
        this.id=id;
        this.name=name;
    }

    public String getName() {
        return name;
    }

    public boolean check() throws SQLException, IOException {
        String url="jdbc:mysql://localhost:3306/data";
        String user="root";
        String passwd="root";
        Connection conn= DriverManager.getConnection(url,user,passwd);
        String sql="SELECT * FROM customer";
        Statement stmt= conn.createStatement();
        ResultSet rs=stmt.executeQuery(sql);
       while(rs.next()){
           if(this.name==rs.getString("name")) return true;
       }
       return false;
    }

}
