package com.akshaya.expensetracker.service;

import com.akshaya.expensetracker.exception.InvalidExpenseException;
import com.akshaya.expensetracker.model.Expense;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ExpenseService {
    private List<Expense> expenses = new ArrayList<>();
    private int expenseId = 1;

    public void addExpense(String title, String category, double amount) throws InvalidExpenseException {
        if (amount <= 0) {
            throw new InvalidExpenseException("Amount must be greater than zero.");
        }
        Expense expense = new Expense(expenseId++, title, category, amount);
        expenses.add(expense);
        System.out.println("Expense Added Successfully.");
    }

    public void displayExpenses() {
        if (expenses.isEmpty()) {
            System.out.println("No expenses available.");
            return;
        }
        System.out.println("\n===== ALL EXPENSES =====");
        for (Expense expense : expenses) {
            System.out.println(expense);
        }
    }

    public void deleteExpense(int id) {
        Iterator<Expense> iterator = expenses.iterator();
        while (iterator.hasNext()) {
            Expense expense = iterator.next();
            if (expense.getId() == id) {
                iterator.remove();
                System.out.println("Expense Deleted Successfully.");
                return;
            }
        }
        System.out.println("Expense ID Not Found.");
    }

    public void updateExpense(int id, String newTitle, String newCategory, double newAmount) {
        for (int i = 0; i < expenses.size(); i++) {
            Expense expense = expenses.get(i);
            if (expense.getId() == id) {
                Expense updatedExpense = new Expense(id, newTitle, newCategory, newAmount);
                expenses.set(i, updatedExpense);
                System.out.println("Expense Updated Successfully.");
                return;
            }
        }
        System.out.println("Expense ID Not Found.");
    }

    public void calculateTotalExpenses() {
        double total = 0;
        for (Expense expense : expenses) {
            total += expense.getAmount();
        }
        System.out.println("\nTotal Expenses: ₹" + total);
    }

    public void filterByCategory(String category) {
        boolean found = false;
        System.out.println("\n===== FILTERED EXPENSES =====");
        for (Expense expense : expenses) {
            if (expense.getCategory().equalsIgnoreCase(category)) {
                System.out.println(expense);
                found = true;
            }
        }
        if (!found) {
            System.out.println("No expenses found in this category.");
        }
    }
}