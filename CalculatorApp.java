package org.example;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class CalculatorApp {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        CalculatorService calc = new CalculatorService();
        HistoryService history = new HistoryService();

        while (true) {
            printMainMenu();
            int choice = readIntSafe(sc);

            try {
                switch (choice) {
                    case 1 -> simpleOperations(sc, calc, history);
                    case 2 -> advancedOperations(sc, calc, history);
                    case 3 -> history.print();
                    case 4 -> history.clear();
                    case 0 -> {
                        System.out.println("خروج. موفق باشید!");
                        return;
                    }
                    default -> throw new InvalidOperationException("خطا: گزینه منو نامعتبر است.");
                }
            } catch (InvalidOperationException e) {
                System.out.println(e.getMessage());
            } catch (Exception e) {
                // برای جلوگیری از Crash
                System.out.println("خطای غیرمنتظره: " + e.getMessage());
            }

            System.out.println();
        }
    }

    // ---------- Menus ----------
    private static void printMainMenu() {
        System.out.println("==== Mini Console Calculator ====");
        System.out.println("1) عملیات ساده (دو عدد)");
        System.out.println("2) عملیات پیشرفته (لیست اعداد)");
        System.out.println("3) مشاهده تاریخچه");
        System.out.println("4) پاک کردن تاریخچه");
        System.out.println("0) خروج");
        System.out.print("انتخاب شما: ");
    }

    private static void printSimpleMenu() {
        System.out.println("---- عملیات ساده ----");
        System.out.println("1) جمع (+)");
        System.out.println("2) تفریق (-)");
        System.out.println("3) ضرب (*)");
        System.out.println("4) تقسیم (/)");
        System.out.println("5) باقیمانده (%)");
        System.out.print("انتخاب شما: ");
    }

    private static void printAdvancedMenu() {
        System.out.println("---- عملیات پیشرفته (لیست اعداد) ----");
        System.out.println("1) Sum");
        System.out.println("2) Average");
        System.out.println("3) Max");
        System.out.println("4) Min");
        System.out.print("انتخاب شما: ");
    }

    // ---------- Simple ----------
    private static void simpleOperations(Scanner sc, CalculatorService calc, HistoryService history)
            throws InvalidOperationException {

        printSimpleMenu();
        int op = readIntSafe(sc);

        double a = readDoubleSafe(sc, "عدد اول: ");
        double b = readDoubleSafe(sc, "عدد دوم: ");

        double result;
        String record;

        switch (op) {
            case 1 -> { result = calc.add(a, b); record = a + " + " + b + " = " + result; }
            case 2 -> { result = calc.sub(a, b); record = a + " - " + b + " = " + result; }
            case 3 -> { result = calc.mul(a, b); record = a + " * " + b + " = " + result; }
            case 4 -> { result = calc.div(a, b); record = a + " / " + b + " = " + result; }
            case 5 -> { result = calc.mod(a, b); record = a + " % " + b + " = " + result; }
            default -> throw new InvalidOperationException("خطا: عملیات نامعتبر انتخاب شده است.");
        }

        System.out.println("نتیجه: " + result);
        history.add(record);
    }

    // ---------- Advanced ----------
    private static void advancedOperations(Scanner sc, CalculatorService calc, HistoryService history)
            throws InvalidOperationException {

        printAdvancedMenu();
        int op = readIntSafe(sc);

        int n = readIntSafe(sc, "چند عدد وارد می‌کنید؟ ");
        if (n <= 0) throw new InvalidOperationException("خطا: تعداد اعداد باید بیشتر از صفر باشد.");

        ArrayList<Double> nums = new ArrayList<>();
        for (int i = 1; i <= n; i++) {
            double x = readDoubleSafe(sc, "عدد " + i + ": ");
            nums.add(x);
        }

        double result;
        String name;

        switch (op) {
            case 1 -> { result = calc.sum(nums); name = "Sum"; }
            case 2 -> { result = calc.average(nums); name = "Average"; }
            case 3 -> { result = calc.max(nums); name = "Max"; }
            case 4 -> { result = calc.min(nums); name = "Min"; }
            default -> throw new InvalidOperationException("خطا: عملیات نامعتبر انتخاب شده است.");
        }

        System.out.println("نتیجه: " + result);
        history.add(name + " " + nums + " = " + result);
    }

    // ---------- Safe Input ----------
    private static int readIntSafe(Scanner sc) {
        while (true) {
            try {
                return sc.nextInt();
            } catch (InputMismatchException e) {
                sc.nextLine();
                System.out.print("ورودی نامعتبر! لطفاً یک عدد صحیح وارد کنید: ");
            }
        }
    }

    private static int readIntSafe(Scanner sc, String msg) {
        System.out.print(msg);
        return readIntSafe(sc);
    }

    private static double readDoubleSafe(Scanner sc, String msg) {
        while (true) {
            try {
                System.out.print(msg);
                return sc.nextDouble();
            } catch (InputMismatchException e) {
                sc.nextLine();
                System.out.println("ورودی نامعتبر! لطفاً عدد (double) وارد کنید.");
            }
        }
    }
}
