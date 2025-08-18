import java.util.Scanner;

public class Momo {
    public static void main(String[] args) {
        String logo = " __  __                       \n"
                + "|  \\/  |                      \n"
                + "| \\  / | ___  _ __ ___   ___  \n"
                + "| |\\/| |/ _ \\| '_ ` _ \\ / _ \\ \n"
                + "| |  | | (_) | | | | | | (_) |\n"
                + "|_|  |_|\\___/|_| |_| |_|\\___/ \n";
        System.out.println("____________________________________________________________");
        System.out.println("Hello I'm\n" + logo);
        System.out.println("What can I do for you?");
        System.out.println("____________________________________________________________\n");
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        while (!input.equals("bye")) {
            System.out.println("____________________________________________________________");
            System.out.println(input);
            System.out.println("____________________________________________________________\n");
            input = scanner.nextLine();
        }
        scanner.close();
        System.out.println("____________________________________________________________");
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println("____________________________________________________________\n");

    }
}