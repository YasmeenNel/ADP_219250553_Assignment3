
package za.ac.cput.assignment3;

import java.io.BufferedWriter;
import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.text.ParseException;


/**
 *
 * @author Yasmeen Nel <219250553@mycput.ac.za>
 * Stud no: 219250553
 */
public class ReadCSArraylist{
    
    ObjectInputStream input;
    Object ArrList;
    BufferedWriter buffWrite;
    int countTrue = 0;
    int countFalse = 0;
    ArrayList <Customer> customer = new ArrayList <>();
    ArrayList <Supplier> supplier = new ArrayList <>();
    
   
    
    public void openFile(){
        try{
            input = new ObjectInputStream( new FileInputStream( "stakeholder.ser" ) ); 
            System.out.println("*** ser file opened for reading ***");               
        }
        catch (IOException ioe){
            System.out.println("error opening ser file: " + ioe.getMessage());
            System.exit(1);
        }
    }
     public void closeFile(){
        try{
            input.close( ); 
        }
        catch (IOException ioe){            
            System.out.println("error closing ser file:  " + ioe.getMessage());
            System.exit(1);
        }        
    }  
 //Step 2a: Have supplier and customer in seperate ArrayLists
     public void readArrayList(){
       
        try{
            while(true){
                ArrList = input.readObject();
                String customers = "Customer";
                String suppliers = "Supplier";
                String index = ArrList.getClass().getSimpleName();
                
                if (index.equals(customers)){
                    customer.add((Customer)ArrList);
                }
                else if(index.equals(suppliers)){
                    supplier.add((Supplier)ArrList);
                }
                else {
                    System.out.println("Unable to add to Array List");
                }
            }
        }
        catch(EOFException eofe){
            System.out.println("EOF Reached");
        }
        catch(ClassNotFoundException ioe ){
            System.out.println("Class error"+ioe);
        }
        catch(IOException ioe){
            System.out.println("Error reading ser file "+ioe);
        }
        finally{
            closeFile();
            System.out.println("*** file has been closed  ***");               
        }
        
        sortCustomer();
    }    
    
   //Step 2b: Sort customer by Stakeholder Id
     public void sortCustomer(){
         String[] customSort = new String[customer.size()];
         ArrayList<Customer> sortIDArray = new ArrayList<>();
         int index = customer.size();
            for(int i = 0; i<index;i++){
                customSort[i] = customer.get(i).getStHolderId();
            }
                Arrays.sort(customSort);
            for(int i =0;i<index;i++){
                for(int k =0;k<index;k++){
                    if(customSort[i].equals(customer.get(k).getStHolderId())){
                        sortIDArray.add(customer.get(k));
                    } 
                }
            }
            customer=sortIDArray;
            System.out.println("Sort Customer by ID");
     }
    //Step 2c: Age
     public void Age(){
         
        
                 
    }
     
    //Step 2d: Re-format Date
    public void formatDate(){
        String [] format = new String[customer.size()];
        ArrayList<Customer> formatDOB = new ArrayList<>();
        
        String dobFormat;
        
        for(int i=0;i<customer.size();i++){
            format[i] = customer.get(i).getDateOfBirth();
            String day = format[i].substring(8,10); //"1981-01-27"
            String month = "";
            String year = format[i].substring(0,4);
            
            switch(format[i].substring(5, 7)){
                case "01":
                    month = "Jan";
                    break;
                case "02":
                    month ="Feb";
                    break;
                case "03":
                    month ="Mar";
                    break;
                case "04":
                    month ="Apr";
                    break;
                case "05":
                    month = "May";
                    break;
                case "06":
                    month ="Jun";
                    break;
                case "07":
                    month ="Jul";
                    break;
                case "08":
                    month = "Aug";
                    break;
                case "09":
                    month = "Sep";
                    break;
                case "10":
                    month = "Oct";
                    break;
                case "11":
                    month ="Nov";
                    break;
                case "12":
                    month ="Dec";
                    break;
                default:
                    System.out.println("Unreadable date");
                    break;
                
            }
            dobFormat = day+ " "+ month+" "+year;
           
                 if (format[i].equals(customer.get(i).getDateOfBirth())){
                     formatDOB.add(customer.get(i));
                     customer.get(i).setDateOfBirth(dobFormat);
                     System.out.println(customer.get(i).getDateOfBirth());
             }
         }
    }  
     //Step 2f: Eligibility to rent a car
     public void eligibilityRentCar(){
         
        for(int i=0; i<customer.size();i++){
            if (customer.get(i).getCanRent()){
                if(true){
                    countTrue = countTrue +1;
                    countFalse= customer.size()-countTrue;
                    
                }
            }  
        }
        System.out.println("Number of customers who can rent: "+countTrue);
        System.out.println("Number of customers who can't rent: "+countFalse);
     
     }
     
