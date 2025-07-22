package MyCollections;


public class MyListDemo {
    public static void main(String[] args) {
        MyList<Integer> myList=new MyList<>();
        for(int i=20;i>0;i--){
            myList.add(i);
        }
        int b=myList.get(10);
        if(myList.isEmpty()){
            System.out.println("myList是空的");
        }else{
            System.out.println("myList有元素");
        }
        if(myList.contains(3)){
            System.out.println("myList有3");
        }
        myList.remove(9);
        for(int i=0;i<myList.size();i++){
            System.out.println(myList.get(i));
        }
    }

}
