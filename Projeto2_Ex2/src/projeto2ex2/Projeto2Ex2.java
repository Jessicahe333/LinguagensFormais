/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projeto2ex2;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author Fernanda
 */
public class Projeto2Ex2 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
         // The name of the file to open.
        String fileName = "output.asm";

        // This will reference one line at a time
        String line = null;

        try {
            List<Double> nums = new ArrayList<>();
            double result; 
            // FileReader reads text files in the default encoding.
            FileReader fileReader = 
                new FileReader(fileName);

            try ( // Always wrap FileReader in BufferedReader.
                    BufferedReader bufferedReader = new BufferedReader(fileReader)) {
                while ((line = bufferedReader.readLine()) != null) {
                    System.out.println(line);
                    
                    if (IsNumber(line)){
                         Pattern p = Pattern.compile("[0-9][0-9]*");
                         Matcher m = p.matcher(line);
                         while(m.find()) {
                            nums.add(Double.parseDouble(m.group()));
                        }
                    }
                    if (IsAdd(line)){
                    double opt1 = nums.remove(nums.size()-1);
                    double opt2 = nums.remove(nums.size()-1);
                    result = opt2 + opt1;

                    nums.add(result);
                }
                    if (IsSub(line)){
                    double opt1 = nums.remove(nums.size()-1);
                    double opt2 = nums.remove(nums.size()-1);
                    result = opt2 - opt1;

                    nums.add(result);
                }
                    if (IsMult(line)){
                    double opt1 = nums.remove(nums.size()-1);
                    double opt2 = nums.remove(nums.size()-1);
                    result = opt2 * opt1;

                    nums.add(result);
                }
                    if (IsDiv(line)){
                    double opt1 = nums.remove(nums.size()-1);
                    double opt2 = nums.remove(nums.size()-1);
                    result = opt2 / opt1;

                    nums.add(result);
                }
                    System.out.println(nums);
                }
                // Always close files.
            }         
        }
        catch(FileNotFoundException ex) {
            System.out.println(
                "Unable to open file '" + 
                fileName + "'");                
        }
        catch(IOException ex) {
            System.out.println(
                "Error reading file '" 
                + fileName + "'");                  
            // Or we could just do this: 
            // ex.printStackTrace();
        }
        
    }
    private static boolean IsNumber(String s){
        return s.startsWith("PUSH ");
    }
    private static boolean IsSub(String s){
        return s.equals("SUB");
    }
    private static boolean IsMult(String s){
        return s.equals("MULT");
    }
    private static boolean IsDiv(String s){
        return s.equals("DIV");
    }
    private static boolean IsAdd(String s){
        return s.equals("ADD");
    }
}
