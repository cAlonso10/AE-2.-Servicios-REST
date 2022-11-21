package com.edix.rest.controlador;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.edix.rest.modelo.entidad.Videojuego;
import com.edix.rest.modelo.persistencia.DaoVideojuego;


@RestController
public class ControladorVideojuego {
	
	@Autowired
	private DaoVideojuego daoVideojuego;
	
	//Dar de alta un videojuego
	//http://localhost:8080/videojuegos  -> Con el metodo POST se le pasa la información en el body
	@PostMapping(path="videojuegos",consumes=MediaType.APPLICATION_JSON_VALUE,
			produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Videojuego> altaVideojuego(@RequestBody Videojuego v) {
		System.out.println("Alta videojuego: " + v);
		daoVideojuego.add(v);
		return new ResponseEntity<Videojuego>(v,HttpStatus.CREATED);
	}
	
	//Dar de baja un videojuego
	//http://localhost:8080/videojuegos/ID  -> Se utiliza el metodo delete
	@DeleteMapping(path="videojuegos/{id}")
	public ResponseEntity<Videojuego> borrarVideojuego(@PathVariable("id") int id) {
		System.out.println("ID a borrar: " + id);
		Videojuego v = daoVideojuego.delete(id);
		if(v != null) {
			return new ResponseEntity<Videojuego>(v,HttpStatus.OK);//200 OK
		}else {
			return new ResponseEntity<Videojuego>(HttpStatus.NOT_FOUND);//404 NOT FOUND
		}
	}
	
	//Modificar un videojuego
	//http://localhost:8080/videojuegos/ID  -> Se utiliza el metodo PUT y se pasa la información en el body
	@PutMapping(path="videojuegos/{id}",consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Videojuego> modificarVideojuego(
			@PathVariable("id") int id, 
			@RequestBody Videojuego v) {
		System.out.println("ID a modificar: " + id);
		System.out.println("Datos a modificar: " + v);
		v.setId(id);
		Videojuego vUpdate = daoVideojuego.update(v);
		if(vUpdate != null) {
			return new ResponseEntity<Videojuego>(HttpStatus.OK);
		}else {
			return new ResponseEntity<Videojuego>(HttpStatus.NOT_FOUND);
		}
	}
	
	
	//Listar videojuego por id
	//http://localhost:8080/videojuegos/ID  -> Con el metodo GET se indica la ID
	@GetMapping(path="videojuegos/{id}",produces = MediaType.APPLICATION_JSON_VALUE)	
	public ResponseEntity<Videojuego> getVideojuego(@PathVariable("id") int id) {
		System.out.println("Buscando videojuego mediante la id: " + id);
		Videojuego v = daoVideojuego.get(id);
		if(v != null) {
			return new ResponseEntity<Videojuego>(v,HttpStatus.OK);
		}else {
			return new ResponseEntity<Videojuego>(HttpStatus.NOT_FOUND);
		}
	}
	
	//Listar todos los videojuegos
	//http://localhost:8080/videojuegos  -> Con el metodo GET
	@GetMapping(path="videojuegos",produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Videojuego>> listarVideojuegos() {
		List<Videojuego> listaVideojuego = null;
		System.out.println("Listando los videojuegos");
		listaVideojuego = daoVideojuego.list();			
		System.out.println(listaVideojuego);
		return new ResponseEntity<List<Videojuego>>(listaVideojuego,HttpStatus.OK);
	}
}
