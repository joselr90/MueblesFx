package dam.jlr.mueblesfxf.util;

import dam.jlr.mueblesfxf.model.Model;
import org.hibernate.Session;

import javax.persistence.EntityManager;
import javax.persistence.Query;


import java.util.ArrayList;
import java.util.List;

public class HibernateActions {
   private static EntityManager entityManager;

    public static String getDataBase() {
        return dataBase;
    }

    public static void setDataBase(String dataBase) {
        HibernateActions.dataBase = dataBase;
    }

    private static String dataBase;

    public HibernateActions() {
      entityManager = HibernateUtil.getSessionFactory(dataBase).createEntityManager();
    }

    public static void save(Model model) {
        entityManager = HibernateUtil.getSessionFactory(dataBase).createEntityManager();
        entityManager.getTransaction().begin();
        entityManager.persist(model);
        entityManager.getTransaction().commit();
        entityManager.close();
    }

public static List<Model> getData(){
    Session session = HibernateUtil.getSessionFactory(dataBase).openSession();
    Query query = session.createQuery("from Model");
    List<Model> list = query.getResultList();
    session.close();
    //query to get all the records from the table



//   ArrayList<Model> muebles=(ArrayList<Model>) entityManager.createQuery("SELECT m FROM Model m").getResultList();
//    entityManager.getTransaction().commit();
//    entityManager.close();
    return list;
}
//get by id
public static Model getById(int id){
    entityManager = HibernateUtil.getSessionFactory(dataBase).createEntityManager();
    entityManager.getTransaction().begin();
    Model mueble=entityManager.find(Model.class, id);
    entityManager.getTransaction().commit();
    entityManager.close();
    return mueble;
}
//get by material ignore case
public static ArrayList<Model> getByMaterial(String material){
    entityManager = HibernateUtil.getSessionFactory(dataBase).createEntityManager();
    entityManager.getTransaction().begin();
    ArrayList<Model> muebles=(ArrayList<Model>) entityManager.createQuery("SELECT m FROM Model m WHERE m.material LIKE :material").setParameter("material", "%"+material+"%").getResultList();
    entityManager.getTransaction().commit();
    entityManager.close();
    return muebles;
}
//get by tipo ignore case
public static ArrayList<Model> getByTipo(String tipo){
    entityManager = HibernateUtil.getSessionFactory(dataBase).createEntityManager();
    entityManager.getTransaction().begin();
    ArrayList<Model> muebles=(ArrayList<Model>) entityManager.createQuery("SELECT m FROM Model m WHERE m.tipo LIKE :tipo").setParameter("tipo", "%"+tipo+"%").getResultList();
    entityManager.getTransaction().commit();
    entityManager.close();
    return muebles;
}
//get by precio
public static ArrayList<Model> getByPrecio(double precio){
    entityManager = HibernateUtil.getSessionFactory(dataBase).createEntityManager();
    entityManager.getTransaction().begin();
    ArrayList<Model> muebles=(ArrayList<Model>) entityManager.createQuery("SELECT m FROM Model m WHERE m.precio = :precio").setParameter("precio", precio).getResultList();
    entityManager.getTransaction().commit();
    entityManager.close();
    return muebles;
}

    public static void modify(Model model) {
        entityManager = HibernateUtil.getSessionFactory(dataBase).createEntityManager();
        entityManager.getTransaction().begin();
        entityManager.merge(model);
        entityManager.getTransaction().commit();
        entityManager.close();
    }

    public static void delete(Model model) {
        entityManager = HibernateUtil.getSessionFactory(dataBase).createEntityManager();
        entityManager.getTransaction().begin();

        entityManager.remove(model);
        entityManager.getTransaction().commit();
        entityManager.close();
    }


    public static final String INSERT = "insert";
    public static final String UPDATE = "update";
    public static final String DELETE = "delete";
    public static final String SELECT = "select";
    public static final String SELECT_ALL = "selectAll";
    public static final String SELECT_BY_ID = "selectById";

    public static void insert(Model model) {
        entityManager = HibernateUtil.getSessionFactory(dataBase).createEntityManager();
        entityManager.getTransaction().begin();
        entityManager.persist(model);
        entityManager.getTransaction().commit();
        entityManager.close();
    }

    }

