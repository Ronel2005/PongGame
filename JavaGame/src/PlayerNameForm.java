import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PlayerNameForm extends JFrame {

    private JTextField player1TextField;
    private JTextField player2TextField;

    public PlayerNameForm() {
        initializeComponents();
    }

    private void initializeComponents() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(300, 150);
        setTitle("Enter Player Names");
        setLayout(new GridLayout(3, 2));

        JLabel player1Label = new JLabel("Player 1 Name:");
        player1TextField = new JTextField();

        JLabel player2Label = new JLabel("Player 2 Name:");
        player2TextField = new JTextField();

        JButton submitButton = new JButton("Submit");
        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String player1Name = player1TextField.getText();
                String player2Name = player2TextField.getText();

                if (!player1Name.isEmpty() && !player2Name.isEmpty()) {
                    startPongGame(player1Name, player2Name);
                    dispose(); // Close the form after submission
                } else {
                    JOptionPane.showMessageDialog(PlayerNameForm.this,
                            "Please enter names for both players.",
                            "Error",
                            JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        add(player1Label);
        add(player1TextField);
        add(player2Label);
        add(player2TextField);
        add(new JLabel()); // Empty label for spacing
        add(submitButton);

        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void startPongGame(String player1Name, String player2Name) {
        Form form = new Form(player1Name, player2Name);
        GameFrame frame = new GameFrame();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new PlayerNameForm();
            }
        });
    }
}
