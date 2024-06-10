import java.io.*;
import java.nio.file.*;
import java.util.Scanner;

public class FileStatistics {

    private File file;
    private int charCount;
    private int wordCount;

    public FileStatistics(File file) {
        this.file = file;
    }

    public boolean isFile() {
        return file.isFile();
    }

    public boolean isDirectory() {
        return file.isDirectory();
    }

    public boolean isTextFile() {
        return file.getName().toLowerCase().endsWith(".txt");
    }

    public void analyzeFile() throws IOException {
        charCount = 0;
        wordCount = 0;

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                charCount += countEnglishCharacters(line);
                wordCount += countWords(line);
            }
        }
    }

    private int countEnglishCharacters(String line) {
        int count = 0;
        for (char c : line.toCharArray()) {
            if (Character.isLetter(c)) {
                count++;
            }
        }
        return count;
    }

    private int countWords(String line) {
        String[] words = line.split("\\s+");
        return words.length;
    }

    public void outputResults(PrintStream out) {
        out.println("Character count: " + charCount);
        out.println("Word count: " + wordCount);
    }

    public void outputResultsToFile(File outputFile) throws IOException {
        try (PrintWriter writer = new PrintWriter(new FileWriter(outputFile))) {
            writer.println("Character count: " + charCount);
            writer.println("Word count: " + wordCount);
        }
    }
}

