/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bom_editor.controllers;

import bom_editor.common.Parts;
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
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;

/**
 * FXML Controller class
 *
 * @author Federico
 */
public class ConvertScreenFXMLController implements Initializable {

    @FXML
    private TableView<?> tablelist;
    @FXML
    private TableColumn<?, ?> itemCol;
    @FXML
    private TableColumn<?, ?> refCol;
    @FXML
    private TableColumn<?, ?> descriptionCol;
    @FXML
    private TableColumn<?, ?> packageCol;
    @FXML
    private TableColumn<?, ?> mpnCol;
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
    
    List<Parts> bom_list = new ArrayList();
    ObservableList observable_bom = FXCollections.observableList(bom_list);

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
    
}
