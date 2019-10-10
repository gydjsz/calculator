package com.ctgu.calculator;

public class CreatePractice {


    public static void main(String[] args) {
        int n = 3;
        Calculator calculator = new Calculator();
        for(int i = 0; i < n; i++){
            calculator.createFormula();
            calculator.getResult();
            calculator.output();
        }
    }
}
