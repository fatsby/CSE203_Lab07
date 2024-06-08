
import java.io.IOException;
import java.io.Serializable;
import java.lang.reflect.Array;
import java.util.*;

public class Store implements Serializable {
    private static Store instance;
    private ArrayList<CD> cdlist;
    public Store() {
        cdlist = new ArrayList<CD>();
        if (FileUtils.fileExists("C:\\CDStore","CD.eiu")){
            cdlist.addAll(FileUtils.read("C:\\CDStore","CD.eiu"));
        }
    }
    public ArrayList<CD> getCDlist() {
        return cdlist;
    }
    public void setCDlist(ArrayList<CD> cdlist) {
        this.cdlist = cdlist;
    }
    public static Store getInstance() {
        if (instance == null) {
            instance = new Store();
        }
        return instance;
    }

    public void addCD(CD cd){
        cdlist.add(cd);
        FileUtils.write("C:\\CDStore", "CD.eiu", cdlist);
    }
    public CD findTitle(String title){
        for (CD cd : cdlist){
            if (cd.getTitle().equals(title)){
                return cd;
            }
        }
        return null;
    }
    public ArrayList<CD> containsTitle(String title){
        String titleUppercase = title.toUpperCase();
        ArrayList<CD> similarTitle = new ArrayList<>();
        for (CD cd : cdlist){
            if (cd.getTitle().toUpperCase().contains(titleUppercase)){
                similarTitle.add(cd);
            }
        }
        return similarTitle;
    }
    public ArrayList<CD> searchType(String type){
        ArrayList<CD> similarType = new ArrayList<>();
        for (CD cd : cdlist){
            if (cd.getType().equals(type)){
                similarType.add(cd);
            }
        }
        return similarType;
    }
    public ArrayList<CD> searchCollection(String collection){
        String collectionUppercase = collection.toUpperCase();
        ArrayList<CD> similarCollection = new ArrayList<>();
        for (CD cd : cdlist){
            if (cd.getCollection().toUpperCase().equals(collectionUppercase)){
                similarCollection.add(cd);
            }
        }
        return similarCollection;
    }
    public ArrayList<CD> searchPrice(int price){
        ArrayList<CD> similarPrice = new ArrayList<>();
        for (CD cd : cdlist){
            if (cd.getPrice() <= price){
                similarPrice.add(cd);
            }
        }
        return similarPrice;
    }

    public void deleteByTitle(String title){
        cdlist.remove(findTitle(title));
        FileUtils.write("C:\\CDStore","CD.eiu",cdlist);
    }
}