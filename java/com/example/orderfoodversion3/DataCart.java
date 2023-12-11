package com.example.orderfoodversion3;

public class DataCart {
    String foodName ;
    int foodQte ;
    String foodDesc ;
    Long foodTotal ;

    public DataCart(String foodName, int foodQte, String foodDesc, Long totalQte) {
        this.foodName = foodName;
        this.foodQte = foodQte;
        this.foodDesc = foodDesc;
        this.foodTotal = totalQte;
    }
public DataCart(){

}
    public String getFoodName() {
        return foodName;
    }

    public void setFoodName(String foodName) {
        this.foodName = foodName;
    }






    public String getFoodDesc() {
        return foodDesc;
    }

    public void setFoodDesc(String foodDesc) {
        this.foodDesc = foodDesc;
    }

    public int getFoodQte() {
        return foodQte;
    }

    public void setFoodQte(int foodQte) {
        this.foodQte = foodQte;
    }

    public Long getFoodTotal() {
        return foodTotal;
    }

    public void setFoodTotal(Long foodTotal) {
        this.foodTotal = foodTotal;
    }
}
