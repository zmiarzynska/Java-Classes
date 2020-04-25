import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;

public class Reader {
 protected FileReader fromFile;
 protected BufferedReader buffer;

    public FileReader fromFile(String fileName)  throws FileNotFoundException {
        fromFile = new FileReader(fileName);
        return fromFile;
    }

 public BufferedReader toBufferedReader(FileReader fr) {
        buffer= new BufferedReader(fr);
        return buffer;
 }
}
