import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;

public class ExerciseGenerator {
    private static final String[] OPERATORS = {"+", "-"};
    private static final int[] NUMBERS = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31, 32, 33, 34, 35, 36, 37, 38, 39, 40, 41, 42, 43, 44, 45, 46, 47, 48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 58, 59, 60, 61, 62, 63, 64, 65, 66, 67, 68, 69, 70, 71, 72, 73, 74, 75, 76, 77, 78, 79, 80, 81, 82, 83, 84, 85, 86, 87, 88, 89, 90, 91, 92, 93, 94, 95, 96, 97, 98, 99, 100};
    private static final int[] COUNTS = {50, 60, 70, 80, 90, 100};
    private static final int MAX_SERIAL_NUMBER = 999;
    private static final String EXERCISE_FILE_PREFIX = "exercise_";
    private static final String PRACTICE_FILE_PREFIX = "practice_";
    private static final String CHECKING_FILE_PREFIX = "checking_";
    private static final String FILE_TYPE = ".csv";

    public static void main(String[] args) throws IOException {
        generateExerciseFiles();
    }

    private static void generateExerciseFiles() throws IOException {
        for (int count : COUNTS) {
            generateAdditionExerciseFile(count);
            generateSubtractionExerciseFile(count);
            generateMixedExerciseFile(count);
        }
    }

    private static void generateAdditionExerciseFile(int count) throws IOException {
        String fileName = EXERCISE_FILE_PREFIX + "add_" + count + "_";
        generateExerciseFile(fileName, count, "+");
    }

    private static void generateSubtractionExerciseFile(int count) throws IOException {
        String fileName = EXERCISE_FILE_PREFIX + "sub_" + count + "_";
        generateExerciseFile(fileName, count, "-");
    }

    private static void generateMixedExerciseFile(int count) throws IOException {
        String fileName = EXERCISE_FILE_PREFIX + "mix_" + count + "_";
        generateExerciseFile(fileName, count, null);
    }

    private static void generateExerciseFile(String fileNamePrefix, int count, String operator) throws IOException {
        Random random = new Random();
        int serialNumber = 0;
        while (serialNumber <= MAX_SERIAL_NUMBER) {
            String fileName = fileNamePrefix + String.format("%03d", serialNumber) + FILE_TYPE;
            File file = new File(fileName);
            FileWriter writer = new FileWriter(file);
            for (int i = 0; i < count; i++) {
                int a = random.nextInt(101);
                int b = random.nextInt(101);
                if (operator == null) {
                    operator = OPERATORS[random.nextInt(OPERATORS.length)];
                }
                if (operator.equals("+")) {
                    if (a + b > 100) {
                        i--;
                        continue;
                    }
                } else {
                    if (a - b < 0) {
                        i--;
                        continue;
                    }
                }
                String expression = a + " " + operator + " " + b + " = ";
                writer.write(expression + "\n");
            }
            writer.close();
            serialNumber++;
        }
    }

}

