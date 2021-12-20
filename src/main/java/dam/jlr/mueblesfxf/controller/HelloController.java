package dam.jlr.mueblesfxf.controller;

import dam.jlr.mueblesfxf.HelloApplication;
import dam.jlr.mueblesfxf.components.MyRadioButton;
import dam.jlr.mueblesfxf.model.Model;
import dam.jlr.mueblesfxf.util.HibernateActions;
import dam.jlr.mueblesfxf.util.HibernateUtil;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseButton;
import javafx.stage.Stage;


import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.io.IOException;
import java.net.URL;
import java.util.Comparator;
import java.util.List;
import java.util.ResourceBundle;
import java.util.regex.Pattern;

public class HelloController implements Initializable {
    private static Stage stage;

    public  void setStage(Stage stage) {
       stage = stage;
    }

    @FXML
    private TextField txtID;
    @FXML
    private TextField textMaterial;
    @FXML
    private RadioButton radiobuttonPrecio;
    @FXML
    private TextField textPrecio;
    @FXML
    private RadioButton radiobuttonTipo;
    @FXML
    private TextField txtTipo;
    @FXML
    private RadioButton radiobuttonID;
    @FXML
    private RadioButton radiobuttonMaterial;
    @FXML
    private TextField txtBuscar;
    @FXML
    private TableColumn<Model, String> precioColumn;
    @FXML
    private TableColumn<Model, String> materialColumn;
    @FXML
    private TableColumn<Model, Integer> idcolumn;

    @FXML
    private TableColumn<Model, String> tipoColumn;
    @FXML
    private TableView<Model> table = new TableView<>();
    @FXML
    private ToggleGroup m;
    @FXML
    private RadioButton radioButtonAny;
    @FXML
    private ToggleButton toggle;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        radiobuttonID.setSelected(true);
        toggle.setFocusTraversable(false);
        initTable();
        initRadioButtons();
        initTxtBuscar();
        initDoubleClick();
        textPrecio.setTextFormatter(new3DecimalFormatter());
        txtBuscar.setTextFormatter(new TextFormatter<>(c -> (c.getControlNewText().matches("\\d*")) ? c : null));

    }


    @FXML
    public void modify(ActionEvent actionEvent) {
        if (txtID.getText().length() > 0) {
            Model model = new Model();
            model.setId(Integer.parseInt(txtID.getText()));
            model.setMaterial(textMaterial.getText());
            model.setPrecio(Double.parseDouble(textPrecio.getText()));
            model.setTipo(txtTipo.getText());
            HibernateActions.modify(model);
            //show popup alert
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Modificado");
            alert.setHeaderText("Modificado");
            alert.setContentText("¿Seguro que quieres modificar?");

            alert.showAndWait();
            initTable();

        }
    }
