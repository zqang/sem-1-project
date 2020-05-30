package sample;

public class Munny {
    private int Munnybalance;


    public Munny(){
        this.Munnybalance= 100;

    }

    public int getMunnybalance() {
        return Munnybalance;
    }

    public void setMunnybalance(int munnybalance) {
        Munnybalance = munnybalance;
    }

    public void lossMunny(int munny){
        if(this.getMunnybalance()>=munny){
            setMunnybalance(getMunnybalance()-munny);
            System.out.println(this.getMunnybalance());
        }else{
            return;
            //alert user don have enough munny
            //show the munnybalance
        }
    }

    public void gainMunny(int munny){
        setMunnybalance(getMunnybalance()+munny);
        //show the munnybalance
    }
}
