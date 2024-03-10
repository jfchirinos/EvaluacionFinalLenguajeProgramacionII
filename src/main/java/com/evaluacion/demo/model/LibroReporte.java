package com.evaluacion.demo.model;

import java.util.Date;

public class LibroReporte {

    private Long idlibro;
    private String nombre;
    private String autor;
    private Date fechapublicacion;
    private Date fecharegistro;
    private String genero;


    public LibroReporte(Long idlibro, String nombre, String autor, Date fechapublicacion, Date fecharegistro, String genero) {
        this.idlibro = idlibro;
        this.nombre = nombre;
        this.autor = autor;
        this.fechapublicacion = fechapublicacion;
        this.fecharegistro = fecharegistro;
        this.genero = genero;
    }

    public Long getIdlibro() {
        return idlibro;
    }

    public void setIdlibro(Long idlibro) {
        this.idlibro = idlibro;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public Date getFechapublicacion() {
        return fechapublicacion;
    }

    public void setFechapublicacion(Date fechapublicacion) {
        this.fechapublicacion = fechapublicacion;
    }

    public Date getFecharegistro() {
        return fecharegistro;
    }

    public void setFecharegistro(Date fecharegistro) {
        this.fecharegistro = fecharegistro;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }
}
