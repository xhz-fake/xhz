package ConstructorDemo.StaticFactoryMethod;

public class Pizza {//复杂对象的构造器模式
    private final Size size;
    private final boolean cheese;
    private final boolean pepperoni;
    private final boolean mushrooms;
    private final boolean bacon;

    //私有化Pizza的构造器-->只能通过构造器创建对象
    private Pizza(Builder builder) {
        this.size = builder.size;
        this.cheese = builder.cheese;
        this.bacon = builder.bacon;
        this.mushrooms = builder.mushrooms;
        this.pepperoni = builder.pepperoni;
    }

    //静态工厂方法返回构造器
    public static Builder builder(Size size) {
        return new Builder(size);
    }

    //构造器类(静态内部类)
    public static class Builder {
        private final Size size;
        private boolean cheese = false;
        private boolean mushrooms = false;
        private boolean pepperoni = false;
        private boolean bacon = false;

        public Builder(Size size) {
            this.size = size;
        }

        public Builder addCheese() {
            this.cheese = true;
            return this;
        }

        public Builder addPepperoni() {
            this.pepperoni = true;
            return this;
        }

        public Builder addMushroom() {
            this.mushrooms = true;
            return this;
        }

        public Builder addBacon() {
            this.bacon = true;
            return this;
        }

        public Pizza build() {
            return new Pizza(this);
        }
    }

    @Override
    public String toString() {
        return "Pizza[size=" + size +
                ", cheese=" + cheese +
                ", pepperoni=" + pepperoni +
                ", mushrooms=" + mushrooms +
                ", bacon=" + bacon + "]";
    }
}
