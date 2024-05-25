package cn.regexp.coding.trainee.utils;

import java.util.Arrays;
import java.util.Objects;

/**
 * @author Regexpei
 * @date 2024/4/4 23:04
 * @description 动态数组
 */
public class MyArrayList<T> {

    /**
     * 默认容量
     */
    private static final int DEFAULT_CAPACITY = 10;
    /**
     * 数组中元素个数
     */
    private int size;
    /**
     * 存放元素的数组
     */
    private T[] elements;

    public MyArrayList() {
        this(DEFAULT_CAPACITY);
    }

    @SuppressWarnings("unchecked")
    public MyArrayList(int capacity) {
        if (capacity < 0) {
            throw new IllegalArgumentException();
        }

        elements = (T[]) new Object[capacity];
    }

    /**
     * 获取元素个数
     *
     * @return 元素个数
     */
    public int size() {
        return size;
    }

    /**
     * 判断是否为空数组
     *
     * @return 是否为空数组
     */
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * 添加元素
     *
     * @param element 需要添加的元素
     */
    public void add(T element) {
        add(size, element);
    }

    /**
     * 添加元素到指定索引下标位置
     *
     * @param index   索引下标
     * @param element 需要添加的元素
     */
    public void add(int index, T element) {
        if (index != size) {
            checkRange(index);
        }

        // 检查是否需要扩容并进行扩容
        ensureCapacity(size + 1);

        for (int i = size; i > index; i--) {
            elements[i] = elements[i - 1];
        }

        elements[index] = element;
        size++;
    }

    /**
     * 确保有足够容量进行添加元素
     *
     * @param minCapacity 最小容量
     */
    private void ensureCapacity(int minCapacity) {
        int oldCapacity = elements.length;
        if (minCapacity <= oldCapacity) {
            return;
        }

        // oldCapacity >> 1 ==> oldCapacity / 2
        int newCapacity = oldCapacity + oldCapacity >> 1;

        /*
            int[] newElements = new int[newCapacity];
            for (int i = 0; i < size; i++) {
                newElements[i] = elements[i];
            }
            elements = newElements;
         */
        elements = Arrays.copyOf(elements, newCapacity);
    }

    /**
     * 获取指定索引的元素
     *
     * @param index 索引下标
     * @return 元素
     */
    public T get(int index) {
        checkRange(index);
        return elements[index];
    }

    /**
     * 替换元素
     *
     * @param index   被替换的元素索引下标
     * @param element 替换的元素
     * @return 被替换的元素
     */
    public T set(int index, T element) {
        checkRange(index);

        T old = elements[index];
        elements[index] = element;
        return old;
    }

    /**
     * 删除指定索引位置的元素
     *
     * @param index 索引下标
     * @return 返回被删除元素
     */
    public T remove(int index) {
        checkRange(index);

        T old = elements[index];
        for (int i = index; i < size - 1; i++) {
            elements[i] = elements[i + 1];
        }
        elements[--size] = null;
        return old;
    }

    /**
     * 查询数组是否包含某个元素
     *
     * @param element 需要查询的元素
     * @return 是否包含
     */
    public boolean contains(T element) {
        return indexOf(element) >= 0;
    }

    /**
     * 获取给定元素在数组中的索引下标
     *
     * @param element 需要查询的元素
     * @return 索引下标
     */
    public int indexOf(T element) {
        for (int i = 0; i < size; i++) {
            if ((element == null && elements[i] == null)
                    || Objects.equals(element, elements[i])) {
                return i;
            }
        }

        return -1;
    }

    /**
     * 清除元素
     */
    public void clear() {
        for (int i = 0; i < size; i++) {
            // 断除对象与数组的链接
            elements[i] = null;
        }
        size = 0;
    }

    /**
     * 检查索引下标是否在合理范围内
     *
     * @param index 索引下标
     */
    private void checkRange(int index) {
        if (index < 0 || index >= size) {
            String message = String.format("Index: %d, Range: 0 - %d", index, size);
            throw new ArrayIndexOutOfBoundsException(message);
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        for (int i = 0; i < size; i++) {
            if (i != 0) {
                sb.append(", ");
            }
            sb.append(elements[i]);
        }
        sb.append("], size = ").append(size);
        return sb.toString();
    }
}