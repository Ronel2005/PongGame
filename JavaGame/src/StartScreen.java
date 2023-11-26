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
        setSize(400, 200);
        setTitle("Pong Game");
        setLayout(new BorderLayout());

        JPanel titlePanel = new JPanel();
        JLabel titleLabel = new JLabel("Pong Game");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 30));
        titlePanel.add(titleLabel);

        JPanel buttonPanel = new JPanel();
        JButton startButton = new JButton("Start Game");
        startButton.setFont(new Font("Arial", Font.PLAIN, 16));
        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                openPlayerNameForm();
                dispose(); // Closes the start screen after starting the game
            }
        });
        buttonPanel.add(startButton);

        add(titlePanel, BorderLayout.NORTH);
        add(buttonPanel, BorderLayout.CENTER);

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
