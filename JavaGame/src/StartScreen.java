import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class StartScreen extends JFrame {

    public StartScreen() {
        initializeComponents();
    }

    private void initializeComponents() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(300, 150);
        setTitle("Pong Game Start Screen");
        setLayout(new GridLayout(3, 1));

        JLabel titleLabel = new JLabel("Pong Game");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 20));

        JButton startButton = new JButton("Start Game");
        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                openPlayerNameForm();
                dispose(); // Close the start screen after starting the game
            }
        });

        add(titleLabel);
        add(startButton);
        add(new JLabel()); // Empty label for spacing

        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void openPlayerNameForm() {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new PlayerNameForm();
            }
        });
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new StartScreen();
            }
        });
    }
}
