package game;

import javax.swing.*;
import java.awt.*;

public class GameFrame extends JFrame {

    private Player currentPlayer;

    private PlayerService playerService;

    private GameLogic gameLogic;

    private JButton[] buttons;

    public GameFrame(Player player) {

        currentPlayer = player;

        playerService = new PlayerService();

        gameLogic = new GameLogic();

        setTitle("Tic Tac Toe");
        setSize(500,500);
        setLocationRelativeTo(null);
        setResizable(false);

        JPanel boardPanel = new JPanel(new GridLayout(3,3)) {

            @Override
            protected void paintComponent(Graphics g) {

                super.paintComponent(g);

                Graphics2D g2 =
                        (Graphics2D) g;

                g2.setColor(Color.BLACK);

                g2.setStroke(
                        new BasicStroke(8));

                int w = getWidth();
                int h = getHeight();

                g2.drawLine(
                        w / 3,
                        0,
                        w / 3,
                        h);

                g2.drawLine(
                        2 * w / 3,
                        0,
                        2 * w / 3,
                        h);

                g2.drawLine(
                        0,
                        h / 3,
                        w,
                        h / 3);

                g2.drawLine(
                        0,
                        2 * h / 3,
                        w,
                        2 * h / 3);
            }
        };

        boardPanel.setBackground(Color.WHITE);

        buttons = new JButton[9];

        for (int i = 0; i < 9; i++) {

            buttons[i] = new JButton("");

            buttons[i].setFont(
                    new Font(
                            "Arial",
                            Font.BOLD,
                            90));

            buttons[i].setBorderPainted(false);

            buttons[i].setContentAreaFilled(false);

            buttons[i].setFocusPainted(false);

            buttons[i].setOpaque(false);

            final int index = i;

            buttons[i].addActionListener(
                    e -> handlePlayerMove(index));

            boardPanel.add(buttons[i]);
        }

        add(boardPanel);

        setVisible(true);
    }

    private void handlePlayerMove(int index) {

        boolean success =
                gameLogic.makeMove(index, 'X');

        if (!success) {
            return;
        }

        buttons[index].setText("X");

        buttons[index].setForeground(
                new Color(220,50,50));

        if (gameLogic.checkWinner('X')) {

            finishGame("WIN");

            return;
        }

        if (gameLogic.isDraw()) {

            finishGame("DRAW");

            return;
        }

        int computerMove =
                gameLogic.computerMove();

        buttons[computerMove].setText("O");

        buttons[computerMove].setForeground(
                new Color(50,100,255));

        if (gameLogic.checkWinner('O')) {

            finishGame("LOSE");

            return;
        }

        if (gameLogic.isDraw()) {

            finishGame("DRAW");
        }
    }

    private void finishGame(String result) {

        playerService.updateStatistics(
                currentPlayer,
                result);

        currentPlayer =
                playerService.getPlayerById(
                        currentPlayer.getId());

        String message;

        if (result.equals("WIN")) {

            message =
                    "Congratulations!\nYou Win!";

        } else if (result.equals("LOSE")) {

            message =
                    "Game Over!\nYou Lose!";

        } else {

            message =
                    "It's a Draw!";
        }

        JOptionPane.showMessageDialog(
                this,
                message,
                "Game Over",
                JOptionPane.INFORMATION_MESSAGE);

        dispose();

        new MainMenuFrame(currentPlayer);
    }
}