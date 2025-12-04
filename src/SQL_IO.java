import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class SQL_IO {
    private String url;
    List<User> users = new ArrayList<>();

    public SQL_IO(String url) {
        this.url = url;
    }

    public void readUser() {
        try (Connection conn = DriverManager.getConnection(url)) {

            String sql = "SELECT id, username, email, password FROM users";
            PreparedStatement statement = conn.prepareStatement(sql);

            ResultSet result = statement.executeQuery();


            while (result.next()) {
                int id = result.getInt("id");
                String name = result.getString("username");
                String email = result.getString("email");
                String password = result.getString("password");

                users.add(new User(id, name, email,password));
            }

        } catch (
                SQLException e) {
            e.printStackTrace();
        }

        // Print Java objects
        users.forEach(System.out::println);
    }
    public void makeUser(String name, String email,String password){

    }
}


