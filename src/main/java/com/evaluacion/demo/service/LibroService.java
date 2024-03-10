package com.evaluacion.demo.service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.evaluacion.demo.model.Libro;
import com.evaluacion.demo.model.LibroReporte;
import com.evaluacion.demo.repository.LibroRepository;

@Service
public class LibroService {
	
	@Autowired
	public LibroRepository libroRepository;
	
	public List<Libro> getAllLibros() {
		return libroRepository.findAll();
	}	
	
	public void registrarLibro(Libro libro) {
		libroRepository.save(libro);
    }
	
	public Libro getLibroById(Long id) {
		return libroRepository.findById(id).orElse(null);
	}
	
	public void eliminarLibro(Long id) {
		libroRepository.deleteById(id);
	}	
	public List<LibroReporte> getLibrosReporte() {
        List<Libro> libros = libroRepository.findAll();
        List<LibroReporte> librosReporte = new ArrayList<>();

        for (Libro libro : libros) {
            if (isFechaRegistroValida(libro.getFecharegistro())) {
                String genero = libro.getGenero().getNombre();
                librosReporte.add(new LibroReporte(libro.getIdlibro(), libro.getNombre(), libro.getAutor(),
                        libro.getFechapublicacion(), libro.getFecharegistro(), genero));
            }
        }

        return librosReporte;
    }

	private boolean isFechaRegistroValida(Date fechaRegistro) {
	    Calendar calendar = Calendar.getInstance();
	    calendar.add(Calendar.MONTH, -6);
	    Date fechaLimite = calendar.getTime();
	    return fechaRegistro.after(fechaLimite);
	}    
	
}
