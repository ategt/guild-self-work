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
public class StackArrayImpl<T> implements Stack<T> {

    private int pointerNum = 0;
    private Object[] stack;
    private float percentToChangeBy = .20f;

    public StackArrayImpl() {
        stack = new Object[10];
    }

    public StackArrayImpl(int startingSize) {
        stack = new Object[startingSize];
    }

    private void growArray() {

        int newSize = Math.round(stack.length * (1 + percentToChangeBy));
        System.out.println("Stack was: " + stack.length + "\t Growing to : " + newSize );
        System.out.println("Pointer is at : " + pointerNum + "\n");
        Object[] tempStack = java.util.Arrays.copyOf(stack, newSize);

        stack = tempStack;

    }

    private void shrinkArray() {
        int newSize = Math.round(stack.length * (1 - percentToChangeBy));
        System.out.println("Stack was: " + stack.length + "\t Shrinking to : " + newSize );
        System.out.println("Pointer is at : " + pointerNum + "\n");
        Object[] tempStack = java.util.Arrays.copyOf(stack, newSize);
        stack = tempStack;
    }

    private void considerGrowingStack(){
        
        if ( stack.length <= pointerNum + 1) {
            growArray();
        }
    }
    
    private void considerShrinkingStack(){
        
        if ( stack.length * (1 - percentToChangeBy)  >= pointerNum * (1 - (percentToChangeBy*2)) ) {
            shrinkArray();
        }
    }
    
    
    @Override
    public void push(T element) {
        stack[pointerNum] = element;
        pointerNum++;

        considerGrowingStack();
    }

    @Override
    public T pop() {
        
        considerShrinkingStack();
        
        if (pointerNum >= 0) {
            return (T) stack[pointerNum--];
        } else {
            return null;
        }
    }

    @Override
    public boolean isEmpty() {
        if (pointerNum < 0) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public int size() {
        return (pointerNum + 1);
    }

}
