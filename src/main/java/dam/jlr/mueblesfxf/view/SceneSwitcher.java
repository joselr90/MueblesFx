package dam.jlr.mueblesfxf.view;

import dam.jlr.mueblesfxf.HelloApplication;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class SceneSwitcher {
    Stage stage;
    Scene scene;
    HelloApplication helloApplication;

    public HelloApplication getHelloApplication() {
        return helloApplication;
    }

    public void setHelloApplication(HelloApplication helloApplication) {
        this.helloApplication = helloApplication;
    }

    public SceneSwitcher(Stage stage, Scene scene) {
        this.stage = stage;
        this.scene = scene;
    }
    public SceneSwitcher() {
    }

    public Stage getStage() {
        return stage;
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    public Scene getScene() {
        return scene;
    }

    public void setScene(Scene scene) {
        this.scene = scene;
    }

    public void changeScene(String fxml) {
        try {
            this.stage.setScene(this.scene);
            this.stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
