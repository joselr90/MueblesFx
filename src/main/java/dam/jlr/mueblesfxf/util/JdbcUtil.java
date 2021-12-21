package dam.jlr.mueblesfxf.util;

import java.sql.*;
import java.util.ArrayList;
import java.util.Properties;

public class JdbcUtil {
    public static final String DRIVER = "com.mysql.jdbc.Driver";
    public static final String URL = "jdbc:mysql://localhost:3306/muebles";
    static Connection conn = null;
    static String username = "";
    static String password = "";

    public static void getConnection() {
        Properties connectionProps = new Properties();
        connectionProps.put("user", username);
        connectionProps.put("password", password);
        connectionProps.put("useSSL", "false");


        connectionProps.put("createDatabaseIfNotExist", "true");

        try {
            Class.forName("com.mysql.jdbc.Driver").getDeclaredConstructor().newInstance();
            conn = DriverManager.getConnection(
                    "jdbc:" + "mysql" + "://" +
                            "localhost" +
                            ":" + "3306" + "/" +
                            "",
                    connectionProps);
        } catch (SQLException ex) {
            // handle any errors
            ex.printStackTrace();
        } catch (Exception ex) {
            // handle any errors
            ex.printStackTrace();
        }
    }

    public static void getConnection(String username, String password) {
        Properties connectionProps = new Properties();
        connectionProps.put("user", username);
        connectionProps.put("password", password);
        connectionProps.put("useSSL", "true");


        connectionProps.put("createDatabaseIfNotExist", "true");

        try {
            Class.forName("com.mysql.jdbc.Driver").getDeclaredConstructor().newInstance();
            conn = DriverManager.getConnection(
                    "jdbc:" + "mysql" + "://" +
                            "localhost" +
                            ":" + "3306" + "/" +
                            "",
                    connectionProps);
        } catch (SQLException ex) {
            // handle any errors
            ex.printStackTrace();
//            Alert alert = new Alert(Alert.AlertType.ERROR);
//            alert.setTitle("Error");
//            alert.setHeaderText("Error al conectar con la base de datos");
//            alert.setContentText("Por favor, revise los datos introducidos");
//            alert.showAndWait();

        } catch (Exception ex) {
            // handle any errors
            ex.printStackTrace();
        }
    }

    public static void closeConnection() {
        try {
            if (conn != null) {
                conn.close();
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public static void createdbifnotexist(String dbName) {
        getConnection(username, password);
        Statement stmt = null;
        ResultSet resultset = null;
        try {
            stmt = conn.createStatement();
            resultset = stmt.executeQuery("SHOW DATABASES LIKE '" + dbName + "'");
            if (!resultset.next()) {
                stmt.executeUpdate("CREATE DATABASE " + dbName);
                System.out.println("Database " + dbName + " created.");

            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public static void renameDB(String dbName, String newName) {
        getConnection(username, password);
        Statement stmt = null;
        ResultSet resultset = null;
        try {
            stmt = conn.createStatement();
            resultset = stmt.executeQuery("SHOW DATABASES LIKE '" + dbName + "'");
            if (resultset.next()) {
                //create new database
                stmt.executeUpdate("CREATE DATABASE " + newName);
                System.out.println("Database " + newName + " created.");
                //


            }
            //drop old database

        } catch (SQLException ex) {
            ex.printStackTrace();

        }


    }


    public static boolean connecttoDB(String dbName) {

        conn = null;
        try {
            Class.forName("com.mysql.jdbc.Driver").getDeclaredConstructor().newInstance();
            conn = DriverManager.getConnection(
                    "jdbc:" + "mysql" + "://" +
                            "localhost" +
                            ":" + "3306" + "/" +
                            dbName + "",
                    username,
                    password);
            System.out.println("Connected to database: " + dbName);

            return true;
        } catch (SQLException ex) {
            // handle any errors
            ex.printStackTrace();
            createdbifnotexist(dbName);
            return false;
        } catch (Exception et) {
            // handle any errors
            et.printStackTrace();
        }
        return false;

    }

    public static String getUsername() {
        return username;
    }

    public static void setUsername(String username) {
        JdbcUtil.username = username;
    }

    public static String getPassword() {
        return password;
    }

    public static void setPassword(String password) {
        JdbcUtil.password = password;
    }

    public static ArrayList<String> executeMySQLQuery() {
//        conn=getConnection(username,password);
        Statement stmt = null;
        ResultSet resultset = null;
        ArrayList<String> dbNames = new ArrayList<>();
        try {
            stmt = conn.createStatement();
            resultset = stmt.executeQuery("SHOW DATABASES;");

            if (stmt.execute("SHOW DATABASES;")) {
                resultset = stmt.getResultSet();
            }

            while (resultset.next()) {
                dbNames.add(resultset.getString("Database"));
                System.out.println(resultset.getString("Database"));
            }
        } catch (SQLException ex) {
            // handle any errors
            ex.printStackTrace();
        } finally {
            // release resources
            if (resultset != null) {
                try {
                    resultset.close();
                } catch (SQLException sqlEx) {
                }
                resultset = null;
            }

            if (stmt != null) {
                try {
                    stmt.close();
                } catch (SQLException sqlEx) {
                }
                stmt = null;
            }

            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException sqlEx) {
                }
                conn = null;
            }
        }

        //resultset to arraylist
dbNames.remove("information_schema");
dbNames.remove("mysql");
dbNames.remove("performance_schema");
dbNames.remove("sys");
dbNames.remove("test");
dbNames.remove("phpmyadmin");
dbNames.remove("default");
dbNames.remove("null");

        return dbNames;
    }

    public static void removeDB(String selectedItem) {
        getConnection(username, password);
        Statement stmt = null;
        ResultSet resultset = null;
        try {
            stmt = conn.createStatement();
            resultset = stmt.executeQuery("SHOW DATABASES LIKE '" + selectedItem + "'");
            if (resultset.next()) {
                //create new database
                stmt.executeUpdate("DROP DATABASE " + selectedItem);
                System.out.println("Database " + selectedItem + " removed.");
                //
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

    }

}