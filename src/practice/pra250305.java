package practice;

import java.util.*;
import java.util.ArrayList;

public class pra250305 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int P = in.nextInt();
        int level = 0;
        HashMap<Integer, List<Integer[]>> map = new HashMap<>();
        Queue<Integer[]> q = new PriorityQueue<>((a,b)->{
            return a[2]-b[2];
        });

        for (int i = 1; i <= n; i++) {//存储
            int f = in.nextInt();
            int s = in.nextInt();
            int k = in.nextInt();
            if (!map.containsKey(f)) {
                map.put(f, new ArrayList<>());//以每个关卡的前置关卡号码为键
            }

            map.get(f).add(new Integer[]{i,s,k});
        }
        for (Integer[] t : map.get(0)) {
            q.offer(t);
        }
        map.remove(0);
        while (q.peek()[2] <= P) {//只要满足闯关要求就一直遍历
            Integer[] temp = q.poll();//取出经验值要求最小的关卡
            level++;
            P += temp[1];
            if (map.containsKey(temp[0])) {//如果map中包含 以temp[0]作为前置号码的关卡，则把当前temp对应的子关卡加入队列中
                for(Integer []t:map.get(temp[0])){//把子关卡全部加入队列之中
                    q.offer(t);
                }
                map.remove(temp[0]);
                //添加到队列当中后，清空map中 以t[0]为键的键值对
            }
        }
        System.out.println(level);
        in.close();
    }
}