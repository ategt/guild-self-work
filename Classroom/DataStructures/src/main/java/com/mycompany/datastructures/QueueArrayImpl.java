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
    private int minimumSize = 0;

    public QueueArrayImpl() {
        queue = new Object[10];
        minimumSize = 10;
    }

    public QueueArrayImpl(int startingSize) {
        queue = new Object[startingSize];
        minimumSize = startingSize;
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

        //int  = pushNum - pullNum;
        //Object[] tempStack = java.util.Arrays.copyOf(queue, newSize);
        Object[] tempQueue = new Object[newSize];

        if (wrap) {
            //for ( int i = 0 ; i < queue.length ;  i++ ) {

            for (int i = 0; i < pushNum; i++) {
                tempQueue[i] = queue[i];
            }

            // Skip the Blank Spaces
            //for (int i = changeInSize + pullNum; i < tempQueue.length; i++) {
            for (int i = pullNum; i < tempQueue.length; i++) {
                tempQueue[i + changeInSize] = queue[i];
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

        //int  = pushNum - pullNum;
        //Object[] tempStack = java.util.Arrays.copyOf(queue, newSize);
        Object[] tempQueue = new Object[newSize];

        if (wrap) {
            //for ( int i = 0 ; i < queue.length ;  i++ ) {

            for (int i = 0; i < pushNum; i++) {
                tempQueue[i] = queue[i];
            }

            // Skip the Blank Spaces
            //for (int i = changeInSize + pullNum; i < tempQueue.length; i++) {
            for (int i = pullNum; i < tempQueue.length; i++) {
                tempQueue[i - changeInSize] = queue[i];
            }
        } else {
            System.out.println("\n");
            int highestPush = 0;
            for (int i = pullNum,x = 0; i < pushNum ; x++, i++) {
                System.out.println("temp[" + x + "]: " + tempQueue[x] + " queue[" + i + "]: " + queue[i]);
                tempQueue[x] = queue[i];
                highestPush = x+1;
            }
            
            System.out.println("\n");
            newPull = 0;
            newPush = highestPush;

        }

        queue = tempQueue;
        pushNum = newPush;
        pullNum = newPull;

        //Object[] tempStack = java.util.Arrays.copyOf(queue, newSize);
        queue = tempQueue;
    }

    private void considerGrowingQueue() {

        int freeSpace = queue.length - size();
        if (freeSpace < 2) {
            growArray();
        }
    }

    private void considerShrinkingQueue() {

        int freeSpaceNow = queue.length - size();

        int oneShrink = Math.round(queue.length * (1 - percentToChangeBy));

        int freeSpaceAfterOneShrink = oneShrink - size();

        int twoShrinks = Math.round(oneShrink * (1 - percentToChangeBy));

        int freeSpaceAfterTwoShrinks = twoShrinks - size();

        //int halfWayBetween = Math.round((oneShrink + twoShrinks) / 2);
        int halfWayBetweenFreeSpace = Math.round((freeSpaceAfterOneShrink + freeSpaceAfterTwoShrinks) / 2);

        int freeSpaceAfterOneAndAHalfShrinks = freeSpaceAfterOneShrink + (freeSpaceAfterTwoShrinks / 2);

        System.out.println("\t 1 1/2: " + freeSpaceAfterOneAndAHalfShrinks + "\t Free After Two: " + freeSpaceAfterTwoShrinks);
        
        // When freespace after 1 1/2 shrinks is 0.
        if (freeSpaceAfterOneAndAHalfShrinks > 0 && freeSpaceAfterTwoShrinks < 0 && oneShrink > minimumSize) {
            System.out.println("\t\tShrinking....");
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
            //newPull = pullNum;
            // newPush = pushNum;
            size = pushNum - pullNum;

        } else {
            wrap = true;

            // The occupied part does wrap around.
            // The new spaces will go in the middle.
            //newPush = pushNum;
            //changeInSize = newSize - queue.length;
            int pullDistanceFromEnd = queue.length - pullNum;

            size = pullDistanceFromEnd + pushNum;

            //newPull = newSize - pullDistanceFromEnd;
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

        System.out.println("Push: " + pushNum + " , Pull: " + pullNum + " , Queue Length: " + queue.length);
        System.out.println("\t Size: " + size());
        if (pullNum != pushNum && pullNum >= 0 && pullNum < queue.length) {
            
            System.out.println("Made it to the first block.");
            
            Object returnObject = queue[pullNum];
            queue[pullNum] = null;

            if (returnObject != null) {
                System.out.println("Made it to the second block.");
                incrementPullNumber();
                considerShrinkingQueue();
                return (T) returnObject;
            } else {
                return null;
            }
        } else {
            return null;
        }
        //return null;
    }

    private void incrementPullNumber() {
        pullNum++;

        if (pullNum >= queue.length) {
            pullNum = 0;
        }
    }

}
