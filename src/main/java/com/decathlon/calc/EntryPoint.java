package com.decathlon.calc;

import com.decathlon.calc.UI.DecathlonConsoleUI;

public class EntryPoint {
    public static void main(String[] args) throws Exception {
        DecathlonConsoleUI consoleUI = new DecathlonConsoleUI(args);
        consoleUI.startUI();
    }

}
