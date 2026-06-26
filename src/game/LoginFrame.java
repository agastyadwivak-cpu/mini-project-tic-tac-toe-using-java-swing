package game;

import javax.swing.*;
import java.awt.*;

public class LoginFrame extends JFrame {

    private JTextField txtUsername;
    private JPasswordField txtPassword;
    private JButton btnLogin;

    private PlayerService playerService;

    public LoginFrame() {

        playerService = new PlayerService();

        setTitle("Login");
        setSize(350, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(3, 2, 10, 10));

        JLabel lblUsername = new JLabel("Username:");
        JLabel lblPassword = new JLabel("Password:");

        txtUsername = new JTextField();
        txtPassword = new JPasswordField();

        btnLogin = new JButton("Login");

        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        panel.add(lblUsername);
        panel.add(txtUsername);

        panel.add(lblPassword);
        panel.add(txtPassword);

        panel.add(new JLabel());
        panel.add(btnLogin);

        add(panel);

        btnLogin.addActionListener(e -> login());

        setVisible(true);
    }

    private void login() {

        String username = txtUsername.getText();

        String password =
                new String(txtPassword.getPassword());

        Player player =
                playerService.login(username, password);

        if (player != null) {

            JOptionPane.showMessageDialog(
                    this,
                    "Login berhasil!"
            );

            MainMenuFrame menu =
                    new MainMenuFrame(player);

            menu.setVisible(true);

            dispose();

        } else {

            JOptionPane.showMessageDialog(
                    this,
                    "Username atau password salah!"
            );
        }
    }
}