/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package za.ac.cput.assignment3;

//import java.text.ParseException;

import java.io.IOException;
import java.text.ParseException;


/**
 *
 * @author Yasmeen Nel <219250553@mycput.ac.za>
 */
public class RunSerialization {
    public static void main (String[]args) throws IOException, ParseException{
   /* CreateStakeholderSer obj= new CreateStakeholderSer();
    obj.openFile();
    obj.writeToFile();
    obj.closeFile();*/
    
        
    ReadCSArraylist obj = new ReadCSArraylist();
    obj.openFile();
    obj.readArrayList();
    obj.closeFile();
    obj.sortCustomer();
    obj.sortSupplier();
    obj.formatDate();
    //obj.Age();
    obj.eligibilityRentCar();
    obj.displayCustomerTxtFile();
    obj.displaySupplierTxtFile();
    
    }
}
