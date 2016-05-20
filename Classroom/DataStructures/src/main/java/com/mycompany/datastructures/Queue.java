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
public interface Queue<T> {
    
    public void enqueue(T element);
    
    public T dequeue();
    
    public boolean isEmpty();
    
    public int size();
    
}
