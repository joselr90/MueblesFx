package dam.jlr.mueblesfxf.controller;

import dam.jlr.mueblesfxf.HelloApplication;
import dam.jlr.mueblesfxf.util.JdbcUtil;

import javafx.event.ActionEvent;
import javafx.event.EventDispatchChain;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.stage.Window;


import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class LoginController extends Controller implements Initializable {

    HelloApplication helloApplication;
    @FXML
    private Button button;
    @FXML
    private TextField txtUser;
    @FXML
    private PasswordField txtPassword;

    public HelloApplication getHelloApplication() {
        return helloApplication;
    }

    public void setHelloApplication(HelloApplication helloApplication) {
        this.helloApplication = helloApplication;
    }





    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
//        this.sceneSwitcher=new SceneSwitcher();



    }
    @FXML
    public void entrar(ActionEvent actionEvent) {
        JdbcUtil.getConnection(txtUser.getText(),txtPassword.getText());
      FXMLLoader loader = new FXMLLoader(HelloApplication.class.getResource("dbselection.fxml"));
        try {
            Scene scene = new Scene(loader.load());
            DbController dbController=loader.getController();
            dbController.setHelloApplication(helloApplication);
            helloApplication.getStage().close();
            helloApplication.getStage().setTitle("Muebles Fx");
            helloApplication.getStage().setScene(scene);
            JdbcUtil.setPassword(txtPassword.getText());
            JdbcUtil.setUsername(txtUser.getText());

            helloApplication.getStage().show();


        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    }
