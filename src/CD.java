
import java.io.Serializable;
import java.util.Scanner;

public class CD implements Serializable {
    private String id;
    private String collection;
    private String type;
    private String title;
    private int price;
    private int releaseYear;

    public CD(String id, String collection, String type, String title, int price, int releaseYear) {
        this.id = id;
        this.collection = collection;
        this.type = type;
        this.title = title;
        this.price = price;
        this.releaseYear = releaseYear;
    }

    public CD(){}

    public void addInfo(){

    }

    public void getInfo(){
    }

    public void editCD() {
    }

    public String getTitle() {
        return title;
    }
    public String getCollection() {
        return collection;
    }
    public String getType(){
        return type;
    }
    public String getId(){
        return id;
    }
    public int getReleaseYear(){
        return releaseYear;
    }
    public int getPrice(){
        return price;
    }
    public void setPrice(int price){
        this.price = price;
    }

}