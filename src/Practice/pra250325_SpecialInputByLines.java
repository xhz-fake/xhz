package Practice;

import java.util.ArrayList;
import java.util.Scanner;

public class pra250325_SpecialInputByLines {
    public static void main(String[] args) {
        Scanner in=new Scanner(System.in);
        ArrayList<Integer> list=new ArrayList<>();
        int N=in.nextInt();
        in.nextLine();
        int m=0;
        int n=0;

        while(N-->0){
            String line=in.nextLine();
            String[] splitS=line.split(" ");
            for (String split : splitS) {
                list.add(Integer.parseInt(split));
            }
        }

        list.sort(null);
        for(int i=0;i<list.size()-1;i++) {
            int num=list.get(i);
            int nextNum=list.get(i+1);
            if(num==nextNum) {
                n=num;
            }else {
                if(num+1!=nextNum) {
                    m=num+1;
                }
            }
        }
        System.out.println(m+" "+n);
        in.close();
    }
}
