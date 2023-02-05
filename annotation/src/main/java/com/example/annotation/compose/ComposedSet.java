package com.example.annotation.compose;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author zsl
 * @date 2023/1/27
 * @apiNote
 */
public class ComposedSet<E> extends ForwardingSet<E>{
    private int count = 0;

    public ComposedSet(Set<E> s) {
        super(s);
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

    int getCount(){
        return count;
    }

    public static void main(String[] args) {
        ComposedSet<String> set = new ComposedSet<>(new HashSet<>());
        set.addAll(List.of("a","b","c"));
        System.out.println(set.getCount());
    }
}
