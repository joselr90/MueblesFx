package dam.jlr.mueblesfxf.util;

import dam.jlr.mueblesfxf.model.Model;
import org.apache.commons.lang3.SerializationUtils;

import java.io.*;
import java.util.ArrayList;


public class Data {
    //arraylist to binary
    ArrayList<Model> models = new ArrayList<>();

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
    public static void arraylistToData(ArrayList<Model> a, String name) {
        String path = ".\\src\\main\\java\\src\\data\\exportdata\\" + name + ".data";
        //  Write to disk with FileOutputStream
        FileOutputStream f_out = null;
        try {
            f_out = new
                    FileOutputStream(path);
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
            obj_out.writeObject(a);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


    }
        //serialize data






