/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author apprentice
 */
public abstract class Employee {
    
    protected String firstName;
    private String lastName;
    
//    public Employee(){
//        System.out.println("Employee Constructed.");
//    }
    
    public Employee(String firstName, String lastName){
        this.firstName = firstName;
        this.lastName = lastName;
    }
    
    public abstract void doWork();



//public abstract void doWork()
//      {
//        System.out.println("Employee: Do Work.");
//    }
    
    public void createObjectives(){
        System.out.println("Employee objectives: Don't get fired.");
        this.firstName = "Name";
    }

    

    
}
