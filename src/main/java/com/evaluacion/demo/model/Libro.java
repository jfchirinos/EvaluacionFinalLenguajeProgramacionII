package com.evaluacion.demo.model;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "libros")
public class Libro {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idlibro", nullable = false)
	public Long idlibro;

    @Column(name = "nombre", nullable = false)
    public String nombre;

    @Column(name = "autor", nullable = false)
    public String autor;

    @Column(name = "fechapublicacion", nullable = false)
    public Date fechapublicacion;
    
    @Column(name = "fecharegistro", nullable = false)
    public Date fecharegistro;

    @ManyToOne
    @JoinColumn(name = "idgenero", nullable = false)
    public Genero genero;
    
    public Libro() {
    }

    public Libro(String nombre, String autor
    		, Date fechapublicacion, 
    		Genero genero
    		) {
        this.nombre = nombre;
        this.autor = autor;
        this.fechapublicacion = fechapublicacion;
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

    public void setFecharegistro(Date fechapublicacion) {
        this.fecharegistro = fechapublicacion;
    }

    public Genero getGenero() {
        return genero;
    }

    public void setGenero(Genero genero) {
        this.genero = genero;
    }
	
}