     //Step 2e: Display Customer txt file
     public void displayCustomerTxtFile() throws IOException{
         try{
             FileWriter print = new FileWriter("customerOutFile.txt");
             buffWrite = new BufferedWriter(print);
             buffWrite.write("===========================Customers========================================\n");
             buffWrite.write(String.format("%-10s%-20s%-20s%-20s%-20s\n","ID","Name", "Surname","Date Of Birth","Age"));
             buffWrite.write("============================================================================\n");
                for(int i=0; i<customer.size();i++){
                    buffWrite.write(String.format("%-10s%-20s%-20s%-20s\n",customer.get(i).getStHolderId(),customer.get(i).getFirstName(),
                            customer.get(i).getSurName(),customer.get(i).getDateOfBirth()));
                }
             buffWrite.write("\n");
             buffWrite.write("Number of customers who can rent: \t"+countTrue+"\n");
             buffWrite.write("Number of customers who can rent: \t"+countFalse);
             buffWrite.close();
             System.out.println("Customer wrote to file");
         }
         
         catch(IOException ioe){
             System.out.println("Wrote to file!"+ioe.getMessage());
             System.exit(1);
         }
     }
     
     
     //Step 3a: Sort Supplier
     public void sortSupplier(){
         String[] supSort = new String[supplier.size()];
         ArrayList<Supplier> sortNameArray = new ArrayList<>();
         int index = supplier.size();
            for(int i = 0; i<index;i++){
                supSort[i] = supplier.get(i).getName();
            }
                Arrays.sort(supSort);
            for(int y =0;y<index;y++){
                for(int n =0;n<index;n++){
                    if(supSort[y].equals(supplier.get(n).getName())){
                        sortNameArray.add(supplier.get(n));
                    } 
                }
            }
            supplier=sortNameArray;
            System.out.println("Sort Supplier by name");
     }
     
     //Step 3b: Display on Supplier txt file
     public void displaySupplierTxtFile() throws IOException{
         try{
             FileWriter print = new FileWriter("supplierOutFile.txt");
             buffWrite = new BufferedWriter(print);
             buffWrite.write("===========================Suppliers========================================\n");
             buffWrite.write(String.format("%-10s%-20s%-20s%-20s\n","ID", "Name", "Prod Type", "Description"));
             buffWrite.write("============================================================================\n");
                for(int i=0; i<supplier.size();i++){
                    buffWrite.write(String.format("%-10s%-20s%-20s%-20s\n", supplier.get(i).getStHolderId(), supplier.get(i).getName(),
                            supplier.get(i).getProductType(),supplier.get(i).getProductDescription()));
                }
             buffWrite.close();
             System.out.println("Supplier wrote to file");
         }
         
         catch(IOException ioe){
             System.out.println("Wrote to file!"+ioe.getMessage());
             System.exit(1);
         }
     }
     
}
    
    
    /*public class IdComparator implements Comparator<Customer>{
    @Override
    public int compare (Customer o1, Customer o2)
            return o1.getStHolderId().compareTo(o2.getStHolderId());
    }*/
    
   /* public void Age() throws ParseException{
      try{
            for(int i =0; i<11;i++){
                Cus = input.readObject();
                if(Cus.getClass()==Customer.class){
                Customer = (Customer)Cus;
                customer.add(Customer);  
     String s = Customer.getDateOfBirth();
  SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
  Date d = sdf.parse(s);
  Calendar c = Calendar.getInstance();
  c.setTime(d);
  int year = c.get(Calendar.YEAR);
  int month = c.get(Calendar.MONTH) + 1;
  int date = c.get(Calendar.DATE);
  LocalDate l1 = LocalDate.of(year, month, date);
  LocalDate now1 = LocalDate.now();
  Period diff1 = Period.between(l1, now1);
  System.out.println("age:" + diff1.getYears() + "years");
  System.out.println(Customer);
                }
            }
            System.out.println(customer.size());
            //customer.sort;
            
        }
        catch(ClassNotFoundException ioe ){
            System.out.println("Class error"+ioe);
        }
        catch(IOException ioe){
            System.out.println("Error reading ser file"+ioe);
        }
        finally{
            closeFile();
            System.out.println("*** file has been closed ***");               
        }
    }*/

    
        
        
