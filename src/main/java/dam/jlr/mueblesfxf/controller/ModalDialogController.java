package dam.jlr.mueblesfxf.controller;

import dam.jlr.mueblesfxf.model.Model;
import dam.jlr.mueblesfxf.util.Data;
import dam.jlr.mueblesfxf.util.HibernateActions;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class ModalDialogController extends Controller implements Initializable {
    public HelloController getHelloController() {
        return helloController;
    }

    public void setHelloController(HelloController helloController) {
        this.helloController = helloController;
    }

    private HelloController helloController;
     ArrayList<Model> list;
    @javafx.fxml.FXML
    private Button importar;
    @javafx.fxml.FXML
    private TableColumn<Model,String>  materialColumn;

    public ArrayList<Model> getList() {
        return list;
    }

    public void setList(ArrayList<Model> list) {
        this.list = list;
    }

    @javafx.fxml.FXML
    private TableColumn<Model,Integer>  idcolumn;
    @javafx.fxml.FXML
    private TableColumn<Model,String> tipoColumn;
    @javafx.fxml.FXML
    private TableView<Model> table;
    @javafx.fxml.FXML
    private TableColumn<Model,String>  precioColumn;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        helloController=ControllerHandler.getHelloController();
        helloController.refreshTable();

        list= Data.getModels();
initTable();
        System.out.println(list);
    }


    @javafx.fxml.FXML
    public void importar(ActionEvent actionEvent) {
        //get selected items
        ObservableList<Model> selectedItems = table.getSelectionModel().getSelectedItems();
        //add them to the list
        ArrayList<Model> list2 = new ArrayList<>(selectedItems);
        //add them to the database
        for (Model model : list2) {
            model.setId(0);
            HibernateActions.save(model);
        }

        //remove them from the table

        //add them to the database

        //update the table
        initTable();
        helloController.refreshTable();
    }

    public void initTable(){
        ObservableList<Model> list2= FXCollections.observableArrayList(list);
//        table.setItems(list2);
        table.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        idcolumn.setCellValueFactory(new PropertyValueFactory<Model, Integer>("id"));
        materialColumn.setCellValueFactory(new PropertyValueFactory<Model, String>("material"));
        precioColumn.setCellValueFactory(new PropertyValueFactory<>("precio2"));
        tipoColumn.setCellValueFactory(new PropertyValueFactory<Model, String>("tipo"));
        table.getItems().setAll(list2);
        System.out.println(list2);

    }
}
