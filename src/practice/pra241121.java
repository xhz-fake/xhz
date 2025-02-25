package practice;

import java.util.*;
import java.io.*;

public class pra241121 {
    static int mod=998244353;
    static int N=2010;
    static int n,m;
    static long ans=0;
    static char a[][]=new char[N][N];
    static long s[][][]=new long[N][N][3];

    public static void main(String[] args) {
        solve();
        out.flush();
    }

    static void solve(){
        n=in.nextInt();
        m=in.nextInt();
        for(int i=1;i<=n;i++){
            String row=in.next();
            for(int j=1;j<=m;j++){
                a[i][j]=row.charAt(j-1);
                if(a[i][j]=='R'){
                    s[i][j][0]++;
                }
                if(a[i][j]=='G'){
                    s[i][j][1]++;
                }
            }
        }

        for(int i=1;i<=n;i++){
            for(int j=1;j<=m;j++){
                s[i][j][0]+=(s[i-1][j][0]+s[i][j-1][0]-s[i-1][j-1][0]);
                s[i][j][0]%=mod;
            }
        }
        for(int i=n;i>=1;i--){
            for(int j=m;j>=1;j--){
                s[i][j][1]+=(s[i+1][j][1]+s[i][j+1][1]-s[i+1][j+1][1]);
                s[i][j][1]%=mod;
            }
        }
        for(int i=1;i<=n;i++){
            for(int j=1;j<=m;j++){
                if(a[i][j]=='O'){
                    ans+=(s[i][j][0]*s[i][j][1]);
                    ans%=mod;
                }
            }
        }
        out.println(ans);
    }

    static FastReader in=new FastReader();
    static PrintWriter out=new PrintWriter(System.out);

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
                st=new StringTokenizer(str);
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