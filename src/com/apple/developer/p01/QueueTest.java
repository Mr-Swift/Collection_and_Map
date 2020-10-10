package com.apple.developer.p01;
public class QueueTest {
    public static void main(String[] args) {
        Queue q = new Queue();
        q.add(1);
        q.print();
        q.add(2);
        q.print();
        q.add(3);
        q.print();
        System.out.println(q.get());
        System.out.println(q.get());
        System.out.println(q.get());
    }
}
