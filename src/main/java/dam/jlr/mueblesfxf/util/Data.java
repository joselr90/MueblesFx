package dam.jlr.mueblesfxf.util;

import dam.jlr.mueblesfxf.model.Model;
import org.apache.commons.lang3.SerializationUtils;

import java.io.*;
import java.util.ArrayList;


public class Data {
    public static ArrayList<Model> getModels() {
        return models;
    }

    public static void setModels(ArrayList<Model> models) {
        Data.models = models;
    }

    //arraylist to binary
    static ArrayList<Model> models = new ArrayList<>();

    //serialize data
    //serialize to byte
    public byte[] serialize(ArrayList<Model> models) {
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        ObjectOutputStream oos;
        try {
            oos = new ObjectOutputStream(bos);
            oos.writeObject(models);
            oos.flush();
            oos.close();
            bos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return bos.toByteArray();
    }
    public static void arraylistToData(File file,ArrayList<Model> models) {
//        String path = ".\\src\\main\\resources\\dataexport\\" + name + ".data";
//        String file = path + name + ".data";
        //  Write to disk with FileOutputStream
        FileOutputStream f_out = null;
        try {
            f_out = new
                    FileOutputStream(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

// Write object with ObjectOutputStream
        ObjectOutputStream obj_out = null;
        try {
            obj_out = new ObjectOutputStream(f_out);
        } catch (IOException e) {
            e.printStackTrace();
        }

// Write object out to disk
        try {
            obj_out.writeObject(models);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    public static ArrayList<Model> dataToArraylist(File file) {
        // Read from disk using FileInputStream
//        String path = ".\\src\\main\\resources\\dataexport\\" + "\\" + name + ".data";

//        String basePath =  path;

//        System.out.println(basePath);
        FileInputStream f_in = null;
        try {
            f_in = new FileInputStream(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

// Read object using ObjectInputStream
        ObjectInputStream obj_in =
                null;
        try {
            obj_in = new ObjectInputStream(f_in);
        } catch (IOException e) {

        }

// Read an object
        Object obj = null;
        try {
            obj = obj_in.readObject();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }


        // Cast object to a Vector
        ArrayList<Model> vec = (ArrayList<Model>) obj;

        // Do something with vector....

        return vec;
    }



}
        //serialize data






