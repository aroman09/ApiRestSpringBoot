package com.bussinessIt.api.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.persistence.*;

@Table(name="Persona")
@Entity
@Data
@AllArgsConstructor
@Inheritance(strategy = InheritanceType.JOINED)

public class Persona {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_persona", nullable = false, unique = true)
    private long id_persona;
    @Column(name = "nombre", length = 20,nullable = false)
    private String nombre;
    @Column(name = "genero", length = 5,nullable = false)
    private String genero;
    @Column(name = "identificacion", length = 15,nullable = false)
    private String identificacion;
    @Column(name = "direccion", length = 30,nullable = false)
    private String direccion;
    @Column(name = "telefono", length = 10,nullable = false)
    private String telefono;

    @Override
    public String toString() {
        return "Persona{" +
                ", nombre='" + nombre + '\'' +
                ", genero='" + genero + '\'' +
                ", identificacion='" + identificacion + '\'' +
                ", direccion='" + direccion + '\'' +
                ", telefono='" + telefono + '\'' +
                '}';
    }

    public Persona(String nombre, String genero, String identificacion, String direccion, String telefono) {
        this.nombre = nombre;
        this.genero = genero;
        this.identificacion = identificacion;
        this.direccion = direccion;
        this.telefono = telefono;
    }

    public Persona() {
    }
}
