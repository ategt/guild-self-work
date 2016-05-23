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

    private int pushNum = 0;
    private int pullNum = 0;
    private Object[] queue;

    private float percentToChangeBy = .20f;
    private int minimumSize = 15;

    public QueueArrayImpl() {
        queue = new Object[10];
        minimumSize = 15;
    }

    public QueueArrayImpl(int startingSize) {
        queue = new Object[startingSize];
    }

    private void growArray() {

        boolean wrap = false;
        int newPush;
        int newPull;
        int changeInSize = 0;

        int newSize = Math.round(queue.length * (1 + percentToChangeBy));

        if (pushNum > pullNum) {
            wrap = false;

            // The occupied part does not wrap around.
            // The new spaces will go on the end.
            newPull = pullNum;
            newPush = pushNum;

        } else {
            wrap = true;

            // The occupied part does wrap around.
            // The new spaces will go in the middle.
            newPush = pushNum;

            changeInSize = newSize - queue.length;
            int pullDistanceFromEnd = queue.length - pullNum;

            newPull = newSize - pullDistanceFromEnd;

        }

        Object[] tempQueue = new Object[newSize];

        if (wrap) {

            for (int i = 0; i < pushNum; i++) {
                tempQueue[i] = queue[i];
            }

            // Skip the Blank Spaces
            for (int i = pullNum, x = newPull; i < queue.length; i++,x++) {
                tempQueue[x] = queue[i];
            }
        } else {
            for (int i = 0; i < queue.length; i++) {
                tempQueue[i] = queue[i];
            }
        }

        queue = tempQueue;
        pushNum = newPush;
        pullNum = newPull;

    }

    private void shrinkArray() {
        
        int newSize = Math.round(queue.length * (1 - percentToChangeBy));
        //System.out.println("Shrinking from " + queue.length + " to " + newSize);
        //System.out.println("\t\t Old Push: " + pushNum + "\tOld Pull: " + pullNum + "\tNew Length: " + queue.length);
        
        boolean wrap = false;
        int newPush;
        int newPull;
        int changeInSize = 0;

        if (pushNum > pullNum) {
            wrap = false;

            // The occupied part does not wrap around.
            // The new spaces will go on the end.
            newPull = pullNum;
            newPush = pushNum;

        } else {
            wrap = true;

            
            // The occupied part does wrap around.
            // The new spaces will go in the middle.
            newPush = pushNum;

            changeInSize = newSize - queue.length;
            int pullDistanceFromEnd = queue.length - pullNum;

            newPull = newSize - pullDistanceFromEnd;

        }

        Object[] tempQueue = new Object[newSize];

        if (wrap) {

            for (int i = 0; i < pushNum; i++) {
                tempQueue[i] = queue[i];
            }
            
            // Skip the Blank Spaces
            for (int i = pullNum, x = newPull ; i < queue.length; i++,x++) {
                tempQueue[x] = queue[i];
            }

        } else {
            int highestPush = 0;
            for (int i = pullNum,x = 0; i < pushNum ; x++, i++) {
                tempQueue[x] = queue[i];
                highestPush = x+1;
            }
            
            newPull = 0;
            newPush = highestPush;

        }

        pushNum = newPush;
        pullNum = newPull;
        queue = tempQueue;
    }

    private void considerGrowingQueue() {

        int freeSpace = queue.length - size();
        if (freeSpace < 3) {
            growArray();
        }
    }

    private void considerShrinkingQueue() {

        int oneShrink = Math.round(queue.length * (1 - percentToChangeBy));
        int freeSpaceAfterOneShrink = oneShrink - size();
        int twoShrinks = Math.round(oneShrink * (1 - percentToChangeBy));
        int freeSpaceAfterTwoShrinks = twoShrinks - size();
        int freeSpaceAfterOneAndAHalfShrinks = freeSpaceAfterOneShrink + (freeSpaceAfterTwoShrinks / 2);
        
        if (freeSpaceAfterOneAndAHalfShrinks > 0 && freeSpaceAfterTwoShrinks < 0 && oneShrink > minimumSize) {
            shrinkArray();
        }
    }

    @Override
    public boolean isEmpty() {
        return pushNum == pullNum;
    }

    @Override
    public int size() {

        int size;
        boolean wrap;

        if (pushNum == pullNum) {
            size = 0;
        } else if (pushNum > pullNum) {
            wrap = false;

            // The occupied part does not wrap around.
            // The new spaces will go on the end.

            size = pushNum - pullNum;
        } else {
            wrap = true;

            // The occupied part does wrap around.
            // The new spaces will go in the middle.
            int pullDistanceFromEnd = queue.length - pullNum;

            size = pullDistanceFromEnd + pushNum;            
        }

        return size;
    }

    @Override
    public void enqueue(T element) {
    
        if (element != null) {
            queue[pushNum] = element;
            incrementPushNum();

            considerGrowingQueue();
        }

    }

    private void incrementPushNum() {
        pushNum++;

        if (pushNum >= queue.length) {
            pushNum = 0;
        }
    }

    @Override
    public T dequeue() {
        if (pullNum != pushNum && pullNum >= 0 && pullNum < queue.length) {
            
            Object returnObject = queue[pullNum];
            queue[pullNum] = null;

            if (returnObject != null) {
                incrementPullNumber();
                considerShrinkingQueue();
                return (T) returnObject;
            } else {
                return null;
            }
        } else {
            return null;
        }
    }

    private void incrementPullNumber() {
        pullNum++;

        if (pullNum >= queue.length) {
            pullNum = 0;
        }
    }
}
