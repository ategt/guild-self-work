/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author apprentice
 */
public class Manager extends Employee{
    
    public Manager(String firstName, String lastName){
        //super(firstName, lastName);
        System.out.println("Manager Constructed.");
    }
        
    @Override
    public void doWork(){
        super.doWork();
        System.out.println("Manager doing work: Life is good.");
        super.doWork();
    }
    
    public void hire(){
        System.out.println("Manager: Hiring");
    }
    
    public void fire(){
        System.out.println("Manager: Firing");
    }
}
