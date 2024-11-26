package practice;

import java.util.*;
import java.io.*;

public class pra1112 {

    public static void main(String[] args) {
        solve();
        out.flush();
    }

    static void solve(){
        int n=in.nextInt();
        int t=in.nextInt();
        int q=in.nextInt();
        int x=in.nextInt();

        int j;
        int count=0;
        int N=(int)(1e5+10);
        int a[]=new int[N];
        int s[]=new int[N];
        int c[]=new int[N];

        for(int i=1;i<=t;i++){
            int l=in.nextInt();
            int r=in.nextInt();
            a[l]++;
            a[r+1]--;
        }
        for(int i=1;i<=n;i++){
            a[i]+=a[i-1];
        }

        for(int i=1;i<=n;i++){
            if(a[i]>=x){
                a[i]=1;
            }else{
                a[i]=0;
            }
            s[i]=s[i-1]+a[i];
        }


        for(int i=1;i<=q;i++){
            int l=in.nextInt();
            int r=in.nextInt();
            out.println(s[r]-s[l-1]);
        }

    }

    static  FastReader in=new FastReader();
    static PrintWriter out=new PrintWriter(System.out);

    static class FastReader{
        static BufferedReader br;
        static StringTokenizer st;

        FastReader(){
            br=new BufferedReader(new InputStreamReader(System.in));
        }

        String next(){
            String str ="";
            while (st==null || !st.hasMoreElements()){
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