import java.io.*;
import java.time.LocalDate;
import java.time.temporal.TemporalAdjusters;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

class ExpenseManager {
    private List<Expense> expenses;
    private final String FILE_NAME = "expenses.txt";

    public ExpenseManager() {
        expenses = new ArrayList<>();
        loadExpenses();
    }

    public void addExpense(double amount, String category, String description) {
        Expense expense = new Expense(amount, category, description, LocalDate.now());
        expenses.add(expense);
        saveExpenses();
    }

    private void saveExpenses() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(FILE_NAME))) {
            oos.writeObject(expenses);
        } catch (IOException e) {
            System.out.println("Error saving expenses: " + e.getMessage());
        }
    }

    private void loadExpenses() {
        File file = new File(FILE_NAME);
        if (file.exists()) {
            try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(FILE_NAME))) {
                expenses = (ArrayList<Expense>) ois.readObject();
            } catch (IOException | ClassNotFoundException e) {
                System.out.println("Error loading expenses.");
            }
        }
    }

    public double getTotalExpenses(LocalDate start, LocalDate end) {
        return expenses.stream()
                .filter(e -> !e.getDate().isBefore(start) && !e.getDate().isAfter(end))
                .mapToDouble(Expense::getAmount)
                .sum();
    }

    public void viewSummaries() {
        LocalDate today = LocalDate.now();
        LocalDate startOfWeek = today.with(TemporalAdjusters.previousOrSame(java.time.DayOfWeek.MONDAY));
        LocalDate startOfMonth = today.with(TemporalAdjusters.firstDayOfMonth());

        System.out.println("\nExpense Summary:");
        System.out.println("Today: ₹" + getTotalExpenses(today, today));
        System.out.println("This Week: ₹" + getTotalExpenses(startOfWeek, today));
        System.out.println("This Month: ₹" + getTotalExpenses(startOfMonth, today));
    }

    public void viewAllExpenses() {
        System.out.println("\nAll Expenses:");
        for (Expense e : expenses) {
            System.out.println(e);
        }
    }
}
