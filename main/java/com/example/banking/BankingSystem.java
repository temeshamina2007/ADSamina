package com.example.banking;

import java.util.*;

public class BankingSystem {

    static LinkedList<BankAccount> accounts = new LinkedList<>();
    static Stack<String> transactionHistory = new Stack<>();
    static Queue<String> billQueue = new LinkedList<>();
    static Queue<BankAccount> accountRequests = new LinkedList<>();

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        BankAccount[] fixedAccounts = {
                new BankAccount("101", "Ali", 150000),
                new BankAccount("102", "Sara", 220000),
                new BankAccount("103", "Ivan", 50000)
        };

        while (true) {
            System.out.println("\nMAIN MENU");
            System.out.println("1 – Enter Bank (Opening and Transactions)");
            System.out.println("2 – Enter ATM (Balance and Withdraw)");
            System.out.println("3 – Admin Area (Process Queues)");
            System.out.println("4 – Exit");
            int choice = sc.nextInt();

            switch (choice) {
                case 1: bankMenu(sc); break;
                case 2: atmMenu(sc); break;
                case 3: adminMenu(); break;
                case 4: System.exit(0);
                default: System.out.println("Invalid choice");
            }
        }
    }

    static void bankMenu(Scanner sc) {
        System.out.println("\n1. Request Account Opening\n2. Deposit\n3. Withdraw\n4. Undo (Stack)\n5. Pay Bill");
        int op = sc.nextInt();
        sc.nextLine();

        if (op == 1) {
            System.out.print("Enter name: ");
            String name = sc.nextLine();
            accountRequests.add(new BankAccount("TMP" + (accountRequests.size() + 1), name, 0));
            System.out.println("Request submitted to queue.");
        } else if (op == 2 || op == 3) {
            System.out.print("Enter username: ");
            String name = sc.nextLine();
            BankAccount foundAcc = null;
            for (BankAccount acc : accounts) {
                if (acc.username.equalsIgnoreCase(name)) {
                    foundAcc = acc;
                    break;
                }
            }

            if (foundAcc != null) {
                System.out.print("Enter amount: ");
                double amt = sc.nextDouble();
                if (op == 2) {
                    foundAcc.balance += amt;
                    transactionHistory.push("Deposit of " + amt + " to " + name);
                    System.out.println("Success New balance: " + foundAcc.balance);
                } else if (op == 3) {
                    if (foundAcc.balance >= amt) {
                        foundAcc.balance -= amt;
                        transactionHistory.push("Withdrawal of " + amt + " from " + name);
                        System.out.println("Success New balance: " + foundAcc.balance);
                    } else {
                        System.out.println("Insufficient funds");
                    }
                }
            } else {
                System.out.println("Account not found");
            }
        } else if (op == 4) {
            if (!transactionHistory.isEmpty()) {
                System.out.println("Undo" + transactionHistory.pop() + " removed");
            } else {
                System.out.println("History is empty.");
            }
        } else if (op == 5) {
            System.out.print("Bill name: ");
            billQueue.add(sc.nextLine());
            System.out.println("Bill added to queue.");
        }
    }

    static void atmMenu(Scanner sc) {
        sc.nextLine();
        System.out.print("Enter username: ");
        String name = sc.nextLine();
        for (BankAccount acc : accounts) {
            if (acc.username.equalsIgnoreCase(name)) {
                System.out.println("1. Balance enquiry\n2. Withdraw");
                int choice = sc.nextInt();
                if (choice == 1) {
                    System.out.println("Balance: " + acc.balance);
                } else if (choice == 2) {
                    System.out.print("Amount: ");
                    double amt = sc.nextDouble();
                    if (acc.balance >= amt) {
                        acc.balance -= amt;
                        System.out.println("Done. New balance: " + acc.balance);
                    } else {
                        System.out.println("Not enough money.");
                    }
                }
                return;
            }
        }
        System.out.println("Account not found.");
    }

    static void adminMenu() {
        System.out.println("\nADMIN AREA");
        System.out.println("Processing account requests...");
        while (!accountRequests.isEmpty()) {
            BankAccount req = accountRequests.poll();
            accounts.add(req);
            System.out.println("Account created for: " + req.username);
        }
        System.out.println("Processing bills...");
        while (!billQueue.isEmpty()) {
            System.out.println("Paid bill: " + billQueue.poll());
        }
    }
}