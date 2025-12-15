import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class DatabaseIO {
    private String url;

    public DatabaseIO(String url) {
        this.url = url;
    }

    public User login(String username, String password) throws Exception {
        String consoleFindUser = "SELECT id, username, email, password, saldo FROM users WHERE username = ? AND password = ?";
        try (Connection conn = DriverManager.getConnection(url)) {


            PreparedStatement statement = conn.prepareStatement(consoleFindUser);

            statement.setString(1, username);//Her laver jeg det først ? til username
            statement.setString(2, password);//Her laver jeg det andet ? til password

            ResultSet result = statement.executeQuery();

            if (result.next()) {
                return new User(
                        result.getInt("id"),
                        result.getString("username"),
                        result.getString("email"),
                        result.getString("password"),
                        result.getInt("saldo")
                );
            } else {
                throw new Exception("Wrong username or password");
            }

        }
    }

    public void makeUser(String username, String email, String password) throws Exception {

        String consoleReadUsers = ("SELECT username, email FROM users WHERE username = ? OR email = ?");
        String consoleInsertUser = ("INSERT INTO users (username, email, password) Values (?,?,?)");

        try (Connection conn = DriverManager.getConnection(url)) {

            PreparedStatement statementCheckUser = conn.prepareStatement(consoleReadUsers);

            statementCheckUser.setString(1, username);//Her laver jeg det først ? til username
            statementCheckUser.setString(2, email);//Her laver jeg det andet ? til email

            try (ResultSet result = statementCheckUser.executeQuery()) {
                if (result.next()) {
                    String exsistingName = result.getString("username");
                    String exsistingEmail = result.getString("email");

                    if (exsistingName.equals(username) || exsistingEmail.equals(email)) {
                        throw new Exception("Navn eller email findes allerede");
                    }
                }
            }


            try (PreparedStatement statement = conn.prepareStatement(consoleInsertUser)) {
                statement.setString(1, username);
                statement.setString(2, email);
                statement.setString(3, password);

                statement.executeUpdate();

            }
        } catch (SQLException e) {
            throw new Exception("FEJL");
        }


    }

    public List<Cards> cardReader() throws Exception {
        List<Cards> deck = new ArrayList<>();
        String card = ("SELECT cardName, cardNumber, color, symbols FROM BlackJack");

        try (Connection conn = DriverManager.getConnection(url)) {
            PreparedStatement StatementCardDeck = conn.prepareStatement(card);
            ResultSet resultset = StatementCardDeck.executeQuery();

            while (resultset.next()) {
                String cardName = resultset.getString(1);
                int cardNumber = resultset.getInt(2);
                String color = resultset.getString(3);
                String symbol = resultset.getString(4);
                Cards cards = new Cards(cardName, cardNumber, color, symbol);
                deck.add(cards);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return deck;
    }

    public void addSaldo(int userId, int amount) throws Exception {
        String sql = "UPDATE users SET saldo = saldo + ? WHERE id = ?";

        try (Connection conn = DriverManager.getConnection(url);
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, amount);
            stmt.setInt(2, userId);
            stmt.executeUpdate();

        } catch (SQLException e) {
            throw new Exception("Kunne ikke opdatere saldo", e);
        }
    }


}

