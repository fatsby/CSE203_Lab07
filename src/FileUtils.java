import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class FileUtils {

    public static boolean fileExists(String directoryPath, String fileName) {
        File directory = new File(directoryPath);
        if (!directory.exists()) {
            directory.mkdirs(); // Create the directory if it does not exist
        }
        File file = new File(directoryPath, fileName);
        return file.exists() && !file.isDirectory();
    }

    public static List<CD> read(String directoryPath, String fileName) {
        List<CD> CDs = new ArrayList<>();
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(new File(directoryPath, fileName)))){
            CDs = (ArrayList<CD>) ois.readObject();
        } catch (IOException | ClassNotFoundException e){
            e.printStackTrace();
        }
        return CDs;
    }

    public static void write(String directoryPath, String fileName, List<CD> CDs) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(new File(directoryPath, fileName)))) {
            oos.writeObject(CDs);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}