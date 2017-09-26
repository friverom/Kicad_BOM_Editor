/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bom_editor.common;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * This class creates a BOM list and provides all methods to process it
 * @author Federico Rivero
 */
public class BOM_List {
    
    List<Parts> list;
    String filename;
    
    /**
     * Creates an Array List to hold components
     * @param list Reference to "List"
     */
    public BOM_List(List<Parts> list){
        this.list= list;
    }
    /**
     * Reads KiCad PCB BOM and creates the array list
     * @param filename 
     */
    public void loadFile(String filename){
        this.filename=filename;
        this.createList();
    }
    //This method removes the "" from each item STring
    private String cleanItem(String item){
        
        String txt;
        
        if(item.startsWith("\"")==true){
            txt = ""+item.substring(item.indexOf("\"")+1,item.lastIndexOf("\""));
            return txt;
        }
        else {
            return ""+item;
        }
    }
    /**
     * Edit an Item from the parts lists
     * @param item 
     * @param index 
     */
    public void editList(Parts item, int index){
        list.set(index, item);
    }
    /**
     * Removes an Item from the part list
     * @param index 
     */
    public void removeItem(int index){
        list.remove(index);
    }
    /**
     * Returns an item from the part list
     * @param index
     * @return 
     */
    public Parts getItem(int index){
        return list.get(index);
    }
    /**
     * Returns the size of the parts list
     * @return size
     */
    public int listSize(){
        return list.size();
    }
    /**
     * Saves the array list to file
     */
    public void saveList(){
        BufferedWriter output;
        String line="";
        Parts item = new Parts();
       
        try {
            output=new BufferedWriter(new FileWriter(filename));
                        
            for(int i=0;i<list.size();i++){
                item=list.get(i);
                line="";
                line=line+i+";";
                line=line+item.getDesignator()+";";
                line=line+item.getPackaging()+";";
                line=line+item.getQuantity()+";";
                line=line+item.getValue()+";";
                line=line+item.getMpn()+";";
                output.write(line+"\n");
            }
            output.close();
        } catch (IOException ex) {
            Logger.getLogger(BOM_List.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    //This method read the KiCad PCB BOM and creates the array list
    private void createList(){
        BufferedReader input;
        String line,mpn;
        
        try {
            input=new BufferedReader(new FileReader(this.filename));
            line=input.readLine();
            
            while(line!=null){
                String[] items=line.split(";");
                String designator=cleanItem(items[1]);
                String packaging=cleanItem(items[2]);
                String quantity=cleanItem(items[3]);
                String description=cleanItem(items[4]);
                if(items.length<6){
                    mpn="MPN";
                }
                else{
                    mpn=items[5];
                }
                this.list.add(new Parts(designator,packaging,quantity,description,mpn));
                line=input.readLine();
            }
            input.close();
        } catch (FileNotFoundException ex) {
            System.out.println("Error opening file "+this.filename);
            Logger.getLogger(BOM_List.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            System.out.println("Error reading file "+this.filename);
            Logger.getLogger(BOM_List.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
}
