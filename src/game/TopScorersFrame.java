package game;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.ArrayList;

public class TopScorersFrame extends JFrame {

    private JTable table;

    public TopScorersFrame(Player currentPlayer) {

        setTitle("Top 5 Scorers");
        setSize(500,350);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        PlayerService service =
                new PlayerService();

        String[] columns = {
                "Username",
                "Wins",
                "Losses",
                "Draws",
                "Score"
        };

        DefaultTableModel model =
                new DefaultTableModel(columns,0);

        ArrayList<Player> players =
                service.getTopFiveScorers();

        for (Player player : players) {

            Object[] row = {
                    player.getUsername(),
                    player.getWins(),
                    player.getLosses(),
                    player.getDraws(),
                    player.getScore()
            };

            model.addRow(row);
        }

        table = new JTable(model);

        add(new JScrollPane(table),
                BorderLayout.CENTER);

        JButton btnBack =
                new JButton("Back");

        btnBack.addActionListener(e -> {

            new MainMenuFrame(currentPlayer);

            dispose();

        });

        JPanel panel = new JPanel();

        panel.add(btnBack);

        add(panel, BorderLayout.SOUTH);

        setVisible(true);
    }
}