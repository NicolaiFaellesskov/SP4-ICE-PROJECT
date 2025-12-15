public class User {
    private int id;
    private String name;
    private String email;
    private String password;
    private int saldo;

    public User(int id, String name, String email, String password, int saldo) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
        this.saldo = saldo;
    }

    public int getId() {
        return id;
    }

    public int getSaldo() {
        return saldo;
    }

    public void setSaldo(int saldo) {
        this.saldo = saldo;
    }

    public String getUsername() {
        return name;
    }

    public String toString (){
        return "name: " + name + " email: " + email;
    }
}
