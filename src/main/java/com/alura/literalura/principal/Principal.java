package com.alura.literalura.principal;

import com.alura.literalura.model.*;
import com.alura.literalura.repository.LibroRepository;
import com.alura.literalura.service.ConsumoAPI;
import com.alura.literalura.service.ConvierteDatos;

import java.util.*;

public class Principal {

    private static final String URL_BASE = "https://gutendex.com/books/";
    private ConsumoAPI consumoAPI = new ConsumoAPI();
    private ConvierteDatos conversor = new ConvierteDatos();
    private Scanner entrada = new Scanner(System.in);

    private LibroRepository repositorio;

    public Principal(LibroRepository repository) {
        this.repositorio=repository;
    }


    public void muestraElMenu() {
        var opcion = -1;
        while (opcion != 0) {
            var menu = """
                    1 - Buscar libro por titulo (obtener datos del libro desde la API)
                    2 - Listar libros registrados
                    3 - Listar autores registrados
                    4 - Listar autores vivos en un determinado año
                    5 - Listar libros por idioma
                    0 - Salir
                    """;
            System.out.println(menu);
            opcion = entrada.nextInt();
            entrada.nextLine();

            switch (opcion) {
                case 1:
                    buscarLibro();
                    break;
                case 2:
                    mostrarLibrosRegistrados();
                    break;
                case 3:
                    obtenerTodosLosAutores();
                    break;
                case 4:
                    obtenerAutoresVivosFecha();
                    break;
                case 5:
                    mostrarLibrosIdioma();
                    break;
                case 0:
                    System.out.println("Cerrando la aplicación...");
                    break;
                default:
                    System.out.println("Opción inválida");
            }
        }
    }

//    OBTENER DATOS DEL LIBRO BUSCADO
    private DatosLibro getDatosLibro() {
        System.out.println("Ingrese el nombre del libro:");
        var tituloLibro = entrada.nextLine();
        var json = consumoAPI.obtenerDatos(URL_BASE + "?search=" + tituloLibro.replace(" ", "+"));
        var datosBusqueda = conversor.obtenerDatos(json, Datos.class);
        System.out.println(json);

        Optional<DatosLibro> libroBuscado = datosBusqueda.resultados().stream()
                .filter(l -> l.titulo().toUpperCase().contains(tituloLibro.toUpperCase()))
                .findFirst();
        if (libroBuscado.isPresent()) {
            System.out.println("Libro encontrado:");
            System.out.println("Datos del libro: " + libroBuscado.get());
            System.out.println();
        } else {
            System.out.println("Libro no encontrado\n");
        }

        DatosLibro nuevoLibro = libroBuscado.get();
        return nuevoLibro;
    }

//    INGRESAR DATOS DEL AUTOR-LIBRO
    private Libro crearLibro(){
        DatosLibro datosLibro = getDatosLibro();
        Libro nuevoLibro = new Libro(datosLibro);

        System.out.println("Ingrese los datos obtenidos del autor de forma manual: ");
        System.out.println("nombre: ");
        String nombre = entrada.nextLine();
        nuevoLibro.setNombreAutor(nombre);
        System.out.println("fecha de nacimiento: ");
        Integer fechaNac = entrada.nextInt();
        System.out.println("fecha de defuncion: ");
        Integer fechaDef = entrada.nextInt();
        nuevoLibro.setNombreAutor(nombre);
        nuevoLibro.setFechaDeNacimiento(fechaNac);
        nuevoLibro.setFechaDeDefuncion(fechaDef);

        nuevoLibro.setTitulo(datosLibro.titulo());
        nuevoLibro.setIdiomas(datosLibro.idiomas());
        nuevoLibro.setNumeroDeDescargas(datosLibro.numeroDeDescargas());

        System.out.println("DATOS LIBRO: " + nuevoLibro);
        System.out.println("AUTOR: " + nuevoLibro.getNombreAutor() + ", (" + nuevoLibro.getFechaDeNacimiento() +
                " - " + nuevoLibro.getFechaDeDefuncion() + ")");
        System.out.println("LIBRO: " + nuevoLibro.getTitulo() + ", IDIOMA: " + nuevoLibro.getIdiomas() +
                ", DESCARGAS: " + nuevoLibro.getNumeroDeDescargas());

        return nuevoLibro;
    }

//    1 - Buscar y registrar libros en la BD
    private void buscarLibro() {
        Libro libro = crearLibro();
        repositorio.save(libro);
        System.out.println(libro);
    }

//    2 - Listar libros registrados
    private void mostrarLibrosRegistrados(){
        List<Libro> libros = repositorio.findAll();
        libros.forEach(System.out::println);
        System.out.println();
    }

//    3 - Listar autores registrados
    private void obtenerTodosLosAutores(){
        List<Libro> autores = repositorio.listaAutores();
        autores.forEach(System.out::println);
        System.out.println();
    }

//    4 - Listar autores vivos en un determinado año
    private void obtenerAutoresVivosFecha(){
        System.out.println("Ingrese el año que desea consultar para conocer los autores vivos: ");
        var fecha = entrada.nextInt();
        List<Libro> autoresVivos = repositorio.findAutoresEntreFechas(fecha);

        if (!autoresVivos.isEmpty()) {
            System.out.println("La lista de autores vivos en la fecha: " + fecha + " es: ");
            autoresVivos.forEach(System.out::println);
        } else {
            System.out.println("No se encontraron autores vivos en esa fecha");
        }
    }

//    5 - Listar libros por idioma
    private void mostrarLibrosIdioma() {
        System.out.println("Ingrese las dos letras iniciales del idioma buscado: ");
        var idiomaLibro = entrada.nextLine();
        List<Libro> librosBuscados = repositorio.librosPorIdioma(idiomaLibro);

        if (!librosBuscados.isEmpty()) {
            System.out.println("La lista de libros en el idioma '" + idiomaLibro + "' es: ");
            librosBuscados.forEach(System.out::println);
        } else {
            System.out.println("No se encontraron libros en ese idioma");
        }
    }

}
