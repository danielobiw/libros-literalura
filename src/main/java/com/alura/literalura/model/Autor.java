//package com.alura.literalura.model;
//
//import jakarta.persistence.*;
//
//import java.util.List;
//
////@Embeddable
//@Entity
//@Table(name = "autores")
//public class Autor {
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long id;
//    @Column(unique = true)
//    private String nombre;
//    private Integer fechaDeNacimiento;
//    private Integer fechaDeDefuncion;
//    @OneToMany(mappedBy = "autor", cascade = CascadeType.ALL)
//    private List<Libro> libro;
//
//    //CONSTRUCTOR POR DEFECTO
//    public Autor() {
//    }
//
//    public Autor(String nombre, Integer fechaDeNacimiento, Integer fechaDeDefuncion){
//        this.nombre = nombre;
//        this.fechaDeNacimiento = fechaDeNacimiento;
//        this.fechaDeDefuncion = fechaDeDefuncion;
//    }
//
//    public Autor(DatosAutor datosAutor) {
//        this.nombre = datosAutor.nombre();
//        this.fechaDeNacimiento = datosAutor.fechaDeNacimiento();
//        this.fechaDeDefuncion = datosAutor.fechaDeDefuncion();
//    }
//
////    public Long getId() {
////        return id;
////    }
////
////    public void setId(Long id) {
////        this.id = id;
////    }
//
//    public String getNombre() {
//        return nombre;
//    }
//
//    public void setNombre(String nombre) {
//        this.nombre = nombre;
//    }
//
//    public Integer getFechaDeNacimiento() {
//        return fechaDeNacimiento;
//    }
//
//    public void setFechaDeNacimiento(Integer fechaDeNacimiento) {
//        this.fechaDeNacimiento = fechaDeNacimiento;
//    }
//
//    public Integer getFechaDeDefuncion() {
//        return fechaDeDefuncion;
//    }
//
//    public void setFechaDeDefuncion(Integer fechaDeDefuncion) {
//        this.fechaDeDefuncion = fechaDeDefuncion;
//    }
//
//    public List<Libro> getLibro() {
//        libro.forEach(l -> l.setAutor(this));
//        return libro;
//    }
//
//    public void setLibro(List<Libro> libro) {
//        this.libro = libro;
//    }
//
//    @Override
//    public String toString() {
//        return "Autor: " +
//                "nombre=" + nombre +
//                ", fecha nacimiento=" + fechaDeNacimiento +
//                ", fecha fallecimiento=" + fechaDeDefuncion;
//    }
//}
