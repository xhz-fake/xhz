package practice;

import java.util.*;
// 1:无需package
// 2: 类名必须Main, 不可修改

public class pra250318_BFS{
    //建立一个表示上下左右的数组
    static int[] dx={-1,1,0,0};
    static int[] dy={0,0,-1,1};
    //起点和终点
    static int startx, starty, endx, endy;
    //标记访问
    static int[][] visited;

    static int N;
    static int M;

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
         N=in.nextInt();
         M=in.nextInt();
        //对访问数组建立一个对等的大小来记录数据是否被访问
        visited=new int[N+1][M+1];
        //建立一个数组储存
        int[][] arr=new int[N+1][M+1];
        for(int i=1;i<=N;i++){
            for(int j=1;j<=M;j++){
                arr[i][j]=in.nextInt();
            }
        }
         startx=in.nextInt();
         starty=in.nextInt();
         endx=in.nextInt();
         endy=in.nextInt();

        System.out.println(bfs(arr,startx,starty));
        in.close();
    }

    static int bfs(int[][] arr,int x,int y){
        //建立一个queue ArrayDeque数组q
        Queue<int[]>q=new ArrayDeque<>();
        //为数组q添加数据   数据为一个数组poll[0]为x [1]为y [2]为steps
        //记录一次后面有迭代一直执行记录每一次的起点知道达到end停止所以停止条件要放在执行语句前面
        q.offer(new int []{x,y,0});
        // 判断里面有数即队列不为空为最后走不出去考虑
        while(!q.isEmpty()){//只要达不到终点就一直向下搜索，搜素不到就返回“-1”
            int[] poll=q.poll();
            int x1=poll[0];
            int y1=poll[1];
            int steps=poll[2];
            //终止条件
            if(x1==endx&&y1==endy){
                return steps;
            }
            //行走路线
            for (int i =0 ; i <4; i++) {
                //结合dx dy看 xx  yy记录的当前位置的前后左右的坐标位置方便后面遍历判断
                int xx=x1+dx[i];
                int yy=y1+dy[i];
                //首先先标记xx,yy不能超出数组范围，然后这个visited   标记是否走过
                // arr是来记录这里没有障碍物可以行走
                if(xx>=1&&yy>=1&&xx<=N&&yy<=M&&visited[xx][yy]==0&&arr[xx][yy]==1){
                    visited[xx][yy]=1;//对于符合以上条件的标记走过
                    q.offer(new int[] {xx,yy,steps+1});
                }
            }
        }
        return -1;
    }

}