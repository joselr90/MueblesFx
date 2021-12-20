package dam.jlr.mueblesfxf.controller;

import dam.jlr.mueblesfxf.HelloApplication;
import dam.jlr.mueblesfxf.model.Model;
import dam.jlr.mueblesfxf.util.JdbcUtil;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class DbController implements Initializable {

    public HelloApplication getHelloApplication() {
        return helloApplication;
    }

    public void setHelloApplication(HelloApplication helloApplication) {
        this.helloApplication = helloApplication;
    }

    private  HelloApplication helloApplication;
    @FXML
    private ListView<String> listView;

    @Override
    public void initialize(java.net.URL location, java.util.ResourceBundle resources) {
//        loadData();
        //arraylist to observable list
        ObservableList<String> prueba = FXCollections.observableArrayList(JdbcUtil.executeMySQLQuery());
//        for (String s : prueba) {
//            System.out.println(s);
//            //string to string property
//            prueba.add(s);
//
//        }
//        //add to listview
        listView.setItems(prueba);



    }


    @FXML
    private TextField textField;


    @FXML
    public void createdb(ActionEvent actionEvent) {
    }
    public void loadData(){
        ArrayList<String> dbnames= JdbcUtil.executeMySQLQuery();
        for (String dbname:dbnames) {
            System.out.println(dbname);


        }
        listView.getItems().clear();
        ObservableList<String> prueba = FXCollections.observableArrayList(JdbcUtil.executeMySQLQuery());
        listView.setItems(prueba);
    }

    @FXML
    public void backScene(ActionEvent actionEvent) {
        JdbcUtil.closeConnection();
        FXMLLoader loader = new FXMLLoader(HelloApplication.class.getResource("login.fxml"));
        try {
            Scene scene = new Scene(loader.load());
            LoginController loginController = loader.getController();
            loginController.setHelloApplication(helloApplication);
            helloApplication.getStage().close();
            helloApplication.getStage().setTitle("Muebles Fx");
            helloApplication.getStage().setScene(scene);


            helloApplication.getStage().show();


        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void renameDB(ActionEvent actionEvent) {
        //get selected item
        String selectedItem = listView.getSelectionModel().getSelectedItem();
        System.out.println(selectedItem);
        String newName = textField.getText();
        System.out.println(newName);
        //alert confirmation
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation Dialog");
        alert.setHeaderText("Look, a Confirmation Dialog");
        alert.setContentText("Are you ok with this?");
        alert.showAndWait();
        JdbcUtil.renameDB(selectedItem,newName);
        //update listview
        listView.getItems().clear();
        ObservableList<String> prueba = FXCollections.observableArrayList(JdbcUtil.executeMySQLQuery());
        listView.setItems(prueba);
        loadData();
    }

    @FXML
    public void removeDB(ActionEvent actionEvent) {
    }
    public void loadData2(){
        ArrayList<String> dbnames= JdbcUtil.executeMySQLQuery();
        for (String dbname:dbnames) {
            System.out.println(dbname);
}
    }
}
