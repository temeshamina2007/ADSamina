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
            System.out.println("\n--- MAIN MENU ---");
            System.out.println("1 – Enter Bank (Opening & Transactions)");
            System.out.println("2 – Enter ATM (Balance & Withdraw)");
            System.out.println("3 – Admin Area (Process Queues)");
            System.out.println("4 – Exit");
            int choice = sc.nextInt();

            switch (choice) {
                case 1: bankMenu(sc); break;
                case 2: atmMenu(sc); break;
                case 3: adminMenu(); break;
                case 4: System.exit(0);
            }
        }
    }

    static void bankMenu(Scanner sc) {
        System.out.println("1. Request Account Opening\n2. Deposit\n3. Withdraw\n4. Undo Last Action\n5. Pay Bill");
        int op = sc.nextInt();
        sc.nextLine();

        if (op == 1) {
            System.out.print("Enter name: ");
            String name = sc.nextLine();
            accountRequests.add(new BankAccount("TMP" + (accountRequests.size()+1), name, 0));
            System.out.println("Request submitted to queue.");
        } else if (op == 2 || op == 3) {
            System.out.print("Enter username: ");
            String name = sc.nextLine();
            for (BankAccount acc : accounts) {
                if (acc.username.equalsIgnoreCase(name)) {
                    System.out.print("Amount: ");
                    double amt = sc.nextDouble();
                    if (op == 2) {
                        acc.balance += amt;
                        transactionHistory.push("Deposit " + amt + " to " + name);
                    } else {
                        acc.balance -= amt;
                        transactionHistory.push("Withdraw " + amt + " from " + name);
                    }
                    System.out.println("New balance: " + acc.balance);
                    return;
                }
            }
            System.out.println("Account not found!");
        } else if (op == 4) {
            if (!transactionHistory.isEmpty()) {
                System.out.println("Undo → " + transactionHistory.pop() + " removed");
            }
        } else if (op == 5) {
            System.out.print("Bill name (e.g. Internet): ");
            billQueue.add(sc.nextLine());
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
                if (choice == 1) System.out.println("Balance: " + acc.balance);
                else {
                    System.out.print("Amount: ");
                    acc.balance -= sc.nextDouble();
                    System.out.println("Taken. New balance: " + acc.balance);
                }
                return;
            }
        }
        System.out.println("Account not found.");
    }

    static void adminMenu() {
        System.out.println("Processing account requests...");
        while (!accountRequests.isEmpty()) {
            BankAccount req = accountRequests.poll();
            System.out.println("Account created for: " + req.username);
        }
        System.out.println("Processing bills...");
        while (!billQueue.isEmpty()) {
            System.out.println("Paid: " + billQueue.poll());
        }
    }
}