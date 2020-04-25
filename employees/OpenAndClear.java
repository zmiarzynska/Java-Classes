import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;


public abstract class OpenAndClear {
    protected BufferedReader bufferToLines;
    protected FileReader file;

    public void closing() throws IOException {
        file.close();
        bufferToLines.close();
    }

    public void open(String filename) throws IOException {
        Reader reader= new Reader();
        file=reader.fromFile(filename);
        bufferToLines= reader.toBufferedReader(file);

    }

    public abstract void clear() throws IOException;
}
