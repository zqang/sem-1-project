/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sample;

/**
 *
 * @author HP
 */
public class Chat {

    private int level;
    protected static String question;
    private String meowlanguage;
    private String meowlanguage2;
    private String meaning;

    public Chat(int level, String question, String meowlanguage, String meaning) {
        this.level = 1;
        this.question = question;
        this.meowlanguage = meowlanguage;
        this.meaning = meaning;
    }

    public Chat() {
    }

    public int getLevel() {
        return level;
    }

    public String getQuestion() {
        return question;
    }

    public String getMeowlanguage() {
        return meowlanguage;
    }

    public String getMeaning() {
        return meaning;
    }

    public String getMeowlanguage2() {
        return meowlanguage2;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public void setMeowlanguage(String meowlanguage) {
        this.meowlanguage = meowlanguage;
    }

    public void setMeaning(String meaning) {
        this.meaning = meaning;
    }

    public void setMeowlanguage2(String meowlanguage2) {
        this.meowlanguage2 = meowlanguage2;
    }

}
