package com.example.annotation.inherit;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;

/**
 * @author zsl
 * @date 2023/1/27
 * @apiNote
 */
public class InstrumentedHashSet<E> extends HashSet<E> {
    private int count = 0;
    public InstrumentedHashSet() {

    }

    @Override
    public boolean add(E e) {
        count++;
        return super.add(e);
    }

    @Override
    public boolean addAll(Collection<? extends E> c) {
        count+=c.size();
        return super.addAll(c);
    }
    public int getCount() {
        return count;
    }
    public static void main(String[] args) {
        InstrumentedHashSet<String> set = new InstrumentedHashSet<>();
        set.addAll(List.of("a","b","c"));
        System.out.println(set.getCount());
    }
}
