package practice;
import java.util.*;
import java.io.*;

public class pra241119 {

    public static void main(String[] args) {
        solve();
        out.flush();
    }

    static void solve(){
        int N=1010,M=1010;
        int n=in.nextInt(), m=in.nextInt(), q=in.nextInt();
        int a[][]=new int[N][M];
        int s[][]=new int[N][M];
        for(int i=1;i<=n;i++){
            for(int j=1;j<=m;j++){
                a[i][j]=in.nextInt();
                s[i][j]=a[i][j]+s[i-1][j]+s[i][j-1]-s[i-1][j-1];
            }
        }
        while(q-->0){
            int x1=in.nextInt(),y1=in.nextInt(),x2=in.nextInt(),y2=in.nextInt();
            out.println(s[x2][y2]+s[x1-1][y1-1]-s[x2][y1-1]-s[x1-1][y2]);
        }
    }

    static FastReader in=new FastReader();
    static PrintWriter out =new PrintWriter(System.out);

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