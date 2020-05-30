package sample;

import java.io.Serializable;
import java.sql.Timestamp;
//u now do only the method and u don know how to make the program keep going until the cat die so u need to do something with it
public class TTL implements Runnable {

private Timestamp timestamp;
private long miliseconds;
private long createdTime;
private long timeToLive;

    public TTL(long miliseconds){
        this.miliseconds = miliseconds;
        this.createdTime = System.currentTimeMillis();
        this.timeToLive = createdTime+miliseconds;
        this.timestamp = new Timestamp(timeToLive);
    }

    @Override
    public void run(){
        TTL ttl = Global.cat.getTtl();
        miliseconds = ttl.miliseconds;
        long seconds = miliseconds/1000;
        seconds--;
        ttl.getTimestamp();
    }

    public Timestamp getTimestamp() {
        return timestamp;
    }

    public void addTtl(long milliseconds){
        this.timestamp = new Timestamp(milliseconds + this.timeToLive);
    }

}
