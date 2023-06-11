import com.csvreader.CsvReader;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

class Practice {
    private String exerciseFileName;
    private String practiceFileName;

    public Practice(String exerciseFileName, String practiceFileName) {
        this.exerciseFileName = exerciseFileName;
        this.practiceFileName = practiceFileName;
    }

    public String getExerciseFileName() {
        return exerciseFileName;
    }

    public void setExerciseFileName(String exerciseFileName) {
        this.exerciseFileName = exerciseFileName;
    }

    public String getPracticeFileName() {
        return practiceFileName;
    }

    public void setPracticeFileName(String practiceFileName) {
        this.practiceFileName = practiceFileName;
    }

    public void practice() throws IOException {
        CsvReader reader = new CsvReader(String.valueOf(new File(exerciseFileName)));
        FileWriter writer = new FileWriter(new File(practiceFileName));
        while (reader.readRecord()) {
            String expression = reader.get(0);
            System.out.print(expression);
            Scanner scanner = new Scanner(System.in);
            int answer = scanner.nextInt();
            writer.write(expression + answer + "\n");
        }
        reader.close();
        writer.close();
    }
}
