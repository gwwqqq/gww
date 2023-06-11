import com.csvreader.CsvReader;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Checking {
    private String exerciseFileName;
    private String practiceFileName;
    private String checkingFileName;

    public Checking(String exerciseFileName, String practiceFileName, String checkingFileName) {
        this.exerciseFileName = exerciseFileName;
        this.practiceFileName = practiceFileName;
        this.checkingFileName = checkingFileName;
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

    public String getCheckingFileName() {
        return checkingFileName;
    }

    public void setCheckingFileName(String checkingFileName) {
        this.checkingFileName = checkingFileName;
    }

    public void check() throws IOException {
        // 读取练习题文件和答案文件
        CsvReader exerciseReader = new CsvReader(exerciseFileName);
        CsvReader practiceReader = new CsvReader(practiceFileName);
        // 创建输出文件
        FileWriter writer = new FileWriter(new File(checkingFileName));
        // 定义数组，保存每个表达式的计算结果
        int[] expectedAnswers = new int[100]; // 假设最多有100个表达式
        int index = 0;
        // 读取练习题文件，计算表达式的值，并保存结果到数组中
        while (exerciseReader.readRecord()) {
            String expression = exerciseReader.get(0);
            String[] parts = expression.split("=");
            if (parts.length == 2) {
                try {
                    int operand1 = Integer.parseInt(parts[0].trim());
                    int operand2 = Integer.parseInt(parts[1].trim());
                    int expectedAnswer = 0;
                    if (expression.contains("+")) {
                        expectedAnswer = operand1 + operand2; // 计算加法表达式的值
                    } else if (expression.contains("-")) {
                        expectedAnswer = operand1 - operand2; // 计算减法表达式的值
                    }
                    expectedAnswers[index++] = expectedAnswer; // 保存结果
                } catch (NumberFormatException e) {
                    // 处理数字格式异常
                    continue; // 跳过当前字符串，继续处理下一个字符串
                }
            } else {
                // 处理字符串格式错误
                writer.write("表达格式错误"+expression+"\n");
            }
        }
        // 读取答案文件，从数组中读取正确的结果进行比较，并将结果写入到输出文件中
        while (practiceReader.readRecord()) {
            String[] parts = practiceReader.get(0).split("=");
            if (parts.length == 2) {
                int actualAnswer = Integer.parseInt(parts[1].trim());
                int expectedAnswer = expectedAnswers[index++]; // 从数组中读取正确的结果
                String result = actualAnswer == expectedAnswer ? "正确" : "错误";
                writer.write(parts[0] + "=" + expectedAnswer + "," + result + "\n");
            } else {
                // 处理字符串格式错误
            }
        }
        // 关闭文件流
        exerciseReader.close();
        practiceReader.close();
        writer.close();
    }
}


