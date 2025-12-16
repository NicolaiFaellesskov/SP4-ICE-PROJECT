public class Main {
    public static void main(String[] args) {
        Option option = new Option();
        try {
            option.startMenu();

        } catch (Exception e) {
            System.out.print("noget gik galt" + e.getMessage());

        }
    }
}
