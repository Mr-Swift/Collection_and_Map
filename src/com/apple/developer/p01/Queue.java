package com.apple.developer.p01;
import java.util.LinkedList;
public class Queue {
    protected LinkedList l = new LinkedList();
    protected void print(){
        System.out.println(l);
    }
    protected void add(Object obj) {
        l.addFirst(obj);
    }
    protected Object get() {
        return l.removeLast();
    }
}
