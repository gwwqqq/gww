import java.io.IOException;

public class UnitTest {
    public static void main(String[] args) throws IOException {
        // 生成习题文件
        ExerciseGenerator.main(null);

        // 练习加法习题
        Practice practice = new Practice("exercise_add_50_000.csv", "practice_ae_000.csv");
        practice.practice();

        // 批改加法习题
        Checking checking = new Checking("exercise_add_50_000.csv", "practice_ae_000.csv", "checking_ae_000.csv");
        checking.check();


        // 输出加法习题
        Exercise exercise = new Exercise("exercise_add_50_000.csv");
        exercise.print();

        // 输出加法练习结果
        exercise.setFileName("practice_ae_000.csv");
        exercise.print();

        // 输出加法批改结果
        exercise.setFileName("checking_ae_000.csv");
        exercise.print();
    }
}