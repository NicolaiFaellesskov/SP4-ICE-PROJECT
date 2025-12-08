import java.sql.*;


public class DatabaseIO {
    private String url;

    public DatabaseIO(String url) {
        this.url = url;
    }

    public User login(String username, String password) throws Exception {
        String consoleFindUser = "SELECT id, username, email, password FROM users WHERE username = ? AND password = ?";
        try (Connection conn = DriverManager.getConnection(url)) {


            PreparedStatement statement = conn.prepareStatement(consoleFindUser);

            statement.setString(1, username);//Her laver jeg det først ? til username
            statement.setString(2, password);//Her laver jeg det andet ? til password

            ResultSet result = statement.executeQuery();

            if (result.next()) {
                int uId = result.getInt("id");
                String uName = result.getString("username");
                String uEmail = result.getString("email");
                String uPassword = result.getString("password");

                return new User(uId, uName, uEmail, uPassword);
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
}




