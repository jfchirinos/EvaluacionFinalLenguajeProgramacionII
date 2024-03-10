package com.evaluacion.demo.controllers;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.evaluacion.demo.model.Genero;
import com.evaluacion.demo.model.Libro;
import com.evaluacion.demo.service.GeneroService;
import com.evaluacion.demo.service.LibroService;

@Controller
@RequestMapping("/libro")
public class LibroController {

	@Autowired
	private LibroService libroService;
	
	@Autowired
	private GeneroService generoService;
	
	@GetMapping("/libros")
	public String getAllLibros(Model model) {
        List<Libro> lisLibros = libroService.getAllLibros();
        model.addAttribute("libros", lisLibros); 
        return "libroList";  
    }
	
	@GetMapping("/register")
	public String register(Model model) {
		model.addAttribute("generos", generoService.getAllGeneros());
		return "libroRegister";
	}
		
	@PostMapping("/register")
	public String registerLibro(@RequestParam("nombre") String nombre,
	                                @RequestParam("autor") String autor,
	                                @RequestParam("fechapublicacion") String fechapublicacionStr,	                               
	                                @RequestParam("idgenero") Long idGenero, 
	                                Model model) {
		
	    Genero genero = generoService.getGeneroById(idGenero);
	    
	    Libro libro = new Libro();
	    libro.setNombre(nombre);
	    libro.setAutor(autor);
	    try {
	        Date fechapublicacion = new SimpleDateFormat("yyyy-MM-dd").parse(fechapublicacionStr);
	        libro.setFechapublicacion(fechapublicacion);	       
	    } catch (ParseException e) {
	    	System.out.println(e);
	    }	   
	    Date fecharegistro = new Date();
	    libro.setFecharegistro(fecharegistro);
	    libro.setGenero(genero);

	    libroService.registrarLibro(libro);
	    
	    List<Libro> listLibros = libroService.getAllLibros();
		model.addAttribute("libros", listLibros);

	    return "libroList"; 
	}

	@GetMapping("/edit/{id}")
	public String showEditForm(@PathVariable("id") Long id, Model model) {
		Libro libro = libroService.getLibroById(id);
	    model.addAttribute("libro", libro);
	    model.addAttribute("generos", generoService.getAllGeneros());
	    return "LibroEdit";
	}
		
	@PostMapping("/edit")
	public String editLibro(@RequestParam("id") Long id,
	                            @RequestParam("nombre") String nombre,
	                            @RequestParam("autor") String autor,
	                            @RequestParam("fechapublicacion") String fechapublicacionStr,
                                @RequestParam("idgenero") Long idGenero,
	                            Model model) {

		Genero genero = generoService.getGeneroById(idGenero);

		Libro libro = libroService.getLibroById(id);
	    libro.setNombre(nombre);
	    libro.setAutor(autor);
	    try {
	        Date fechapublicacion = new SimpleDateFormat("yyyy-MM-dd").parse(fechapublicacionStr);
	        libro.setFechapublicacion(fechapublicacion);	       
	    } catch (ParseException e) {
	    	System.out.println(e);
	    }	
	    libro.setFecharegistro(libro.getFecharegistro());
	    libro.setGenero(genero);

	    libroService.registrarLibro(libro);

	    List<Libro> listLibros = libroService.getAllLibros();
	    model.addAttribute("libros", listLibros);

	    return "libroList";
	}
	
	@GetMapping("/delete/{id}")
	public String deleteLibro(@PathVariable Long id, Model model) {
		libroService.eliminarLibro(id);
		
		List<Libro> listLibros = libroService.getAllLibros();
	    model.addAttribute("libros", listLibros);
		
		return "libroList";
	}
}
