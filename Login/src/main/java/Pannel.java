import java.util.Scanner;

public class Pannel {

    private DBManager dbManager;

    public Pannel(Scanner sc, DBManager dbManager) {
        this.dbManager = dbManager;
        int value = signInUpMessage(sc);

        if (value == 1) {
            signUp(sc);
        } else if (value == 2) {
            signIn(sc);
        } else {
            System.out.println("Invalid input");
            System.out.println("Try later.");
        }

    }

    public static int signInUpMessage(Scanner sc) {
        System.out.println("-----------------Login-----------------");
        System.out.println("1.Sign Up");
        System.out.println("2.Sign In");
        System.out.println("Enter: ");
        return Integer.parseInt(sc.nextLine());
    }

    public void signUp(Scanner sc) {
        System.out.println("Choose a user name: ");
        String userName = sc.nextLine();

        System.out.println("Choose a password: ");
        String passWord = sc.nextLine();

        dbManager.saveToDB(userName,passWord);


    }


    public void signIn(Scanner sc) {
        System.out.println("Enter your user name: ");
        String userName = sc.nextLine();

        System.out.println("Enter your pass word: ");
        String passWord = sc.nextLine();

        dbManager.identityCheck(userName, passWord);
    }
}
