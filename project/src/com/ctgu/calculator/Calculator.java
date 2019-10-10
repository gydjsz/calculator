package com.ctgu.calculator;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Stack;

public class Calculator {
    private List<Integer> num;
    private List<String> symbol;
    private int sum;

    public void output(){
        for(int i = 0; i < num.size() + symbol.size(); ++i){
            if(i % 2 == 0){
                System.out.print(num.get(i / 2));
            }
            else{
                System.out.print(symbol.get((i - 1) / 2));
            }
        }
        System.out.println("=" + sum);
    }

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

    /**
     *
     * @param a 一个整数
     * @return a的所有因数
     */
    public List<Integer> getFactor(int a){
        List<Integer> list = new ArrayList<>();
        for(int i = 2; i <= Math.sqrt(a); ++i){
            if(a % i == 0){
                list.add(i);
            }
        }
        return list;
    }


    /**
     * 计算结果
     */
    public int getResult(){
        Stack<Integer> sn = new Stack<>();
        Stack<String> ss = new Stack<>();
        Random r = new Random();
        int a, b, c;
        String s;
        //记录未计算符号位置
        int flag = 0;
        for(int i = 0; i < num.size() + symbol.size(); ++i){
            //数字和符号各自的下标
            int indexN = i / 2, indexS = (i - 1) / 2;
            if(i % 2 == 0){
                sn.push(num.get(indexN));
                continue;
            }

            /**
             * 如果为乘或者除，则直接计算
             */
            //例如1 * 3 + 2, 此处到1 *
            if(isFirst(symbol.get(indexS)) == 2){
                //sn => 1
                a = sn.pop();
                //indexN + 1就是下一个数，即3
                b = num.get(indexN + 1);
                s = symbol.get(indexS);
                i++;
            }
            //如果为空，例如 1 + 1 + 1
            else if(ss.empty()){
                flag = indexS;
                ss.push(symbol.get(indexS));
                continue;
            }
            //判断到此处，说明ss非空，栈顶为+或-, 当前符号也为+或-, 例如1+2+3, 此时到1 + 2 +
            else{
                //明显sn => 1 2
                b = sn.pop();
                a = sn.pop();
                s = ss.pop();
                ss.push(symbol.get(indexS));
                flag = indexS;
            }

            /**
             * 符号为-， 计算为负数，则变更符号为+
             * 符号为/， 将被除数因数存下，随机取出一个作为因数，变更表达式数；没有因数，变更符号为*
             */
            if(s.equals("-")){
                if(a > b){
                    c = calSymbol(a, b, s);
                }
                else{
                    //减号的优先级较低，所以必定是计算前一个
                    symbol.set(indexS - 1, "+");
                    c = calSymbol(a, b, "+");
                }
            }else if(s.equals("/")){
                List<Integer> list = getFactor(a);
                if(list.isEmpty()){
                    symbol.set(indexS, "*");
                    c = calSymbol(a, b, "*");
                }
                else{
                    //随机将某个因数作为除数，并变更表达式
                    b = list.get(r.nextInt(list.size()));
                    num.set(indexN + 1, b);
                    c = calSymbol(a, b, s);
                }
            }else{
                c = calSymbol(a, b, s);
            }
            sn.push(c);
        }
        //由于表达式遇见乘除直接计算，所以剩余加减，而遇见两个加减连着的时候，计算前面一个，所以最后只剩余一个加减运算
        if(!ss.empty()){
            b = sn.pop();
            a = sn.pop();
            if(ss.peek() == "-"){
                if(a > b){
                    c = calSymbol(a, b, ss.pop());
                }
                else{
                    symbol.set(flag, "+");
                    c = calSymbol(a, b, "+");
                }
            }
            else{
                c = calSymbol(a, b, ss.pop());
            }
            sn.push(c);
        }
        sum = sn.pop();
        return sum;
    }

}
