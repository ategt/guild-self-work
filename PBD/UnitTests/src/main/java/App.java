/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


/**
 *
 * @author apprentice
 */
public class App {
    public static void main(String[] args) {
       MethodsForArraysJUnitDrills methodsForArraysJUnitDrills = new MethodsForArraysJUnitDrills();

        int[] testArray1 = {4};
        int[] testArray2 = {1,7,3};

        int[] expectedResult = {4,1};

        int[] result = methodsForArraysJUnitDrills.make2(testArray1, testArray2);

        System.out.println("Expected:"+expectedResult.length);
        System.out.println("Returned:"+result.length);
        
        System.out.print("[");
        for (int numb : result){
            System.out.print(numb + ",");
        }
        System.out.println("]");
        
      //  Assert.assertArrayEquals(result, expectedResult);

    }
}
