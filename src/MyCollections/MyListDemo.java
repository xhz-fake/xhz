package MyCollections;


public class MyListDemo implements Cloneable{
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
        System.out.println();
        for(int i=0;i<myList.size();i++){
            System.out.print(myList.get(i)+" ");
        }
        System.out.println();
        myList.remove(9);
        for(int i=0;i<myList.size();i++){
            System.out.println(myList.get(i));
        }
        myList.sort();
        System.out.println();
        for(int i=0;i<myList.size();i++){
            System.out.print(myList.get(i)+" ");
        }

        MyList<Float> myList1=new MyList<>();
        for(int i=20;i>0;i--){
            myList1.add((float) (i+0.1));
        }
        myList1.sort();
        System.out.println();
        for(int i=0;i<myList1.size();i++){
            System.out.print(myList1.get(i)+" ");
        }
        System.out.println();

        MyList<Double> myList2=new MyList<>();
        for(int i=20;i>0;i--){
            myList2.add (i+0.0000001);
        }
        myList2.sort();
        System.out.println();
        for(int i=0;i<myList2.size();i++){
            System.out.print(myList2.get(i)+" ");
        }

        System.out.println(myList);
        Person[] t1={
                new Person("小明",123),
                new Person("鸽哥",1234)
        };
        Person[] t2=MyList.deepCopy(t1);
        System.out.println(t2[0].getId()+t2[0].getName()+t2[1].getId()+t2[1].getName());

    }

    @Override
    public MyListDemo clone() {
        try {
            return (MyListDemo) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new AssertionError();
        }
    }
}
