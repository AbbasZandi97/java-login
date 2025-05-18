import java.util.Scanner;

public class Pannel {

    public void signInUpMessage() {
        System.out.println("-----------------Login-----------------");
        System.out.println("1.Sign Up");
        System.out.println("2.Sign In");
    }

    public String[] getUserPass(Scanner sc) {

        System.out.println("Enter user name: ");
        String user = sc.nextLine();
        System.out.println("Enter password: ");
        String pass = sc.nextLine();
        return new String[]{user, pass};
    }
}
