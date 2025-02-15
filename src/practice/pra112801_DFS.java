package practice;

import java.util.Scanner;

public class pra112801_DFS {

    public static int solution(int N, int M, char[][] data) {
        // 初始化访问标记数组和可达性数组
        boolean[][] visited = new boolean[N][M];
        boolean[][] reachable = new boolean[N][M];

        // 找到出口并从出口开始遍历
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (data[i][j] == 'O') {
                    dfs(i, j, data, visited, reachable, N, M);
                }
            }
        }

        // 统计危险位置的数量
        int dangerCount = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (!reachable[i][j]) {
                    dangerCount++;
                }
            }
        }

        return dangerCount;
    }

    // 私有方法，用于深度优先搜索
    private static void dfs(int x, int y, char[][] data, boolean[][] visited, boolean[][] reachable, int N, int M) {
        // 如果已经访问过，直接返回
        if (visited[x][y]) {
            return;
        }

        // 标记当前位置为已访问
        visited[x][y] = true;
        reachable[x][y] = true;

        // 获取当前位置的字符
        char ch = data[x][y];

        // 根据字符进行传送或移动
        int newX = x;
        int newY = y;
        switch (ch) {
            case 'U':
                newX--;
                break;
            case 'D':
                newX++;
                break;
            case 'L':
                newY--;
                break;
            case 'R':
                newY++;
                break;
            default:
                // 普通地板，不做处理
                break;
        }

        // 如果传送出界，直接返回
        if (newX < 0 || newX >= N || newY < 0 || newY >= M) {
            return;
        }

        // 继续DFS
        dfs(newX, newY, data, visited, reachable, N, M);

        // 如果传送到的位置可达，则当前位置也可达
        if (reachable[newX][newY]) {
            reachable[x][y] = true;
        }
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int N = in.nextInt();
        int M = in.nextInt();
        char data[][] = new char[N][M];
        in.nextLine(); // 读取换行符
        for (int i = 0; i < N; i++) {
            String line = in.nextLine();
            for (int j = 0; j < M; j++) {
                data[i][j] = line.charAt(j); // 使用 charAt 方法获取字符
            }
        }
        // 调用 solution 方法并输出结果
        System.out.println(solution(N, M, data));
    }
}