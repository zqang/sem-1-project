package sample;

import sample.controller.LoginController;
import sample.model.CatData;

import java.sql.Timestamp;

public class Cat {
    private String name;
    private double happiness;
    private double hunger;
    private int level;
    private long timeOfBirth;
    private long timeToLive;
    private String username;

    private String furColour;
    private String eyeColour;
    private String personality;

    private Food food;
    private TTL ttl;

    public Cat(){
    }

    public Cat(String name) {
        this.timeOfBirth = System.currentTimeMillis();
        this.name = name;
        this.happiness = 0;
        this.hunger = 0;
        this.level = 1;
        this.timeToLive = 10000000;
        this.ttl = new TTL(timeToLive);
        this.username = null;
        this.personality = null;
        this.eyeColour = null;
        this.furColour=null;
    }

    public void eatFood(Food food){
        double happiness, hunger;
        happiness = food.getHappiness() + this.happiness;
        setHappiness(happiness);

        hunger = this.hunger - food.getHunger();
        if(hunger<0){
            hunger=0;
        }
        setHunger(hunger);

    }

    public void isDied(){
//        if(ttl.getTimestamp().equals(new Timestamp(System.currentTimeMillis()))){
            System.out.print("Your cat is died. Shame on you");
            CatData.getInstance().open();
            boolean isDeleted = CatData.getInstance().deleteCat(Global.cat.getName(),Global.users.getUsername());
            System.out.println(isDeleted);
            CatData.getInstance().close();
//            return true;
//        }else
//            return false;
    }



    public void setName(String name) {
        this.name = name;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public void setTimeOfBirth(long timeOfBirth) {
        this.timeOfBirth = timeOfBirth;
    }

    public void setFood(Food food) {
        this.food = food;
    }

    public void setTtl(TTL ttl) {
        this.ttl = ttl;
    }

    public void addTTL(Long timeToLive){
        this.ttl.addTtl(timeToLive);
    }

    public void setHappiness(double happiness) {
        this.happiness = happiness;
    }

    public void setHunger(double hunger) {
        this.hunger = hunger;
    }

    public long getTimeToLive() {
        return timeToLive;
    }

    public void setTimeToLive(long timeToLive) {
        this.timeToLive = timeToLive;
    }

    public String getName() {
        return name;
    }

    public double getHappiness() {
        return happiness;
    }

    public double getHunger() {
        return hunger;
    }

    public int getLevel() {
        return level;
    }

    public long getTimeOfBirth() {
        return timeOfBirth;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getFurColour() {
        return furColour;
    }

    public void setFurColour(String furColour) {
        this.furColour = furColour;
    }

    public String getEyeColour() {
        return eyeColour;
    }

    public void setEyeColour(String eyeColour) {
        this.eyeColour = eyeColour;
    }

    public String getPersonality() {
        return personality;
    }

    public void setPersonality(String personality) {
        this.personality = personality;
    }

    public TTL getTtl() {
        return ttl;
    }
}
