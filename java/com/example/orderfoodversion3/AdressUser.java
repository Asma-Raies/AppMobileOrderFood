package com.example.orderfoodversion3;

import java.util.ArrayList;
import java.util.List;

public class AdressUser {
    String addresse ;
    Long Total ;
    int Qte ;
    List<String> foodNameList = new ArrayList<>();

    public AdressUser(String addresse, Long total, List<String> foodNameList) {
        this.addresse = addresse;
        Total = total;
       // Qte = qte;
        this.foodNameList = foodNameList;
    }

    public List<String> getFoodNameList() {
        return foodNameList;
    }

    public void setFoodNameList(List<String> foodNameList) {
        this.foodNameList = foodNameList;
    }

    public AdressUser(){

    }
    public String getAddresse() {
        return addresse;
    }

    public void setAddresse(String addresse) {
        this.addresse = addresse;
    }


    public Long getTotal() {
        return Total;
    }

    public void setTotal(Long total) {
        Total = total;
    }
}
