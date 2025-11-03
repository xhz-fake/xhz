package Practice;
import java.util.*;

class Customer {
    int arriveTime;
    int processTime;
    int isVIP;
    int waitTime;
    int finishTime;

    public Customer(int arriveTime, int processTime, int isVIP) {
        this.arriveTime = arriveTime;
        this.processTime = processTime;
        this.isVIP = isVIP;
        this.waitTime = 0;
        this.finishTime = 0;
    }
}

public class pra251015_3 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int N = scanner.nextInt();

        // 读取顾客信息
        Customer[] customers = new Customer[N];
        for (int i = 0; i < N; i++) {
            int arriveTime = scanner.nextInt();
            int processTime = scanner.nextInt();
            int isVIP = scanner.nextInt();
            customers[i] = new Customer(arriveTime, processTime, isVIP);
        }

        int K = scanner.nextInt();  // 窗口数
        int vipWindow = scanner.nextInt();  // VIP窗口编号

        // 初始化窗口状态
        int[] windowEndTime = new int[K];  // 窗口完成当前服务的时间
        int[] windowServeCount = new int[K];  // 各窗口服务人数
        Arrays.fill(windowEndTime, 0);

        // 等待队列
        Queue<Integer> waitQueue = new LinkedList<>();
        int currentTime = 0;
        int servedCount = 0;
        int totalWaitTime = 0;
        int maxWaitTime = 0;
        int lastFinishTime = 0;

        // 模拟过程
        while (servedCount < N) {
            // 将到达时间小于等于当前时间的顾客加入等待队列
            for (int i = 0; i < N; i++) {
                if (customers[i].arriveTime <= currentTime && customers[i].finishTime == 0) {
                    waitQueue.offer(i);
                }
            }

            // 处理VIP客户优先规则
            boolean vipServed = false;
            if (!waitQueue.isEmpty()) {
                // 检查是否有VIP客户在等待且VIP窗口空闲
                Iterator<Integer> iterator = waitQueue.iterator();
                while (iterator.hasNext()) {
                    int customerIndex = iterator.next();
                    Customer customer = customers[customerIndex];
                    if (customer.isVIP == 1 && windowEndTime[vipWindow] <= currentTime) {
                        // VIP客户选择VIP窗口
                        int waitTime = currentTime - customer.arriveTime;
                        customer.waitTime = waitTime;
                        customer.finishTime = currentTime + customer.processTime;
                        totalWaitTime += waitTime;
                        maxWaitTime = Math.max(maxWaitTime, waitTime);
                        lastFinishTime = Math.max(lastFinishTime, customer.finishTime);
                        windowEndTime[vipWindow] = customer.finishTime;
                        windowServeCount[vipWindow]++;
                        servedCount++;
                        iterator.remove();
                        vipServed = true;
                        break;
                    }
                }
            }

            // 为等待队列中的顾客分配窗口
            if (!vipServed && !waitQueue.isEmpty()) {
                Iterator<Integer> iterator = waitQueue.iterator();
                while (iterator.hasNext()) {
                    int customerIndex = iterator.next();
                    Customer customer = customers[customerIndex];

                    // 寻找最早空闲的窗口
                    int minEndTime = Integer.MAX_VALUE;
                    int selectedWindow = -1;
                    for (int i = 0; i < K; i++) {
                        if (windowEndTime[i] < minEndTime) {
                            minEndTime = windowEndTime[i];
                            selectedWindow = i;
                        }
                    }

                    // 如果找到可用窗口且当前时间 >= 窗口空闲时间
                    if (selectedWindow != -1 && currentTime >= minEndTime) {
                        int serviceStartTime = Math.max(customer.arriveTime, minEndTime);
                        int waitTime = serviceStartTime - customer.arriveTime;
                        customer.waitTime = waitTime;
                        customer.finishTime = serviceStartTime + customer.processTime;
                        totalWaitTime += waitTime;
                        maxWaitTime = Math.max(maxWaitTime, waitTime);
                        lastFinishTime = Math.max(lastFinishTime, customer.finishTime);
                        windowEndTime[selectedWindow] = customer.finishTime;
                        windowServeCount[selectedWindow]++;
                        servedCount++;
                        iterator.remove();
                        break;
                    }
                }
            }

            currentTime++;

            // 防止无限循环
            if (currentTime > 100000) break;
        }

        // 输出结果
        double averageWaitTime = Math.round(totalWaitTime * 10.0 / N) / 10.0;
        System.out.printf("%.1f %d %d\n", averageWaitTime, maxWaitTime, lastFinishTime);

        // 输出各窗口服务人数
        for (int i = 0; i < K; i++) {
            if (i > 0) System.out.print(" ");
            System.out.print(windowServeCount[i]);
        }
        System.out.println();
    }
}