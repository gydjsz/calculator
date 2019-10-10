package com.ctgu.calculator;


import java.util.ArrayList;
import java.util.List;
import java.util.Random;

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

}
