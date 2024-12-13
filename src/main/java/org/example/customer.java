package org.example;

import java.io.IOException;
import java.sql.*;

public class customer {
    private int id;

    private String name;
    public customer(String name){

        this.name=name;
    }

    public String getName() {
        return name;
    }

    public boolean check() throws SQLException, IOException {
        String url="jdbc:mysql://localhost:3306/data?useSSL=false&serverTimezone=UTC";
        String user="root";
        String passwd="root";
        Connection conn= DriverManager.getConnection(url,user,passwd);
        String sql="SELECT * FROM customer WHERE name = ?";
        PreparedStatement stmt= conn.prepareStatement(sql);
        stmt.setString(1,this.name);
        ResultSet rs=stmt.executeQuery();
       return rs.next();
    }

}
