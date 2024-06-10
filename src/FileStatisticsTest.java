import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class FileStatisticsTest {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("请输入文件路径：");
        String path = scanner.nextLine();
        File file = new File(path);

        FileStatistics stats = new FileStatistics(file);

        if (stats.isDirectory()) {
            System.out.println("这是一个目录。");
        } else if (stats.isFile()) {
            if (stats.isTextFile()) {
                try {
                    stats.analyzeFile();

                    System.out.println("请选择输出方式：");
                    System.out.println("1. 输出到屏幕");
                    System.out.println("2. 输出到文件");
                    int choice = scanner.nextInt();

                    if (choice == 1) {
                        stats.outputResults(System.out);
                    } else if (choice == 2) {
                        System.out.print("请输入输出文件路径：");
                        scanner.nextLine();
                        String outputPath = scanner.nextLine();
                        File outputFile = new File(outputPath);
                        stats.outputResultsToFile(outputFile);
                        System.out.println("统计结果已输出到文件：" + outputPath);
                    } else {
                        System.out.println("无效的选择。");
                    }

                } catch (IOException e) {
                    System.out.println("文件处理出错：" + e.getMessage());
                }
            } else {
                System.out.println("这是一个文件，但不是文本文件（扩展名不是txt）。");
            }
        } else {
            System.out.println("路径无效。");
        }

        scanner.close();
    }
}
