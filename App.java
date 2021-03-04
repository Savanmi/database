import java.util.Scanner;

public class App {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("login : ");
        String login = scanner.next();
        System.out.print("password : ");
        String password = scanner.next();

        while(!Connector.connect(login, password)) {
            System.out.print("login : ");
            login = scanner.next();
            System.out.print("password : ");
            password = scanner.next();
        }

        System.out.print("Hello world\n\n");

        Connector.disconnect();
    }

}
