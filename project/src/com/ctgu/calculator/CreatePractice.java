package com.ctgu.calculator;

import java.io.*;

public class CreatePractice {

    public void create(int n) throws IOException {
        String s = "";
        Calculator calculator = new Calculator();
        for(int i = 0; i < n; i++){
            calculator.createFormula();
            calculator.getResult();
            s += calculator.output();
        }
        File f = new File("practice");
        if(!f.exists()){
           f.createNewFile();
        }
        FileWriter fw = new FileWriter("practice");
        BufferedWriter bw = new BufferedWriter(fw);
        bw.write(s);
        bw.close();
        fw.close();
    }

    public static void main(String[] args) throws IOException {
        if(args.length == 1 && args[0].matches("^[0-9]*$")){
            CreatePractice createPractice = new CreatePractice();
            createPractice.create(Integer.valueOf(args[0]));
        }
        else{
            System.out.println("参数错误!");
            System.out.println("请输入参数'n(练习题道数)'");
        }
    }
}
