package com.akshaya.expensetracker;

import com.akshaya.expensetracker.exception.InvalidExpenseException;
import com.akshaya.expensetracker.service.ExpenseService;

import java.util.Scanner;

public class App {

    public static void main(String[] args) {
        Scanner scan=new Scanner(System.in);
        ExpenseService service = new ExpenseService();
        while (true) {
            System.out.println("\n===== EXPENSE TRACKER =====");
            System.out.println("1. Add Expense");
            System.out.println("2. View Expenses");
            System.out.println("3. Delete Expense");
            System.out.println("4. Update Expense");
            System.out.println("5. Total Expenses");
            System.out.println("6. Filter By Category");
            System.out.println("7. Exit");
            System.out.print("Choose option: ");

            int choice = scan.nextInt();
            scan.nextLine();
            switch (choice) {
                case 1:
                    try {
                        System.out.print("Enter title: ");
                        String title = scan.nextLine();
                        System.out.print("Enter category: ");
                        String category =scan.nextLine();
                        System.out.print("Enter amount: ");
                        double amount =scan.nextDouble();
                        service.addExpense(title, category, amount);
                    } catch (InvalidExpenseException e) {
                        System.out.println(e.getMessage());
                    } catch (Exception e) {
                        System.out.println("Invalid Input");
                    }
                    break;
                case 2:
                    service.displayExpenses();
                    break;
                case 3:
                    System.out.print("Enter Expense ID to delete: ");
                    int deleteId = scan.nextInt();
                    service.deleteExpense(deleteId);
                    break;
                case 4:
                    System.out.print("Enter Expense ID to update: ");
                    int updateId = scan.nextInt();
                    scan.nextLine();
                    System.out.print("Enter new title: ");
                    String newTitle = scan.nextLine();
                    System.out.print("Enter new category: ");
                    String newCategory = scan.nextLine();
                    System.out.print("Enter new amount: ");
                    double newAmount = scan.nextDouble();
                    service.updateExpense(updateId, newTitle, newCategory, newAmount);
                    break;
                case 5:
                    service.calculateTotalExpenses();
                    break;
                case 6:
                    System.out.print("Enter category to filter: ");
                    String filterCategory =scan.nextLine();
                    service.filterByCategory(filterCategory);
                    break;
                case 7:
                    System.out.println("Exiting...");
                    System.exit(0);
                default:
                    System.out.println("Invalid Choice");
            }
        }
    }
}