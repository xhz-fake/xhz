package Practice;

import java.util.Scanner;

abstract class Human {
    protected String name;

    public Human(String name) {
        this.name = name;
    }

    public abstract void sayHi();

}

class Chinese extends Human {
    public Chinese(String name) {
        super(name);
    }

    @Override
    public void sayHi() {
        System.out.println("你好");
    }
}

class Japanese extends Human {
    public Japanese(String name) {
        super(name);
    }

    @Override
    public void sayHi() {
        System.out.println("こんにちは");
    }
}

class English extends Human {
    public English(String name) {
        super(name);
    }

    @Override
    public void sayHi() {
        System.out.println("Hello");
    }
}

public class pra250929_2 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int t = in.nextInt();
        in.nextLine(); // 消耗换行符
        Human[] humans = new Human[t];

        for (int i = 0; i < t; i++) {
            String type = in.next();
            if (type.equals("Chinese")) {
                humans[i] = new Chinese(type);
            } else if (type.equals("Japanese")) {
                humans[i] = new Japanese(type);
            } else {
                humans[i] = new English(type);
            }
        }
        for(Human human : humans){
            human.sayHi();
        }
    }
}