package sample;

import javafx.scene.image.Image;

public class Global {
    public static final String BLUEBLUE = "/resources/blue blue.png";
    public static final String BLUERED = "/resources/blue red.png";
    public static final String BLUEYELLOW = "/resources/blue yellow.png";
    public static final String BROWNBLUE = "/resources/brown blue.png";
    public static final String BROWNRED = "/resources/brown red.png";
    public static final String BROWNYELLOW = "/resources/brown yellow.png";
    public static final String ORANGEBLUE = "/resources/orange blue.png";
    public static final String ORANGERED = "/resources/orange red.png";
    public static final String ORANGEYELLOW = "/resources/orange yellow.png";

    public static final Image IMAGE0 = new Image("/resources/Walk (0).png",100,100,true,true);
    public static final Image IMAGE1 = new Image("/resources/Walk (1).png",100,100,true,true);
    public static final Image IMAGE2 = new Image("/resources/Walk (2).png",100,100,true,true);
    public static final Image IMAGE3 = new Image("/resources/Walk (3).png",100,100,true,true);
    public static final Image IMAGE4 = new Image("/resources/Walk (4).png",100,100,true,true);
    public static final Image IMAGE5 = new Image("/resources/Walk (5).png",100,100,true,true);
    public static final Image IMAGE6 = new Image("/resources/Walk (6).png",100,100,true,true);
    public static final Image IMAGE7 = new Image("/resources/Walk (7).png",100,100,true,true);
    public static final Image IMAGE8 = new Image("/resources/Walk (8).png",100,100,true,true);
    public static final Image IMAGE9 = new Image("/resources/Walk (9).png",100,100,true,true);

    public static final String WALKINGCAT = "/resources/Walk (1).png";
    public static final String COIN = "/resources/Gold_1.png";
    public static final String WALL = "/resources/index.jpeg";

    public static String username;
    public static Munny munny;
    public static Users users;
    public static double happiness;
    public static double hunger;
    public static int level;
    public static Cat cat;



    private static Global instance = new Global();
    private Global(){}
    public static Global getInstance(){
        return instance;
    }

    public Image setCatImage(String furColour, String eyeColour){
        if (furColour.equalsIgnoreCase("blue")  && eyeColour.equalsIgnoreCase("blue")) {
            return new Image(getClass().getResourceAsStream(Global.BLUEBLUE));
        }
        if (furColour.equalsIgnoreCase("blue") && eyeColour.equalsIgnoreCase("red")) {
            return new Image(getClass().getResourceAsStream(Global.BLUERED));
        }
        if (furColour.equalsIgnoreCase("blue") && eyeColour.equalsIgnoreCase("yellow")) {
            return new Image(getClass().getResourceAsStream(Global.BLUEYELLOW));
        }
        if (furColour.equalsIgnoreCase("brown") && eyeColour.equalsIgnoreCase("blue")) {
            return new Image(getClass().getResourceAsStream(Global.BROWNBLUE));
        }
        if (furColour.equalsIgnoreCase("brown") && eyeColour.equalsIgnoreCase("red")) {
            return new Image(getClass().getResourceAsStream(Global.BROWNRED));
        }
        if (furColour.equalsIgnoreCase("brown") && eyeColour.equalsIgnoreCase("yellow")) {
            return new Image(getClass().getResourceAsStream(Global.BROWNYELLOW));
        }
        if (furColour.equalsIgnoreCase("orange") && eyeColour.equalsIgnoreCase("blue")) {
            return new Image(getClass().getResourceAsStream(Global.ORANGEBLUE));
        }
        if (furColour.equalsIgnoreCase("orange") && eyeColour.equalsIgnoreCase("red")) {
            return new Image(getClass().getResourceAsStream(Global.ORANGERED));
        }
        if (furColour.equalsIgnoreCase("orange") && eyeColour.equalsIgnoreCase("yellow")) {
            return new Image(getClass().getResourceAsStream(Global.ORANGEYELLOW));
        }
        return null;
    }


    public static void setUsername(String usernames){
        username = usernames;
    }
    public static String getUsername(){
        return username;
    }

    public static Munny getMunny(Users user){
        return user.getMunny();
    }
    public static void setMunny(Munny newMunny,Users users){ users.setMunny(newMunny);}


    public static Cat getCat() {
        return cat;
    }
    public static void setCat(Cat cat) {
        Global.cat = cat;
    }

    public static Users getUsers() {
        return users;
    }

    public static void setUsers(Users users) {
        Global.users = users;
    }
}
