import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        try (Scanner sc = new Scanner(System.in)) {
            DBManager dbManager = new DBManager();
            new Pannel(sc, dbManager);
        }

    }
}
