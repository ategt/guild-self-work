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
public interface Stack<T> {
 
    public void push(T element);
    
    public T pop();
    
    public boolean isEmpty();
    
    public int size();
    
}
