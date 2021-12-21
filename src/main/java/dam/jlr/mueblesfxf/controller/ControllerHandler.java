package dam.jlr.mueblesfxf.controller;

import dam.jlr.mueblesfxf.HelloApplication;

public class ControllerHandler {
    private static HelloApplication helloApplication;
    private static HelloController helloController;
    private static LoginController loginController;
    private static ModalDialogController modalDialogController;

    public static HelloApplication getHelloApplication() {
        return helloApplication;
    }

    public static void setHelloApplication(HelloApplication helloApplication) {
        ControllerHandler.helloApplication = helloApplication;
    }

    public static HelloController getHelloController() {
        return helloController;
    }

    public static void setHelloController(HelloController helloController) {
        ControllerHandler.helloController = helloController;
    }

    public static LoginController getLoginController() {
        return loginController;
    }

    public static void setLoginController(LoginController loginController) {
        ControllerHandler.loginController = loginController;
    }

    public static ModalDialogController getModalDialogController() {
        return modalDialogController;
    }

    public static void setModalDialogController(ModalDialogController modalDialogController) {
        ControllerHandler.modalDialogController = modalDialogController;
    }

    public static DbController getDbController() {
        return dbController;
    }

    public static void setDbController(DbController dbController) {
        ControllerHandler.dbController = dbController;
    }

    private static DbController dbController;


}
