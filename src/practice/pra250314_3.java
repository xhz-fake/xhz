package practice;

import java.util.ArrayList;
import java.util.List;

public class pra250314_3 {

    public static void main(String[] args) {
        List<Integer> list1=new ArrayList<>();
        list1.add(1);list1.add(2);list1.add(3);
        List<Integer>list2= list1.subList(0,1);
        for (Integer integer : list2) {
            System.out.println(integer);
        }
    }
}
