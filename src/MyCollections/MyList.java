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
        ensureCapacity(size + 1);//优先扩展容量
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
        //清空最后一个位置
        elements[--size] = null;
        return oldValue;
    }

    //删除指定元素
    public boolean remove(Object o) {
        for (int i = 0; i < size; i++) {
            if ((o == null && elements[i] == null) || (o != null && o.equals(elements[i]))) {
                remove(i);
                return true;
            }
        }
        return false;
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

    //转换为指定类型的数组
    @SuppressWarnings("unchecked")
    public <T> T[] toArray(T[] a) {
        if (a.length < size) {//如果传入数组长度小于集合大小使用 Arrays.copyOf() 创建新数组
            return (T[]) Arrays.copyOf(elements, size, a.getClass());//新数组类型：a.getClass() 获取传入数组的运行时类型新数组大小：size（集合当前元素数量）
        }
        System.arraycopy(elements, 0, a, 0, size);// 处理数组长度足够的情况: 使用高效的原生数组复制方法

/*
        elements：源数组（集合内部存储）
        0：源数组起始位置
        a：目标数组
        0：目标数组起始位置
        size：要复制的元素数量
*/
        if (a.length > size) {//处理多余空间的情况
            a[size] = null;//设置终止标记: 在集合元素之后设置 null
        }
        return a;
    }

    //如果元素是整数型或浮点数型则快速排序
    @SuppressWarnings("unchecked")
    public void sort() {
        if (elements[0] != null && elements[0].getClass() == Integer.class) {
            int[] arr = new int[size];
            for (int i = 0; i < size; i++) {
                int num = (int) elements[i];
                arr[i] = num;
            }
            intSort(arr);
            for (int i = 0; i < size; i++) {
                elements[i] = arr[i];
            }
        } else if (elements[0] != null && elements[0].getClass() == Float.class) {
            float[] arr = new float[size];
            for (int i = 0; i < size; i++) {
                float num = (float) elements[i];
                arr[i] = num;
            }
            floatSort(arr);
            for (int i = 0; i < size; i++) {
                elements[i] = arr[i];
            }
        } else if (elements[0] != null && elements[0].getClass() == Double.class) {
            double[] arr = new double[size];
            for (int i = 0; i < size; i++) {
                double num = (double) elements[i];
                arr[i] = num;
            }
            doubleSort(arr);
            for (int i = 0; i < size; i++) {
                elements[i] = arr[i];
            }
        }
    }

    //整数快排法
    public static void intSort(int[] arr) {
        if (arr == null || arr.length <= 1) {
            return; // 如果数组为空或长度<=1，无需排序
        }
        intquickSort(arr, 0, arr.length - 1); // 调用递归排序
    }

    private static void intquickSort(int[] arr, int low, int high) {
        if (low < high) {
            int pivotIndex = intPartition(arr, low, high); // 获取基准值的正确位置
            intquickSort(arr, low, pivotIndex - 1);  // 递归排序左子数组
            intquickSort(arr, pivotIndex + 1, high); // 递归排序右子数组
        }
    }

    private static int intPartition(int[] arr, int low, int high) {
        int pivot = arr[high]; // 选择最后一个元素作为基准（pivot）
        int i = low - 1;      // i 是小于 pivot 的元素的边界

        for (int j = low; j < high; j++) {
            if (arr[j] < pivot) {
                i++;
                intSwap(arr, i, j); // 交换 arr[i] 和 arr[j]
            }
        }
        intSwap(arr, i + 1, high); // 将 pivot 放到正确的位置
        return i + 1; // 返回 pivot 的索引
    }

    private static void intSwap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    //小浮点数快排法
    public static void floatSort(float[] arr) {
        if (arr == null || arr.length <= 1) {
            return; // 如果数组为空或长度<=1，无需排序
        }
        floatquickSort(arr, 0, arr.length - 1); // 调用递归排序
    }

    private static void floatquickSort(float[] arr, int low, int high) {
        if (low < high) {
            int pivotIndex = floatPartition(arr, low, high); // 获取基准值的正确位置
            floatquickSort(arr, low, pivotIndex - 1);  // 递归排序左子数组
            floatquickSort(arr, pivotIndex + 1, high); // 递归排序右子数组
        }
    }

    private static int floatPartition(float[] arr, int low, int high) {
        float pivot = arr[high]; // 选择最后一个元素作为基准（pivot）
        int i = low - 1;      // i 是小于 pivot 的元素的边界

        for (int j = low; j < high; j++) {
            if (arr[j] < pivot) {
                i++;
                floatSwap(arr, i, j); // 交换 arr[i] 和 arr[j]
            }
        }
        floatSwap(arr, i + 1, high); // 将 pivot 放到正确的位置
        return i + 1; // 返回 pivot 的索引
    }

    private static void floatSwap(float[] arr, int i, int j) {
        float temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    //大浮点数快排法
    public static void doubleSort(double[] arr) {
        if (arr == null || arr.length <= 1) {
            return; // 如果数组为空或长度<=1，无需排序
        }
        doublequickSort(arr, 0, arr.length - 1); // 调用递归排序
    }

    private static void doublequickSort(double[] arr, int low, int high) {
        if (low < high) {
            int pivotIndex = doublePartition(arr, low, high); // 获取基准值的正确位置
            doublequickSort(arr, low, pivotIndex - 1);  // 递归排序左子数组
            doublequickSort(arr, pivotIndex + 1, high); // 递归排序右子数组
        }
    }

    private static int doublePartition(double[] arr, int low, int high) {
        double pivot = arr[high]; // 选择最后一个元素作为基准（pivot）
        int i = low - 1;      // i 是小于 pivot 的元素的边界

        for (int j = low; j < high; j++) {
            if (arr[j] < pivot) {
                i++;
                doubleSwap(arr, i, j); // 交换 arr[i] 和 arr[j]
            }
        }
        doubleSwap(arr, i + 1, high); // 将 pivot 放到正确的位置
        return i + 1; // 返回 pivot 的索引
    }

    private static void doubleSwap(double[] arr, int i, int j) {
        double temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    //反转列表
    public void reverse() {
        for (int i = 0; i < size / 2; i++) {
            Object temp;
            temp = elements[i];
            elements[i] = elements[size - 1 - i];
            elements[size - 1 - i] = temp;
        }
    }

    //toString方法
    public String toString() {
        if (size == 0) {
            return "}";
        }
        StringBuilder sb = new StringBuilder();
        sb.append("{");
        for (int i = 0; i < size; i++) {
            Object e = elements[i];
            sb.append(e == this ? "(this collection)" : e);
            if (i < size - 1) {
                sb.append(", ");
            }
        }
        return sb.append("}").toString();
    }

    public Object[] cloneArray(E[] objs, CloneTools<E> ct) {
        int size = objs.length;
        Object[] newObjs = new Object[size];
        for (int i = 0; i < size; i++) {
            E o = objs[i];
            if (o != null) {
                E newo = ct.cloneObject(o);
                newObjs[i] = newo;
            }
        }
        return newObjs;
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
