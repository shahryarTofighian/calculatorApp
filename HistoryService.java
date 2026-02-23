package org.example;

import java.util.ArrayList;

public class HistoryService {
    private final ArrayList<String> history = new ArrayList<>();

    public void add(String record) {
        history.add(record);
    }

    public void print() {
        if (history.isEmpty()) {
            System.out.println("تاریخچه خالی است .");
            return;
        }
        System.out.println("----- تاریخچه عملیات -----");
        for (int i = 0; i < history.size(); i++) {
            System.out.println((i + 1) + ") " + history.get(i));
        }
    }

    public void clear() {
        history.clear();
        System.out.println("تاریخچه پاک شد.");
    }
}
