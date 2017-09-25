/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bom_editor.common;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 *
 * @author Federico
 */
public class Parts {

    private final SimpleStringProperty designator;
    private final SimpleStringProperty packaging;
    private final SimpleStringProperty quantity;
    private final SimpleStringProperty partvalue;
    private final SimpleStringProperty mpn;

    public Parts(String designator, String packaging, String quantity, String partvalue, String mpn) {
        this.designator= new SimpleStringProperty(designator);
        this.packaging= new SimpleStringProperty(packaging);
        this.quantity= new SimpleStringProperty(quantity);
        this.partvalue= new SimpleStringProperty(partvalue);
        this.mpn=new SimpleStringProperty(mpn);
    }

    public String getMpn() {
        return mpn.get();
    }

    public void setMpn(String value) {
        mpn.set(value);
    }

    public StringProperty mpnProperty() {
        return mpn;
    }
    
    public String getValue() {
        return partvalue.get();
    }

    public void setValue(String value) {
        partvalue.set(value);
    }

    public StringProperty valueProperty() {
        return partvalue;
    }
    
    public String getQuantity() {
        return quantity.get();
    }

    public void setQuantity(String value) {
        quantity.set(value);
    }

    public StringProperty quantityProperty() {
        return quantity;
    }
    
    
    public String getPackaging() {
        return packaging.get();
    }

    public void setPackaging(String value) {
        packaging.set(value);
    }

    public StringProperty packagingProperty() {
        return packaging;
    }
    
    public String getDesignator() {
        return designator.get();
    }

    public void setDesignator(String value) {
        designator.set(value);
    }

    public StringProperty designatorProperty() {
        return designator;
    }

   
}
