package com.alura.literalura.repository;

import com.alura.literalura.model.Libro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface LibroRepository extends JpaRepository<Libro, Long> {

//    CONSULTA DE AUTORES
    @Query("SELECT DISTINCT new com.alura.literalura.model.Libro( l.nombreAutor, l.fechaDeNacimiento, l.fechaDeDefuncion) FROM Libro l")
    List<Libro> listaAutores();

//    CONSULTA DE AUTORES POR AÑO CON RESULTADOS REPETIDOS
//    @Query("SELECT l FROM Libro l WHERE l.fechaDeNacimiento <= :fecha AND l.fechaDeDefuncion >= :fecha")
//    List<Libro> findAutoresEntreFechas(@Param("fecha") Integer fecha);

//    CONSULTA DE AUTORES POR AÑO Y SIN CAMPOS REPETIDOS
    @Query("SELECT DISTINCT new com.alura.literalura.model.Libro(l.nombreAutor, l.fechaDeNacimiento, l.fechaDeDefuncion) " +
            "FROM Libro l " +
            "WHERE l.fechaDeNacimiento <= :fecha AND l.fechaDeDefuncion >= :fecha")
    List<Libro> findAutoresEntreFechas(@Param("fecha") Integer fecha);

//    CONSULTA DE TODOS LOS LIBROS EN UN IDIOMA
    @Query(value = "SELECT * FROM libros WHERE :idioma = ANY (libros.idiomas);", nativeQuery = true)
    List<Libro> librosPorIdioma(@Param("idioma") String idioma);

}
