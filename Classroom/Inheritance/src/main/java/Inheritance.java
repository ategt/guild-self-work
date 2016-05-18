
import java.util.ArrayList;
import java.util.List;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author apprentice
 */
public class Inheritance {
    public static void main(String[] args) {
        //Employee employee = new Employee();
        
        Employee manager = new Manager("Bill","Jones");
        //Manager
        
        
        SummerIntern intern = new SummerIntern();
        
        //manager.doWork();
        
        //Manager castedManager = (Manager) manager;
        
        
        
        
        
        
//        
//        
//        
//        castedManager.fire();
//        
//        List<Employee> employees = new ArrayList();
//        
//        employees.add(employee);
//        employees.add(manager);
//        employees.add(intern);
//        
//        employees.stream()
//                .forEach(Employee::doWork);
//        
    }
}
