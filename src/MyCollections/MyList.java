package MyCollections;

import java.util.Arrays;
import java.util.Iterator;
import java.util.Spliterator;
import java.util.function.Consumer;

/*
 *    This is an awesome List with powerful capacity,method and competence
 *    It is designed for the target of owning a powerful ArrayList which wished to be applied to my projects.
 *    Bless the instance beyond will cultivate my programming expertises as well good habits.
 */

public class MyList<E> implements Iterable<E> {//实现 Iterable 接口，支持 foreach 循环
    //定义默认的容器容量
    private static final int DEFAULT_CAPACITY = 10;

    //内部数组
    private Object[] elements;//使用 Object[] 而非 E[]：Java 泛型擦除机制下更安全的选择

    //当前元素数量
    private int size;

    //构造方法
    public MyList() {
        this(DEFAULT_CAPACITY);//默认构造方法，初始化数组为默认容量（10）。
    }

    public MyList(int initialCapacity) {
        if (initialCapacity < 0) {
            throw new IllegalArgumentException("Illegal Capacity: " + initialCapacity);
        }
        this.elements = new Object[initialCapacity];
        this.size = 0;// 初始化当前元素数量为0
    }

    //确保容量足够
    private void ensureCapacity(int minCapacity) {
        if (minCapacity > elements.length) {
            int newCapacity = elements.length + (elements.length >> 1);//1.5倍扩容策略
            if (newCapacity < minCapacity) {
                newCapacity = minCapacity;
            }
            elements = Arrays.copyOf(elements, newCapacity);//返回一个容量更大的组
        }
    }

    //索引范围检查
    private void rangeCheck(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        }
    }

    //添加元素方法的索引范围检查
    private void rangeCheckForAdd(int index) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        }
    }

    //获取当前元素的数量
    public int size() {
        return size;
    }

    //判断是否为空
    public boolean isEmpty() {
        return size == 0;
    }

    //将元素增添到末尾
    public boolean add(E element) {
        ensureCapacity(size+1);//优先扩展容量
        elements[size++] = element;
        return true;
    }

    //将元素添加到指定位置
    public void add(int index, E element) {
        rangeCheckForAdd(index);
        ensureCapacity(size++);//优先扩展容量
        //移动插入位置以后的元素
        System.arraycopy(elements, index, elements, index + 1, size - index);
        elements[index] = element;
        size++;
    }

    //获取指定位置的元素
    @SuppressWarnings("unchecked")//告诉编译器："我知道这里可能有不安全的类型转换，但我已经确认它是安全的，请忽略警告"
    //是一个合理且必要的工具，它允许我们在保证类型安全的前提下，实现高性能的泛型集合。关键在于理解其背后的原理，并确保只在真正安全的情况下使用。
    public E get(int index) {
        rangeCheck(index);
        return (E) elements[index];
    }

    //设置指定位置的元素
    @SuppressWarnings("unchecked")
    public E set(int index, int element) {
        rangeCheck(index);
        E oldValue = (E) elements[index];
        elements[index] = element;
        return oldValue;
    }

    //删除指定位置的元素
    @SuppressWarnings("unchecked")
    public E remove(int index) {
        rangeCheck(index);
        E oldValue = (E) elements[index];
        int numMoved = size - index - 1;
        if (numMoved > 0) {
            System.arraycopy(elements, index + 1, elements, index, numMoved);
        }
        return oldValue;
    }

    //清空整个列表
    public void clear() {
        for (int i = 0; i < size; i++) {
            elements[i] = null;
        }
        size = 0;
    }

    //判断是否包含某个元素
    public boolean contains(Object o) {
        return indexOf(o) >= 0;
    }

    //查找元素指引
    public int indexOf(Object o) {
        for (int i = 0; i < size; i++) {
            if ((o == null && elements[i] == null) || (o != null && o.equals(elements[i]))) {
                return i;
            }
        }
        return -1;
    }

//    转化为数组
//    public Object[] toArray(){
//        return Arrays.copyOf(elements, size);
//    }

    //转换为指定类型的数组
    @SuppressWarnings("unchecked")
    public <T> T[] toArray(T[] a) {
        if (a.length < size) {//如果传入数组长度小于集合大小使用 Arrays.copyOf() 创建新数组
            return (T[]) Arrays.copyOf(elements, size, a.getClass());//新数组类型：a.getClass() 获取传入数组的运行时类型新数组大小：size（集合当前元素数量）
        }
        System.arraycopy(elements,0,a,0,size);// 处理数组长度足够的情况: 使用高效的原生数组复制方法

/*
        elements：源数组（集合内部存储）
        0：源数组起始位置
        a：目标数组
        0：目标数组起始位置
        size：要复制的元素数量
*/
        if(a.length>size){//处理多余空间的情况
            a[size]=null;//设置终止标记: 在集合元素之后设置 null
        }
        return a;
    }

    @Override
    public Iterator<E> iterator() {
        return null;
    }

    @Override
    public void forEach(Consumer<? super E> action) {
        Iterable.super.forEach(action);
    }

    @Override
    public Spliterator<E> spliterator() {
        return Iterable.super.spliterator();
    }
}
