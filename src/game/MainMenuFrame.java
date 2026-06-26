package game;

import javax.swing.*;
import java.awt.*;

        public class MainMenuFrame extends JFrame {

            private Player currentPlayer;

            private JButton btnStartGame;
            private JButton btnStatistics;
            private JButton btnTopScorers;
            private JButton btnExit;

            public MainMenuFrame(Player player) {

                currentPlayer = player;

                setTitle("Main Menu");
                setSize(350,300);
                setDefaultCloseOperation(EXIT_ON_CLOSE);
                setLocationRelativeTo(null);
                setResizable(false);

                JLabel lblWelcome =
                        new JLabel(
                                "Welcome, " +
                                        currentPlayer.getUsername(),
                                SwingConstants.CENTER);

                btnStartGame = new JButton("Start Game");
                btnStatistics = new JButton("My Statistics");
                btnTopScorers = new JButton("Top 5 Scorers");
                btnExit = new JButton("Exit");

                JPanel panel = new JPanel();

                panel.setLayout(new GridLayout(4,1,10,10));
                panel.setBorder(
                        BorderFactory.createEmptyBorder(
                                20,20,20,20));

                panel.add(btnStartGame);
                panel.add(btnStatistics);
                panel.add(btnTopScorers);
                panel.add(btnExit);

                add(lblWelcome, BorderLayout.NORTH);
                add(panel, BorderLayout.CENTER);

                btnStartGame.addActionListener(e -> {

                    new GameFrame(currentPlayer);
                    dispose();

                });

                btnStatistics.addActionListener(e -> {

                    new StatisticsFrame(currentPlayer);
                    dispose();

                });

                btnTopScorers.addActionListener(e -> {

                    new TopScorersFrame(currentPlayer);
                    dispose();

                });

                btnExit.addActionListener(e -> {

                    System.exit(0);

                });

                setVisible(true);
            }
        }