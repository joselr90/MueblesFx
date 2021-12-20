module dam.jlr.mueblesfxf {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;
    requires java.persistence;
    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires validatorfx;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;
    requires eu.hansolo.tilesfx;
    requires org.hibernate.orm.core;
    requires java.naming;
    requires java.sql;
    requires org.apache.commons.lang3;
    requires mysql.connector.java;


    requires java.desktop;
    opens dam.jlr.mueblesfxf to javafx.fxml,org.hibernate.orm.core;
    exports dam.jlr.mueblesfxf;
    exports dam.jlr.mueblesfxf.model;
    opens dam.jlr.mueblesfxf.model to javafx.fxml, org.hibernate.orm.core;
    exports dam.jlr.mueblesfxf.controller;
    opens dam.jlr.mueblesfxf.controller to javafx.fxml, org.hibernate.orm.core;
    exports dam.jlr.mueblesfxf.util;
    opens dam.jlr.mueblesfxf.util to javafx.fxml, org.hibernate.orm.core;
}