//create enum
    public enum Material {
        MDF,
        ALUMINIO,
        PLASTICO,
        OTRO
    }
    @FXML
    public void create(ActionEvent actionEvent) {
        Model model = new Model();
        model.setMaterial(textMaterial.getText());
        model.setPrecio(Double.parseDouble(textPrecio.getText()));
        model.setTipo(txtTipo.getText());
        HibernateActions.insert(model);
        initTable();
    }

    @FXML
    public void delete(ActionEvent actionEvent) {
        ObservableList<Model> selectedItems = table.getSelectionModel().getSelectedItems();
        for (Model model : selectedItems) {
            HibernateActions.delete(model);
        }
        //show popup alert
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Eliminando...");
        alert.setHeaderText("Eliminando...¿Deseas eliminar?");
        alert.setContentText("¿Seguro que quieres eliminar?");
        alert.showAndWait();
        initTable();
    }

    public void initTable() {
        //multiselect
        table.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        idcolumn.setCellValueFactory(new PropertyValueFactory<Model, Integer>("id"));
        materialColumn.setCellValueFactory(new PropertyValueFactory<Model, String>("material"));
        precioColumn.setCellValueFactory(new PropertyValueFactory<>("precio2"));
        tipoColumn.setCellValueFactory(new PropertyValueFactory<Model, String>("tipo"));
//textPrecio only double
//show popup menu if click on table
       //create dialog to modify if secondary click



                table.getItems().setAll(HibernateActions.getData());
            }


    private static TextFormatter<Double> new3DecimalFormatter() {
        Pattern decimalPattern = Pattern.compile("-?\\d*(\\.\\d{0,2})?");
        return new TextFormatter<>(c -> (decimalPattern.matcher(c.getControlNewText()).matches()) ? c : null);
    }

    public void initRadioButtons() {
        //radio button no deseleccionable
        radiobuttonID.setSelected(true);
        //set id button true
        radiobuttonID.setSelected(true);
        radiobuttonPrecio.setSelected(false);
        radiobuttonMaterial.setSelected(false);
        radiobuttonTipo.setSelected(false);
        //listener radio buttons
        radiobuttonID.setOnAction(event -> {
            //if txtBuscar is not number
            if (!txtBuscar.getText().matches("\\d*")) {
                txtBuscar.setText("");
            }
                    if (radiobuttonID.isSelected()) {
                        radiobuttonID.setSelected(true);
                        radiobuttonMaterial.setSelected(false);
                        radiobuttonPrecio.setSelected(false);
                        radiobuttonTipo.setSelected(false);
                        //txtBuscar only int
                        txtBuscar.setTextFormatter(new TextFormatter<>(c -> (c.getControlNewText().matches("\\d*")) ? c : null));
                        radiobuttonID.setSelected(true);

                    }
                }
        );
        radiobuttonMaterial.setOnAction(event -> {
                    if (radiobuttonMaterial.isSelected()) {
                        txtBuscar.setTextFormatter(null);
                        radiobuttonMaterial.setSelected(true);
                        radiobuttonID.setSelected(false);
                        radiobuttonPrecio.setSelected(false);
                        radiobuttonTipo.setSelected(false);
                        ;

                    }
                }
        );
        radiobuttonPrecio.setOnAction(event -> {
                    if (radiobuttonPrecio.isSelected()) {
                        radiobuttonID.setSelected(false);
                        radiobuttonMaterial.setSelected(false);
                        radiobuttonTipo.setSelected(false);
                        txtBuscar.setTextFormatter(new3DecimalFormatter());


                    }

                }
        );
        radiobuttonTipo.setOnAction(event -> {
                    if (radiobuttonTipo.isSelected()) {
                        txtBuscar.setTextFormatter(null);
                        radiobuttonID.setSelected(false);
                        radiobuttonMaterial.setSelected(false);
                        radiobuttonPrecio.setSelected(false);

                    }
                }
        );
        radioButtonAny.setOnAction(event -> {
                    if (radioButtonAny.isSelected()) {
                        txtBuscar.setTextFormatter(null);
                        radioButtonAny.setSelected(true);

                    }
                }

        );

    }

    public void initTxtBuscar() {
        txtBuscar.textProperty().addListener((observable, oldValue, newValue) -> {
                    System.out.println(newValue);
                    if (newValue.length() > 0) {
                        if (radiobuttonID.isSelected()) {

                            table.getItems().setAll(HibernateActions.getById(Integer.parseInt(newValue)));

                        } else if (radiobuttonMaterial.isSelected()) {
                            txtBuscar.setTextFormatter(null);
                            table.getItems().setAll(HibernateActions.getByMaterial(newValue));

                        } else if (radiobuttonPrecio.isSelected()) {
                            table.getItems().setAll(HibernateActions.getByPrecio(Double.parseDouble(newValue)));

                        } else if (radiobuttonTipo.isSelected()) {
                            txtBuscar.setTextFormatter(null);
                            table.getItems().setAll(HibernateActions.getByTipo(newValue));
                        } else if (radioButtonAny.isSelected()) {
                            txtBuscar.setTextFormatter(null);
                            EntityManager entityMgr = HibernateUtil.getSessionFactory().createEntityManager();


                            //search by any
                            String query = "SELECT * FROM muebles WHERE id LIKE '%" + newValue + "%' OR material LIKE '%" + newValue + "%' OR precio LIKE '%" + newValue + "%' OR tipo LIKE '%" + newValue + "%'";
                            Query q = entityMgr.createNativeQuery(query, Model.class);
                            List<Model> list = q.getResultList();
                            table.getItems().setAll(list);
                        } else {
                            table.getItems().setAll(HibernateActions.getData());

                        }

                    } else {

                        table.getItems().setAll(HibernateActions.getData());
                    }

                }
        );
        //only numbers in txtPrecio


    }

    //method double click on table
    public void initDoubleClick() {
        table.setOnMouseClicked(event -> {
            if (event.getClickCount() == 2) {
                Model model = table.getSelectionModel().getSelectedItem();
                if (model != null) {
                    txtID.setText(String.valueOf(model.getId()));
                    textMaterial.setText(model.getMaterial());
                    textPrecio.setText(String.valueOf(model.getPrecio()));
                    txtTipo.setText(model.getTipo());
                }
            }
        });
    }

    public TextFormatter onlyIntegers() {
        Pattern integerPattern = Pattern.compile("\\d*");
        return new TextFormatter<>(c -> (integerPattern.matcher(c.getControlNewText()).matches()) ? c : null);
    }
    public  void changeScene(){
        try {
             FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("view.fxml"));
           stage.setScene(new Scene(fxmlLoader.load()));

            stage.setTitle("Muebles");

            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void clear(ActionEvent actionEvent) {
        txtID.clear();
        textMaterial.clear();
        textPrecio.clear();
        txtTipo.clear();
        toggle.setSelected(false);
        toggle.setFocusTraversable(false);
    }
}
