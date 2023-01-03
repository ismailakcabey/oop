package fon;

import user.User;

import java.util.ArrayList;
import java.util.List;

public class bist {
    private User user;
    private Integer stockAmount = 20;
    stockEnum stock;

    private Integer stockPrice ;

    private List<String> bistList = new ArrayList<String>();

    public void bist(){
        System.out.println("BIST FONKSIYON");
    }

    public void setStockPrice(int stockPrice){
        this.stockPrice = stockPrice;
    }

    public Integer getStockPrice() {
        return stockPrice;
    }

    public void setUser(User user){
        this.user = user;
    }



    public List getListBist() {
        return bistList;
    }

    public Integer getStockAmount() {
        return stockAmount;
    }

    public void setStockAmount( stockEnum stock , int amount) {
        this.stock = stock;
        this.stockAmount=stockAmount;
        this.bistList.add("-------\nhisse : "+ stock + "birimi fiyati : " + stockAmount + " sahip olunan adet : " + stockPrice);
        System.out.println("-------\nhisse : "+ stock + "birimi fiyati : " + stockAmount + " sahip olunan adet : " + stockPrice);
    }

    public void setStockAmount(String stocks) {
    }
}
