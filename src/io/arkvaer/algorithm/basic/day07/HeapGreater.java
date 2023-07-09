package io.arkvaer.algorithm.basic.day07;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;

/**
 * 加强堆
 *
 * @author waver
 * @date 2023/7/9 17:40
 */
public class HeapGreater<T> {
    private ArrayList<T> heap;
    private HashMap<T, Integer> indexMap;
    private int heapSize;
    private Comparator<T> comparator;

    public HeapGreater(Comparator<T> comparator) {
        this.heap = new ArrayList<>();
        this.indexMap = new HashMap<>();
        this.heapSize = 0;
        this.comparator = comparator;
    }


    public boolean isEmpty() {
        return heapSize == 0;
    }


    public int size() {
        return heapSize;
    }

    public List<T> getAllElements() {
        return List.copyOf(heap);
    }

    public boolean contains(T obj) {
        return indexMap.containsKey(obj);
    }


    public T pop() {
        T result = heap.get(0);
        swap(0, heapSize - 1);
        indexMap.remove(result);
        heap.remove(heapSize--);
        heapify(0);
        return result;
    }


    public void push(T obj) {
        heap.add(obj);
        indexMap.put(obj, heapSize);
        heapInsert(heapSize++);
    }

    public void remove(T obj) {
        T replace = heap.get(heapSize - 1);
        int index = indexMap.get(obj);
        indexMap.remove(obj);
        heap.remove(--heapSize);
        if (obj != replace) {
            heap.set(index, replace);
            indexMap.put(replace, index);
            resign(replace);
        }
    }

    public T peek() {
        return heap.get(0);
    }

    public void resign(T obj) {
        heapInsert(indexMap.get(obj));
        heapify(indexMap.get(obj));
    }

    public void heapInsert(int index) {
        while (comparator.compare(heap.get(index), heap.get((index - 1) / 2)) > 0) {
            swap(index, (index - 1) / 2);
            index = (index - 1) / 2;
        }
    }


    public void heapify(int index) {
        int left = index * 2 + 1;
        while (left < heapSize) {
            int largest = left + 1 < heapSize && comparator.compare(heap.get(left), heap.get(left + 1)) > 0 ? left : left + 1;
            largest = comparator.compare(heap.get(index), heap.get(largest)) > 0 ? index : largest;
            if (largest == index) {
                return;
            }
            swap(largest, index);
            index = largest;
            left = index * 2 + 1;
        }
    }


    public void swap(int i, int j) {
        T m = heap.get(i);
        T n = heap.get(j);
        indexMap.put(m, j);
        indexMap.put(n, i);
        heap.set(i, n);
        heap.set(j, m);
    }

}
