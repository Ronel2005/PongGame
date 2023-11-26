import java.awt.*;
import java.util.prefs.Preferences;
import javax.swing.*;

public class Score extends Rectangle {

    static int GAME_WIDTH;
    static int GAME_HEIGHT;
    int player1;
    int player2;
    int highestScore;

    //using Preferences to store and retrieve the highest score
    private Preferences preferences = Preferences.userNodeForPackage(Score.class);

    Score(int GAME_WIDTH, int GAME_HEIGHT) {
        Score.GAME_WIDTH = GAME_WIDTH;
        Score.GAME_HEIGHT = GAME_HEIGHT;

        // get the highest score from preferences
        highestScore = preferences.getInt("highestScore", 0);
    }

    public void draw(Graphics g) {
        g.setColor(Color.magenta);
        g.setFont(new Font("IMPACT", Font.BOLD, 50));

        // Draw line down the middle of screen
        g.setColor(Color.white);
        g.drawLine(GAME_WIDTH / 2, 0, GAME_WIDTH / 2, GAME_HEIGHT);

        // Display scores on screen
        g.drawString(String.valueOf(player1), (GAME_WIDTH / 2) - 85, 50);
        g.drawString(String.valueOf(player2), (GAME_WIDTH / 2) + 20, 50);

    }

    //update highest score if a new high score is achieved
    public void updateHighestScore() {
        int currentHighScore = Math.max(player1, player2);

        if (currentHighScore > highestScore) {
            highestScore = currentHighScore;
            // Save the new highest score in Preferences
            preferences.putInt("highestScore", highestScore);
     }
   }
}
