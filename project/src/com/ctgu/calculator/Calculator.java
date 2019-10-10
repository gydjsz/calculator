package com.ctgu.calculator;


import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Stack;

public class Calculator {
    private List<Integer> num;
    private List<String> symbol;

    /**
     * 生成表达式
     */
    public void createFormula(){
        num = new ArrayList<>();
        symbol = new ArrayList<>();
        Random r = new Random();
        int len = r.nextInt(3);
        num.add(r.nextInt(101));
        for(int i = 0; i <= len; ++i){
            switch (r.nextInt(4)){
                case 0:
                    symbol.add("+");
                    break;
                case 1:
                    symbol.add("-");
                    break;
                case 2:
                    symbol.add("*");
                    break;
                default:
                    symbol.add("/");
            }
            num.add(r.nextInt(101));
        }
    }

    /**
     *判断优先级
     */
    public int isFirst(String s){
        switch(s){
            case "+":
            case "-":
                return 1;
            case "*":
            case "/":
                return 2;
            default:
                return 0;
        }
    }

    /**
     * 求值
     * @return a ? b
     */
    public int calSymbol(int a, int b, String s){
        switch(s){
            case "+":
                return a + b;
            case "-":
                return a - b;
            case "*":
                return a * b;
            case "/":
                return a / b;
            default:
                return 0;
        }
    }

    public void getResult(){
        Stack<Integer> sn = new Stack<>();
        Stack<String> ss = new Stack<>();

        int a, b, c;
        for(int i = 0; i < num.size() + symbol.size(); ++i){

        }
    }

}
