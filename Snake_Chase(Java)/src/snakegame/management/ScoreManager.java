package snakegame.management;

import java.io.*;

public class ScoreManager {
    private static final String SCORE_FILE = "highscore.txt";

    public static int loadHighScore() {
        try (BufferedReader reader = new BufferedReader(new FileReader(SCORE_FILE))) {
            return Integer.parseInt(reader.readLine());
        } catch (IOException | NumberFormatException e) {
            return 0; // Default high score
        }
    }

    public static void saveHighScore(int score) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(SCORE_FILE))) {
            writer.write(String.valueOf(score));
        } catch (IOException e) {
            System.out.println("Error saving high score: " + e.getMessage());
        }
    }
}
