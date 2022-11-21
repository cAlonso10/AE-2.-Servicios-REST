package com.edix.cliente;


import java.util.List;
import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;
import com.edix.cliente.entidad.Videojuego;
import com.edix.cliente.servicio.ServicioProxyMensaje;
import com.edix.cliente.servicio.ServicioProxyVideojuego;






@SpringBootApplication
public class Application implements CommandLineRunner{

	
	@Autowired
	private ServicioProxyMensaje spm;
	
	@Autowired
	private ServicioProxyVideojuego spv;
	

	@Autowired
	private ApplicationContext context;
	
	@Bean
	public RestTemplate restTemplate(RestTemplateBuilder builder) {
		return builder.build();
	}
	

	public static void main(String[] args) {
		System.out.println("Cargando cliente Rest...");
		SpringApplication.run(Application.class, args);
		
	}
	

	@Override
	public void run(String... args) throws Exception {
		System.out.println("Cliente Rest cargado con éxito");
		Scanner sc = new Scanner(System.in);
		int opcion = 0;
		int id = 0;
		String nombre;
		String compañia;
		int nota = 0;
		do{    
			System.out.println("1- Dar de alta un videojuego");
			System.out.println("2- Dar de baja un videojuego");
			System.out.println("3- Modificar un videojuego");
			System.out.println("4- Obtener un videojuego");
			System.out.println("5- Listar todos los videojuegos");
			System.out.println("6- Salir");
			opcion = sc.nextInt();
			
			switch (opcion) { 
		    case 1:
		    	Videojuego videojuego = new Videojuego();
		    	System.out.println("Escriba el nombre del videojuego");
		    	sc.nextLine();
		    	nombre = sc.nextLine();
		    	System.out.println("Escriba la compañia del videojuego");
		    	compañia = sc.nextLine();
		    	System.out.println("Escriba la nota del videojuego");
		    	nota = sc.nextInt();
		    	videojuego.setNombre(nombre);
		    	videojuego.setCompañia(compañia);
		    	videojuego.setNota(nota);
		    	
		    	Videojuego vAlta = spv.alta(videojuego);
		    	System.out.println("Videojuego dado de alta" + vAlta);
		     break;
		    case 2:
		    	System.out.println("Indique la id que quiere borrar");
		    	id = sc.nextInt();
		    	boolean borrada = spv.borrar(id);
		     break; 
		    case 3 :
		    	Videojuego modificar = new Videojuego();
		    	System.out.println("Escriba la id del videojuego");
		    	id = sc.nextInt();
		    	System.out.println("Escriba el nuevo nombre");
		    	sc.nextLine();
		    	nombre = sc.nextLine();
		    	System.out.println("Escriba la nueva compañia");
		    	compañia = sc.nextLine();
		    	System.out.println("Escriba la nueva nota");
		    	nota = sc.nextInt();
		    	modificar.setId(id);
		    	modificar.setNombre(nombre);
		    	modificar.setCompañia(compañia);
		    	modificar.setNota(nota);
		    	
		    	boolean modificado = spv.modificar(modificar);
		    	System.out.println("Videojuego modificado: " + modificado);
		     break;
		    case 4:
		    	System.out.println("Escriba la id del videojuego");
		    	id = sc.nextInt();
		    	videojuego = spv.obtener(id);
		    	System.out.println(videojuego);
			     break;
		    case 5:
		    	List<Videojuego> listaVideojuegos = spv.listar(null);
		    	listaVideojuegos.forEach((v) -> System.out.println(v));
			     break;
		    case 6:
		    	System.out.println("Saliendo de la aplicacion");
			     break;
		    default:

		  }
			 
			}while (opcion != 6); 
		pararAplicacion();
	}
	
	public void pararAplicacion() {
		SpringApplication.exit(context, () -> 0);		
	}
}