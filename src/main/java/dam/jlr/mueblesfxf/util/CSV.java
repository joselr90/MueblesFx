package dam.jlr.mueblesfxf.util;

import dam.jlr.mueblesfxf.model.Model;

import java.io.File;
import java.util.ArrayList;
import java.util.logging.FileHandler;

public class CSV {

    public static String toCSV(Model model) {
        StringBuilder sb = new StringBuilder();

        sb.append(model.getTipo()).append(",");
        sb.append(model.getMaterial()).append(",");
        sb.append(model.getPrecio()).append(",");
        sb.append("\n");

        return sb.toString();

    }
    public static String toCSV(ArrayList<Model> model) {
        StringBuilder sb = new StringBuilder();

        for (Model m : model) {
            sb.append(m.getTipo()).append(",");
            sb.append(m.getMaterial()).append(",");
            sb.append(m.getPrecio()).append(",");
            sb.append("\n");
        }
        return sb.toString();

    }
    public static String toCSV(Model[] model) {
        StringBuilder sb = new StringBuilder();

        for (Model m : model) {
            sb.append(m.getTipo()).append(",");
            sb.append(m.getMaterial()).append(",");
            sb.append(m.getPrecio()).append(",");
            sb.append("\n");
        }
        return sb.toString();

    }

    public static File toCSVfile(String fileName, String content) {
        //content to txt
        FileHandler fh;
        try {
            fh = new FileHandler(fileName);
//            fh.setFormatter(new java.util.logging.SimpleFormatter());
            java.util.logging.Logger.getLogger("").addHandler(fh);
            java.util.logging.Logger.getLogger("").log(java.util.logging.Level.INFO, content);
            fh.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        //txt to file
        File file = new File(fileName+".csv");
        return file;
    }

    }

