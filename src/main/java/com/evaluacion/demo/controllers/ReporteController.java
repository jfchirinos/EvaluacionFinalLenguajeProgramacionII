package com.evaluacion.demo.controllers;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.evaluacion.demo.model.LibroReporte;
import com.evaluacion.demo.service.LibroService;

import jakarta.servlet.http.HttpServletResponse;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.util.JRLoader;

@Controller
@RequestMapping("/report")
public class ReporteController {
	
	@Autowired
	private LibroService libroService;

	@GetMapping("/report")
	public void report(HttpServletResponse response) throws JRException, IOException {
		
		// 1. Acceder al reporte 
		InputStream jasperStream = this.getClass().getResourceAsStream("/reportes/Reporte_libros_registrados.jasper");

		// 2. Preparar los datos 	
		// Obtener la fecha actual y calcular la fecha límite de 6 meses
	    Calendar calendar = Calendar.getInstance();
	    calendar.add(Calendar.MONTH, -6);
	    Date fechaLimite = calendar.getTime();
	    
		List<LibroReporte> libros = libroService.getLibrosReporte();
		
		// Filtrar los libros con fecha de registro no mayor a 6 meses
        List<LibroReporte> librosFiltrados = libros.stream()
                .filter(libro -> libro.getFecharegistro().after(fechaLimite))
                .collect(Collectors.toList());
		
        JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(librosFiltrados);
        JasperReport jasperReport = (JasperReport) JRLoader.loadObject(jasperStream);  

        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, new HashMap<>(), dataSource);

        
		// 3. Configuracion 
		response.setContentType("application/x-pdf");
		response.setHeader("Content-disposition", "filename=reporte_libros_registrados.pdf");		

		// 4. Exportar reporte
		final OutputStream outputStream = response.getOutputStream();
		JasperExportManager.exportReportToPdfStream(jasperPrint, outputStream);	

	}
}
