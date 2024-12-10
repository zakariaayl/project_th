package org.example;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class orders {
    int id;
    Date dateS;
    float amount;
    customer c;
    String status;
    List<String> items=new ArrayList<>();
    String addresse ;
    public orders(int id,Date date,float amount,customer d,List<String> items,String addresse,String status) {
        this.id=id;
        this.dateS=date;
        this.amount=amount;
        this.addresse=addresse;
        this.status=status;
        this.items = items;
    }

}
