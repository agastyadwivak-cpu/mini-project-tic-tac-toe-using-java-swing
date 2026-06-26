package game;

import javax.swing.*;
import java.awt.*;

public class StatisticsFrame extends JFrame {

    public StatisticsFrame(Player player) {

        PlayerService service =
                new PlayerService();

        player =
                service.getPlayerById(
                        player.getId());

        setTitle("My Statistics");
        setSize(300,300);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel();

        panel.setLayout(
                new GridLayout(7,1,10,10));

        panel.setBorder(
                BorderFactory.createEmptyBorder(
                        20,20,20,20));

        panel.add(new JLabel(
                "Username : " +
                        player.getUsername()));

        panel.add(new JLabel(
                "Wins : " +
                        player.getWins()));

        panel.add(new JLabel(
                "Losses : " +
                        player.getLosses()));

        panel.add(new JLabel(
                "Draws : " +
                        player.getDraws()));

        panel.add(new JLabel(
                "Score : " +
                        player.getScore()));

        JButton btnReset =
                new JButton(
                        "Reset Statistics");

        JButton btnBack =
                new JButton("Back");

        Player finalPlayer = player;

        btnReset.addActionListener(e -> {

            int confirm =
                    JOptionPane.showConfirmDialog(
                            this,
                            "Reset statistics?",
                            "Confirm",
                            JOptionPane.YES_NO_OPTION);

            if(confirm ==
                    JOptionPane.YES_OPTION){

                service.resetStatistics(
                        finalPlayer.getId());

                dispose();

                new StatisticsFrame(
                        finalPlayer);
            }
        });

        btnBack.addActionListener(e -> {

            new MainMenuFrame(
                    finalPlayer);

            dispose();
        });

        panel.add(btnReset);
        panel.add(btnBack);

        add(panel);

        setVisible(true);
    }
}