package com.apple.developer.p02;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.ListIterator;
public class ArrayListDemo {
    public static void main(String[] args) {
        ArrayList al=new ArrayList();
        for(int i=1;i<=10;i++){
            al.add(i);
        }
        ListIterator listIter=al.listIterator(0);
        while(listIter.hasNext()){
            System.out.print(listIter.next()+"\t");
        }
        System.out.println("\n--------------------------------------");
        ListIterator listIter1=al.listIterator(al.size());
        while(listIter1.hasPrevious()){
            System.out.print(listIter1.previous()+"\t");
        }
        System.out.println("\n--------------------------------------");
        Iterator iter =al.iterator();
        while(iter.hasNext()){
            System.out.print(iter.next()+"\t");
        }
    }
}
