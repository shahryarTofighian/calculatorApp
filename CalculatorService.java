package org.example;

import java.util.ArrayList;


public class CalculatorService {

    // عملیات ساده (دو عدد)
    public double add(double a, double b) { return a + b; }
    public double sub(double a, double b) { return a - b; }
    public double mul(double a, double b) { return a * b; }

    public double div(double a, double b) throws InvalidOperationException {
        if (b == 0) throw new InvalidOperationException("خطا: تقسیم بر صفر مجاز نیست.");
        return a / b;
    }

    public double mod(double a, double b) throws InvalidOperationException {
        if (b == 0) throw new InvalidOperationException("خطا: باقیمانده بر صفر مجاز نیست.");
        return a % b;
    }

    // عملیات پیشرفته (لیست اعداد  Overloading
    public double sum(ArrayList<Double> nums) throws InvalidOperationException {
        validateList(nums);
        double s = 0;
        for (double x : nums) s += x;
        return s;
    }

    public double average(ArrayList<Double> nums) throws InvalidOperationException {
        validateList(nums);
        return sum(nums) / nums.size();
    }

    public double max(ArrayList<Double> nums) throws InvalidOperationException {
        validateList(nums);
        double m = nums.get(0);
        for (double x : nums) if (x > m) m = x;
        return m;
    }

    public double min(ArrayList<Double> nums) throws InvalidOperationException {
        validateList(nums);
        double m = nums.get(0);
        for (double x : nums) if (x < m) m = x;
        return m;
    }

    private void validateList(ArrayList<Double> nums) throws InvalidOperationException {
        if (nums == null || nums.isEmpty()) {
            throw new InvalidOperationException("خطا: تعداد اعداد باید بیشتر از صفر باشد.");
        }
    }
}
