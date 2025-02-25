package practice;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class pra241128_2_DFS {

    private static final int[] dx = {-1, 1, 0, 0};
    private static final int[] dy = {0, 0, -1, 1};
    private static final char[] directions = {'U', 'D', 'L', 'R'};

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the number of rows (N): ");
        int N = scanner.nextInt();
        System.out.print("Enter the number of columns (M): ");
        int M = scanner.nextInt();
        scanner.nextLine(); // Consume the leftover newline

        char[][] maze = new char[N][M];

        System.out.println("Enter the maze layout (use . for empty, O for exit, U/D/L/R for portals):");
        for (int i = 0; i < N; i++) {
            maze[i] = scanner.nextLine().toCharArray();
        }

        System.out.println("Dangerous positions count: " + countDangerousPositions(maze, N, M));
        scanner.close();
    }

    public static int countDangerousPositions(char[][] maze, int N, int M) {
        boolean[][] visited = new boolean[N][M];
        Queue<int[]> queue = new LinkedList<>();

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (maze[i][j] == 'O') { // Exit position
                    queue.offer(new int[]{i, j});
                    visited[i][j] = true;
                }
            }
        }

        while (!queue.isEmpty()) {
            int[] cell = queue.poll();
            int x = cell[0], y = cell[1];

            for (int i = 0; i < 4; i++) {
                int nx = x + dx[i], ny = y + dy[i];
                if (isValid(maze, nx, ny) && !visited[nx][ny]) {
                    if (maze[x][y] == directions[i]) {
                        // Process portal
                        int portalX = x, portalY = y;
                        while (maze[portalX][portalY] == directions[i]) {
                            if (i == 0 && portalX == 0 || i == 1 && portalX == N - 1) break;
                            if (i == 2 && portalY == 0 || i == 3 && portalY == M - 1) break;
                            portalX += dx[i];
                            portalY += dy[i];
                        }
                        nx = portalX;
                        ny = portalY;
                    }
                    if (isValid(maze, nx, ny) && !visited[nx][ny]) {
                        visited[nx][ny] = true;
                        queue.offer(new int[]{nx, ny});
                    }
                }
            }
        }

        int count = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (!visited[i][j] && maze[i][j] != 'O') {
                    count++;
                }
            }
        }
        return count;
    }

    private static boolean isValid(char[][] maze, int x, int y) {
        return x >= 0 && x < maze.length && y >=0 && y < maze[0].length;
    }
}