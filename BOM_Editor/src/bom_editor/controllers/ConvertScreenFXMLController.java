/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bom_editor.controllers;

import bom_editor.common.BOM_List;
import bom_editor.common.Parts;
import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.FileChooser;

/**
 * FXML Controller class
 *
 * @author Federico
 */
public class ConvertScreenFXMLController implements Initializable {
    
    List<Parts> list = new ArrayList();
    ObservableList observable_bom = FXCollections.observableList(list);
    BOM_List bom_list = new BOM_List(list);
    
    @FXML
    private TableView<Parts> tablelist;
    @FXML
    private TableColumn<Parts, String> itemCol;
    @FXML
    private TableColumn<Parts, String> refCol;
    @FXML
    private TableColumn<Parts, String> descriptionCol;
    @FXML
    private TableColumn<Parts, String> packageCol;
    @FXML
    private TableColumn<Parts, String> mpnCol;
    @FXML
    private WebView webview;
    private WebEngine engine;
    @FXML
    private Button loadFile;
    @FXML
    private Button saveFileBtn;
    @FXML
    private Button saveAsButton;
    @FXML
    private Button createFileBtn;
    
    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        engine=webview.getEngine();
        engine.load("http://www.digikey.com");
    }    

    @FXML
    private void loadFileAction(ActionEvent event) {
        //Open Dialog Window to select KiCad .csv file
        FileChooser fc = new FileChooser();
        File selectedFile = fc.showOpenDialog(null);
        
        //Create list of parts
        bom_list.loadFile(selectedFile.toString());
        
        //Populate table
        itemCol.setCellValueFactory(new PropertyValueFactory<Parts,String>("itemNumber"));
        refCol.setCellValueFactory(new PropertyValueFactory<>("designator"));
        descriptionCol.setCellValueFactory(new PropertyValueFactory<>("partvalue"));
        packageCol.setCellValueFactory(new PropertyValueFactory<>("packaging"));
        mpnCol.setCellValueFactory(new PropertyValueFactory<>("mpn"));
        
        tablelist.getItems().setAll(observable_bom);
        print_list(observable_bom);
    }

    @FXML
    private void saveAction(ActionEvent event) {
    }

    @FXML
    private void saveAsAction(ActionEvent event) {
    }

    @FXML
    private void createFileBtn(ActionEvent event) {
    }
    
    public void print_list(ObservableList<Parts> list){
    
        for(int i=0;i<list.size();i++){
            System.out.print(list.get(i).getitemNumber());
            System.out.print(" "+list.get(i).getDesignator());
            System.out.print(" "+list.get(i).getPackaging());
            System.out.print(" "+list.get(i).getQuantity());
            System.out.print(" "+list.get(i).getValue());
            System.out.println(" "+list.get(i).getMpn());
        }
    }
    
}
