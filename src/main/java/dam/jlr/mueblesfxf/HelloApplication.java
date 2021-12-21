package dam.jlr.mueblesfxf;

import dam.jlr.mueblesfxf.controller.ControllerHandler;
import dam.jlr.mueblesfxf.controller.HelloController;
import dam.jlr.mueblesfxf.controller.LoginController;
import dam.jlr.mueblesfxf.model.Model;
import dam.jlr.mueblesfxf.util.CSV;
import dam.jlr.mueblesfxf.util.HibernateActions;
import dam.jlr.mueblesfxf.util.HibernateUtil;
import dam.jlr.mueblesfxf.util.JdbcUtil;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import org.hibernate.Session;

import javax.persistence.EntityManager;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Objects;

public class HelloApplication extends Application {
    private Scene scene;
    private Stage stage;

    public Scene getScene() {
        return scene;
    }

    public Stage getStage() {
        return stage;
    }

    public void setStage(Stage stage) {
        this.stage = stage;
        this.stage.setScene(null);

        this.stage.show();
    }

    public Session getSession() {
        return session;
    }

    public void setSession(Session session) {
        this.session = session;
    }


    public EntityManager getEntityManager() {
        return entityManager;
    }

    public void setEntityManager(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    private Session session;

    EntityManager entityManager;

    @Override
    public void start(Stage stage) throws IOException {
        ControllerHandler.setHelloApplication(this);
        this.stage = stage;
        Model model = new Model();
        model.setMaterial("Madera");
        model.setPrecio(100);
        model.setTipo("Mueble");
        Model model2 = new Model();
        model2.setMaterial("Silla");
        model2.setPrecio(200);
        model2.setTipo("Mueble");
        Model model3 = new Model();
        model3.setMaterial("Mesa");
        model3.setPrecio(300);
        model3.setTipo("Mueble");
        Model model4 = new Model();
        model4.setMaterial("Silla");
        model4.setPrecio(400);
        model4.setTipo("Mueble");
        Model model5 = new Model();
        model5.setMaterial("Silla");
//add all the models to array
        Model[] models = {model, model2, model3, model4, model5};

//        CSV.toCSV(models);
//        File file = CSV.toCSVfile("hola", CSV.toCSV(models));

        FXMLLoader loader = new FXMLLoader(HelloApplication.class.getResource("login.fxml"));



        this.scene = new Scene(loader.load());
        System.out.println(loader.getController().getClass().getSimpleName());
        LoginController loginController = (LoginController) loader.getController();
        loginController.setHelloApplication(this);

        this.stage.setTitle("Login");
        this.stage.setScene(scene);


        System.out.println("stage setted");


        this.stage.show();

        this.stage.getIcons().add(Imagex.fromPlatformImage(Objects.requireNonNull(HelloApplication.class.getResource("images/icon.png")).toExternalForm()));
    }

    public static void main(String[] args) {

//       for (Model m: HibernateActions.getData()) {
//           System.out.println(m);
//       }
        launch();
    }

    private static class Imagex extends Image {
        //fromPlatformImage()
        public static Image fromPlatformImage(String url) {
            return new Image(url);
        }

        public Imagex(String s) {
            super(s);
        }

        public Imagex(String s, boolean b) {
            super(s, b);
        }

        public Imagex(String s, double v, double v1, boolean b, boolean b1) {
            super(s, v, v1, b, b1);
        }

        public Imagex(String s, double v, double v1, boolean b, boolean b1, boolean b2) {
            super(s, v, v1, b, b1, b2);
        }

        public Imagex(InputStream inputStream) {
            super(inputStream);
        }

        public Imagex(InputStream inputStream, double v, double v1, boolean b, boolean b1) {
            super(inputStream, v, v1, b, b1);
        }

        @Override
        public void cancel() {
            super.cancel();
        }

        @Override
        public int hashCode() {
            return super.hashCode();
        }

        @Override
        public boolean equals(Object obj) {
            return super.equals(obj);
        }

        @Override
        protected Object clone() throws CloneNotSupportedException {
            return super.clone();
        }

        @Override
        public String toString() {
            return super.toString();
        }

        @Override
        protected void finalize() throws Throwable {
            super.finalize();
        }
    }

    private void setScene(Scene scene) {

    }
    //change scene

}