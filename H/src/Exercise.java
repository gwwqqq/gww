import com.csvreader.CsvReader;

import java.io.File;
import java.io.IOException;

public class Exercise {
    private String fileName;

    public Exercise(String fileName) {
        this.fileName = fileName;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public void print() throws IOException {
        CsvReader reader = new CsvReader(String.valueOf(new File(fileName)));
        while (reader.readRecord()) {
            System.out.println(reader.getRawRecord());
        }
        reader.close();
    }
}
