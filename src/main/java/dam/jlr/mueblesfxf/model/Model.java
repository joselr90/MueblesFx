package dam.jlr.mueblesfxf.model;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "Muebles")



public class Model implements Serializable {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "tipo")
    private String tipo;
    @Column(name = "material")
    private String material;
    @Column(name="precio")
    private double precio;

    @Override
    public String toString() {
        return "Model{" +
                "id=" + id +
                ", tipo='" + tipo + '\'' +
                ", material='" + material + '\'' +
                ", precio=" + precio +
                '}';
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getMaterial() {
        return material;
    }

    public void setMaterial(String material) {
        this.material = material;
    }

    public String getPrecio2() {
        return String.valueOf(precio)+" €";
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public Model() {
    }

    public Model(String tipo, String material, double precio) {
        this.tipo = tipo;
        this.material = material;
        this.precio = precio;
    }

    public Model(int id, String tipo, String material, double precio) {
        this.id = id;
        this.tipo = tipo;
        this.material = material;
        this.precio = precio;
    }

    public void setId(int id) {
        this.id = id;
    }


    public int getId() {
        return id;
    }
}
