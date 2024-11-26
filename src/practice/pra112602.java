package practice;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.math.BigInteger;
import java.util.*;
public class pra112602{
    public static void main(String[] args){
        //获取scanner类的对象这个方法非常好，重点复习
        Scanner scan = new Scanner(System.in);
        int n = scan.nextInt();
        int m = scan.nextInt();
        for (int i = 0; i < m; i++) {
            int t = scan.nextInt();
            if (t==1) {
                int x = scan.nextInt();
                System.out.println((n>>x)%2);
            }else if (t==2) {
                int l = scan.nextInt();
                int r = scan.nextInt();
                int a = (1<<(r+1))-1;
                int b = (1<<l)-1;
                n = (n^(a-b));
                System.out.println(n);
            }else if (t==3) {
                int l = scan.nextInt();
                int r = scan.nextInt();
                int a = (1<<(r+1))-1;
                int b = (1<<l)-1;
                n = (n|(a-b));
                System.out.println(n);
            }else if (t==4) {
                int l = scan.nextInt();
                int r = scan.nextInt();
                int a = (1<<(r+1))-1;
                int b = (1<<l)-1;
                int c = (((1<<30)-1)-(a-b));
                n = n&c;
                System.out.println(n);
            }else if (t==5) {
                System.out.println(n&-n);
            }
        }

    }
}