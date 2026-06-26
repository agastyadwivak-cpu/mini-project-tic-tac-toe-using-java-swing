package game;

import java.util.ArrayList;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class PlayerService {

    public Player login(String username, String password) {

        String sql =
                "SELECT * FROM players WHERE username = ? AND password = ?";

        try {

            Connection conn = DatabaseManager.getConnection();

            PreparedStatement stmt =
                    conn.prepareStatement(sql);

            stmt.setString(1, username);
            stmt.setString(2, password);

            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {

                int id = rs.getInt("id");
                String uname = rs.getString("username");
                int wins = rs.getInt("wins");
                int losses = rs.getInt("losses");
                int draws = rs.getInt("draws");
                int score = rs.getInt("score");

                return new Player(
                        id,
                        uname,
                        wins,
                        losses,
                        draws,
                        score
                );
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    public void updateStatistics(Player player,
                                 String result) {

        int additionalScore = 0;

        String sql = "";

        if (result.equalsIgnoreCase("WIN")) {

            additionalScore = 10;

            sql =
                    "UPDATE players " +
                            "SET wins = wins + 1, " +
                            "score = score + ? " +
                            "WHERE id = ?";

        }
        else if (result.equalsIgnoreCase("LOSE")) {

            sql =
                    "UPDATE players " +
                            "SET losses = losses + 1, " +
                            "score = score + ? " +
                            "WHERE id = ?";
        }
        else {

            additionalScore = 3;

            sql =
                    "UPDATE players " +
                            "SET draws = draws + 1, " +
                            "score = score + ? " +
                            "WHERE id = ?";
        }

        try {

            Connection conn =
                    DatabaseManager.getConnection();

            PreparedStatement stmt =
                    conn.prepareStatement(sql);

            stmt.setInt(1, additionalScore);

            stmt.setInt(2, player.getId());

            stmt.executeUpdate();

        } catch (Exception e) {

            e.printStackTrace();
        }
    }

    public Player getPlayerById(int id) {

        String sql =
                "SELECT * FROM players WHERE id = ?";

        try {

            Connection conn =
                    DatabaseManager.getConnection();

            PreparedStatement stmt =
                    conn.prepareStatement(sql);

            stmt.setInt(1, id);

            ResultSet rs =
                    stmt.executeQuery();

            if (rs.next()) {

                return new Player(
                        rs.getInt("id"),
                        rs.getString("username"),
                        rs.getInt("wins"),
                        rs.getInt("losses"),
                        rs.getInt("draws"),
                        rs.getInt("score")
                );
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    public ArrayList<Player> getTopFiveScorers() {

        ArrayList<Player> players = new ArrayList<>();

        String sql =
                "SELECT * FROM players " +
                        "ORDER BY score DESC, wins DESC " +
                        "LIMIT 5";

        try {

            Connection conn =
                    DatabaseManager.getConnection();

            PreparedStatement stmt =
                    conn.prepareStatement(sql);

            ResultSet rs =
                    stmt.executeQuery();

            while (rs.next()) {

                Player player = new Player(
                        rs.getInt("id"),
                        rs.getString("username"),
                        rs.getInt("wins"),
                        rs.getInt("losses"),
                        rs.getInt("draws"),
                        rs.getInt("score")
                );

                players.add(player);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return players;
    }
    public void resetStatistics(int id) {

        String sql =
                "UPDATE players " +
                        "SET wins = 0, " +
                        "losses = 0, " +
                        "draws = 0, " +
                        "score = 0 " +
                        "WHERE id = ?";

        try {

            Connection conn =
                    DatabaseManager.getConnection();

            PreparedStatement stmt =
                    conn.prepareStatement(sql);

            stmt.setInt(1, id);

            stmt.executeUpdate();

        } catch (Exception e) {

            e.printStackTrace();
        }
    }

}