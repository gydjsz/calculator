package com.ctgu.calculator;

import java.io.*;

public class CreatePractice {

    public void create(int n) throws IOException {
        String s = "";
        Calculator calculator = new Calculator();
        for(int i = 0; i < n; i++){
            //创建一个表达式
            calculator.createFormula();
            //获得表达式结果
            calculator.getResult();
            //获得整个算术字符串
            s += calculator.output();
        }
        //写入文件
        File f = new File("subject.txt");
        if(!f.exists()){
           f.createNewFile();
        }
        FileWriter fw = new FileWriter("subject.txt");
        BufferedWriter bw = new BufferedWriter(fw);
        bw.write(s);
        bw.close();
        fw.close();
    }

    public static void main(String[] args) throws IOException {
        //一个参数，并判断是否为数字
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
