/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.datastructures;

/**
 *
 * @author apprentice
 */
public class QueueArrayImpl<T> implements Queue<T> {

    private int pointerNum = 0;
    private Object[] stack;
    private float percentToChangeBy = .20f;
    private int minimumSize = 0;

    public QueueArrayImpl() {
        stack = new Object[10];
        minimumSize = 10;
    }

    public QueueArrayImpl(int startingSize) {
        stack = new Object[startingSize];
        minimumSize = startingSize;
    }

    private void growArray() {

        int newSize = Math.round(stack.length * (1 + percentToChangeBy));
        Object[] tempStack = java.util.Arrays.copyOf(stack, newSize);

        stack = tempStack;

    }

    private void shrinkArray() {
        int newSize = Math.round(stack.length * (1 - percentToChangeBy));
        Object[] tempStack = java.util.Arrays.copyOf(stack, newSize);
        stack = tempStack;
    }

    private void considerGrowingStack() {

        if (stack.length <= pointerNum + 1) {
            growArray();
        }
    }

    private void considerShrinkingStack() {

        int oneShrink = Math.round(stack.length * (1 - percentToChangeBy));
        int twoShrinks = Math.round(oneShrink * (1 - percentToChangeBy));
        int halfWayBetween = Math.round((oneShrink + twoShrinks) / 2);

        //System.out.println("Current Size: " + stack.length + "\t One Shrink: " + oneShrink + "\t Two Shrinks: " + twoShrinks + "\t Half: " + halfWayBetween);
        if (halfWayBetween > pointerNum && oneShrink > minimumSize) {
            shrinkArray();
        }
    }

//    @Override
//    public void push(T element) {
//        if (element != null) {
//
//            stack[pointerNum] = element;
//            pointerNum++;
//
//            considerGrowingStack();
//        }
//    }
//
//    @Override
//    public T pop() {
//
//        if (pointerNum >= 0) {
//            pointerNum--;
//
//            if (pointerNum < 0){
//                
//                pointerNum = 0;
//                return null;
//            }
//            
//            considerShrinkingStack();
//            return (T) stack[pointerNum];
//        } else {
//            return null;
//        }
//    }
    
    @Override
    public boolean isEmpty() {
//        if (pointerNum > 0) {
//            return false;
//        } else {
//            return true;
//        }
    }

    @Override
    public int size() {
        //return (pointerNum);
    }

    @Override
    public void enqueue(T element) {
        if (element != null) {

            stack[pointerNum] = element;
            pointerNum++;

            considerGrowingStack();
        }

    }

    @Override
    public T dequeue() {

        if (pointerNum >= 0) {
            pointerNum--;

            if (pointerNum < 0) {

                pointerNum = 0;
                return null;
            }

            considerShrinkingStack();
            return (T) stack[pointerNum];
        } else {
            return null;
        }
        //return null;
    }


}
