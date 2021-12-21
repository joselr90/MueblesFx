package dam.jlr.mueblesfxf.view;

import dam.jlr.mueblesfxf.HelloApplication;
import dam.jlr.mueblesfxf.controller.Controller;
import dam.jlr.mueblesfxf.controller.ControllerHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;

public class SceneManager {
    private static HelloApplication helloApplication=ControllerHandler.getHelloApplication();
    public static void setScene(String fxml){
        Scene scene=null;
        FXMLLoader loader=new FXMLLoader(HelloApplication.class.getResource(fxml));
        try {

            scene=new Scene(loader.load());
            Class<?> controllerClass=loader.getController().getClass();

            Controller controller=loader.getController();
            String controllerName=loader.getController().getClass().getSimpleName();
            System.out.println(Class.forName(loader.getController().getClass().getName()).cast(controller));
            //reflection call static method

            ControllerHandler.class.getDeclaredMethod("set"+controllerName,controllerClass).invoke(null,Class.forName(loader.getController().getClass().getName()).cast(controller));
//call static method with reflection
            System.out.println(Arrays.toString(ControllerHandler.class.getDeclaredMethods()));


        } catch (IOException | ClassNotFoundException | NoSuchMethodException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

        helloApplication.getStage().setScene(scene);
        helloApplication.getStage().show();

    }

}
