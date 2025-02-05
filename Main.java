import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        ExpenseManager manager = new ExpenseManager();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\nDaily Expense Tracker");
            System.out.println("1. Add Expense");
            System.out.println("2. View Summary");
            System.out.println("3. View All Expenses");
            System.out.println("4. Exit");
            System.out.print("Choose an option: ");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    System.out.print("Enter amount: ₹");
                    double amount = scanner.nextDouble();
                    scanner.nextLine();
                    System.out.print("Enter category: ");
                    String category = scanner.nextLine();
                    System.out.print("Enter description: ");
                    String description = scanner.nextLine();
                    manager.addExpense(amount, category, description);
                    System.out.println("Expense added!");
                    break;
                case 2:
                    manager.viewSummaries();
                    break;
                case 3:
                    manager.viewAllExpenses();
                    break;
                case 4:
                    System.out.println("Goodbye!");
                    scanner.close();
                    return;
                default:
                    System.out.println("Invalid choice, try again.");
            }
        }
    }
}
