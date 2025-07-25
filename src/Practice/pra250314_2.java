package Practice;

import java.util.*;
// 1:无需package
// 2: 类名必须Main, 不可修改

public class pra250314_2 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int N = in.nextInt();
        int ans;

        HashMap<Integer, List<Integer>> map = new HashMap<>();
        for (int i = 0; i < N; i++) {
            map.put(i, new ArrayList<>());
        }
        int[] nsync=new int[map.size()];
        System.out.println();

        while (true) {
            String ac = in.next();
            if (ac.equals("add")) {
                int n = in.nextInt();
                map.get(0).add(n);
            } else if (ac.equals("sync")) {
                int index = in.nextInt();
                nsync[index]+=1;
                if(map.get(index).size()<map.get(0).size()) {
                    map.put(index, map.get(0).subList(0, nsync[index]));
                }
            } else if (ac.equals("query")) {
                int num;
                ans=0;
                for (int i = 0; i < map.get(0).size(); i++) {
                    num=0;
                    for (int key : map.keySet()) {
                        if(map.get(key).size()>=i+1) {
                            if (map.get(key).get(i) == map.get(0).get(i)) {
                                num++;
                            }
                        }
                    }
                    if (num == map.size()) {
                        ans++;
                    }
                }

                System.out.println(ans);

            }
       }

    }

}