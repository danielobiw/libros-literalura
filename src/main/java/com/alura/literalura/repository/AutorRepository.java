//package com.alura.literalura.repository;
//
//import com.alura.literalura.model.Autor;
//import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.data.jpa.repository.Query;
//import org.springframework.stereotype.Repository;
//
//import java.util.List;
//
//@Repository
//public interface AutorRepository extends JpaRepository<Autor, Long> {
//    @Query(value = "SELECT * FROM autores;", nativeQuery = true)
//    List<Autor> obtenerTodosLosAutores();
//
//}