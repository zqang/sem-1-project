package sample;

import com.sun.deploy.security.SelectableSecurityManager;

import java.sql.Timestamp;

public class Food {
    private Munny munny;
    private Cat cat;
    private int price;
    private String name;
    private double hunger;
    private double happiness;
    private TTL ttl;


    public Food(String name, int price, double happiness, double hunger){
        this.name = name;
        this.price = price;
        this.happiness = happiness;
        this.hunger = hunger;
        this.ttl = new TTL(60000);
    }



    public boolean isExpired(){
        if(ttl.getTimestamp().equals(new Timestamp(System.currentTimeMillis()))){
            System.out.print("food is expired");
            return true;
        }else
            return false;
    }

    public TTL getTtl(){
        return ttl;
    }

    public int getPrice() {
        return price;
    }

    public double getHunger() {
        return hunger;
    }

    public double getHappiness() {
        return happiness;
    }

}
