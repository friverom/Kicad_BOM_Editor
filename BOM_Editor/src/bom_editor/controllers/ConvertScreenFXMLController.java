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
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceDialog;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellEditEvent;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.FileChooser;
import javafx.util.Callback;

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
    private TableColumn<Parts, String> quantityCol;
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
        String page="";
        //Select which page to use
        List<String> list = new ArrayList();
        list.add("www.digikey.com");
        list.add("www.mouser.com");
        
        ChoiceDialog<String> dialog = new ChoiceDialog<>("",list);
        dialog.setTitle("Parts Provider");
        dialog.setHeaderText("Select Parts Provider");
        dialog.setContentText("Select web page");
        Optional<String> selection = dialog.showAndWait();
        if(selection.isPresent()){
            page=selection.get();
        }
        //Load Selected web page to webview
        engine=webview.getEngine();
        engine.load("http://"+page);
    }    

    @FXML
    private void loadFileAction(ActionEvent event) {
        //Open Dialog Window to select KiCad .csv file
        FileChooser fc = new FileChooser();
        File selectedFile = fc.showOpenDialog(null);
        
        //Create list of parts
        bom_list.loadFile(selectedFile.toString());
        
        //Populate table
        tablelist.setEditable(true);
        itemCol.setCellValueFactory(new PropertyValueFactory<Parts,String>("itemNumber"));
        refCol.setCellValueFactory(new PropertyValueFactory<>("designator"));
        descriptionCol.setCellValueFactory(new PropertyValueFactory<>("value"));
        quantityCol.setCellValueFactory(new PropertyValueFactory<Parts, String>("quantity"));
        packageCol.setCellValueFactory(new PropertyValueFactory<>("packaging"));
        
        mpnCol.setCellValueFactory(new PropertyValueFactory<>("mpn"));
        mpnCol.setCellFactory(TextFieldTableCell.forTableColumn());
        mpnCol.setOnEditCommit(
            new EventHandler<CellEditEvent<Parts, String>>() {
                @Override
                public void handle(CellEditEvent<Parts, String> t) {
                    ((Parts) t.getTableView().getItems().get(
                            t.getTablePosition().getRow())
                            ).setMpn(t.getNewValue());
                }
            }
        );

        
        tablelist.getItems().setAll(observable_bom);
       // print_list(observable_bom);
    }

    @FXML
    private void saveAction(ActionEvent event) {
        bom_list.saveList();
    }

    @FXML
    private void saveAsAction(ActionEvent event) {
        FileChooser fc = new FileChooser();
        File selectedFile = fc.showSaveDialog(null);
        bom_list.saveAsList(selectedFile.toString());
    }

    @FXML
    private void createFileBtn(ActionEvent event) {
        bom_list.createSeeedFile();
    }
    
    @FXML
    private void deleteRowBtn(ActionEvent event){
        Parts selectedPart = tablelist.getSelectionModel().getSelectedItem();
        bom_list.removeItem(Integer.parseInt(selectedPart.getitemNumber()));
        tablelist.getItems().remove(selectedPart);
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
