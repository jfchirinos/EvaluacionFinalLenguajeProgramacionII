package com.evaluacion.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.evaluacion.demo.model.Libro;

public interface LibroRepository extends JpaRepository<Libro, Long>{

}