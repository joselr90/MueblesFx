package dam.jlr.mueblesfxf.util;

import dam.jlr.mueblesfxf.model.Model;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;

import java.sql.Connection;
import java.sql.Statement;
import java.util.Properties;
import java.sql.*;

public class HibernateUtil {
    private static SessionFactory sessionFactory;
    private static String databaseName="prog_muebles";
    public static SessionFactory getSessionFactory() {
        Configuration configuration = new Configuration();
        Properties properties = new Properties();
        properties.put(Environment.DRIVER, "com.mysql.jdbc.Driver");
        properties.put(Environment.URL, "jdbc:mysql://localhost:3306/" + databaseName + "?useSSL=false&createDatabaseIfNotExist=true");
        properties.put(Environment.USER, "root");
        properties.put(Environment.PASS, "");

        properties.put(Environment.DIALECT, "org.hibernate.dialect.MySQL5Dialect");
        properties.put(Environment.SHOW_SQL, "true");
        properties.put(Environment.HBM2DDL_AUTO, "update");
        configuration.addAnnotatedClass(Model.class);
        configuration.setProperties(properties);

        sessionFactory=configuration.buildSessionFactory();


     return sessionFactory;
    }

}


