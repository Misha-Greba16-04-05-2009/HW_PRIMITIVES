package org.example;

import java.util.Scanner;

public class TaxAssistant {
    static int earnings = 0;
    static int spendings = 0;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            printMenu();

            String input = scanner.nextLine();
            if ("end".equals(input)) {
                break;
            }

            try {
                int operation = Integer.parseInt(input);
                processOperation(operation, scanner);
            } catch (NumberFormatException e) {
                System.out.println("Введите число от 1 до 3 или 'end' для завершения");
            }
        }

        System.out.println("Программа завершена!");
        scanner.close();
    }

    private static void printMenu() {
        System.out.println("Выберите операцию и введите её номер");
        System.out.println("1. Добавить новый доход");
        System.out.println("2. Добавить новый расход");
        System.out.println("3. Выбрать систему налогообложения");
    }

    private static void processOperation(int operation, Scanner scanner) {
        switch (operation) {
            case 1:
                addEarnings(scanner);
                break;
            case 2:
                addSpendings(scanner);
                break;
            case 3:
                calculateBestTaxSystem();
                break;
            default:
                System.out.println("Такой операции нет");
        }
    }

    private static void addEarnings(Scanner scanner) {
        System.out.println("Введите сумму дохода");
        String moneyStr = scanner.nextLine();
        try {
            int money = Integer.parseInt(moneyStr);
            if (money < 0) {
                System.out.println("Сумма не может быть отрицательной");
                return;
            }
            earnings += money;
            System.out.println("Доход успешно добавлен");
        } catch (NumberFormatException e) {
            System.out.println("Введите целое число");
        }
    }

    private static void addSpendings(Scanner scanner) {
        System.out.println("Введите сумму расхода");
        String moneyStr = scanner.nextLine();
        try {
            int money = Integer.parseInt(moneyStr);
            if (money < 0) {
                System.out.println("Сумма не может быть отрицательной");
                return;
            }
            spendings += money;
            System.out.println("Расход успешно добавлен");
        } catch (NumberFormatException e) {
            System.out.println("Введите целое число");
        }
    }

    private static void calculateBestTaxSystem() {
        int taxEarnings = taxEarnings();
        int taxEarningsMinusSpendings = taxEarningsMinusSpendings();

        System.out.println("\n=== Результаты расчета ===");
        System.out.println("УСН доходы: " + taxEarnings + " рублей");
        System.out.println("УСН доходы минус расходы: " + taxEarningsMinusSpendings + " рублей");
        System.out.println("==========================\n");

        if (taxEarnings < taxEarningsMinusSpendings) {
            int economy = taxEarningsMinusSpendings - taxEarnings;
            System.out.println("Мы советуем вам УСН доходы");
            System.out.println("Ваш налог составит: " + taxEarnings + " рублей");
            System.out.println("Налог на другой системе: " + taxEarningsMinusSpendings + " рублей");
            System.out.println("Экономия: " + economy + " рублей");
        } else if (taxEarnings > taxEarningsMinusSpendings) {
            int economy = taxEarnings - taxEarningsMinusSpendings;
            System.out.println("Мы советуем вам УСН доходы минус расходы");
            System.out.println("Ваш налог составит: " + taxEarningsMinusSpendings + " рублей");
            System.out.println("Налог на другой системе: " + taxEarnings + " рублей");
            System.out.println("Экономия: " + economy + " рублей");
        } else {
            System.out.println("Можете выбрать любую систему налогообложения");
            System.out.println("Налоги на обеих системах равны: " + taxEarnings + " рублей");
        }
    }

    public static int taxEarnings() {
        return earnings * 6 / 100;
    }

    public static int taxEarningsMinusSpendings() {
        int tax = (earnings - spendings) * 15 / 100;
        return Math.max(tax, 0);
    }
}
