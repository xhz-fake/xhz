package practice;

import java.util.*;
import java.io.*;

public class pra241112_2 {
    static  int N=(int)(2e5+10);
    public static void main(String[] args) {
        solve();
        out.flush();
    }

    static  void  solve(){
        int n=in.nextInt();
        int m=in.nextInt();
        String str=in.next();
        char s[]=str.toCharArray();
        int num[]=new int[N];
        int d[]=new int[N];
        for (int i = 1; i <=n; i++) {
            num[i]=s[i-1]-'a';
            d[i]=num[i]-num[i-1];
        }
        while(m-->0){
            int l=in.nextInt();
            int r=in.nextInt();
            int k=in.nextInt();
            k%=26;
            d[l]+=k;
            d[r+1]-=k;
        }
        for(int i=1;i<=n;i++){
            d[i]+=d[i-1];
            out.print((char)(d[i]%26+'a'));
        }

    }

    static FastReader in=new FastReader();
    static  PrintWriter out=new PrintWriter(System.out);

    static class FastReader{
        static BufferedReader br;
        static StringTokenizer st;

        FastReader(){
            br=new BufferedReader(new InputStreamReader(System.in));
        }
        String next(){
            String str="";
            while(st==null||!st.hasMoreElements()){
                try{
                    str=br.readLine();
                }catch(IOException e){
                    throw new RuntimeException(e);
                }
                st =new StringTokenizer(str);
            }
            return st.nextToken();
        }
        int nextInt(){
            return Integer.parseInt(next());
        }
        long nextLong(){
            return Long.parseLong(next());
        }
        double nextDouble(){
            return Double.parseDouble(next());
        }
    }

}
