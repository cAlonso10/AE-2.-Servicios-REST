package com.edix.cliente.servicio;

import java.util.Arrays;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import com.edix.cliente.entidad.Videojuego;

@Service
public class ServicioProxyVideojuego {
	
	public static final String URL = "http://localhost:8080/videojuegos/";
	
	@Autowired
	private RestTemplate restTemplate;
	
	

	public Videojuego obtener(int id){
		try {
			ResponseEntity<Videojuego> re = restTemplate.getForEntity(URL + id, Videojuego.class);
			HttpStatus hs= re.getStatusCode();
			if(hs == HttpStatus.OK) {	
				return re.getBody();
			}else {
				System.out.println("Respuesta no contemplada");
				return null;
			}
		}catch (HttpClientErrorException e) {
			System.out.println("No se encuentra el videojuego: " + id);
		    System.out.println("Codigo de respuesta: " + e.getStatusCode());
		    return null;
		}
	}
	public Videojuego alta(Videojuego v){
		try {
			ResponseEntity<Videojuego> re = restTemplate.postForEntity(URL, v, Videojuego.class);
			System.out.println("Codigo de respuesta " + re.getStatusCode());
			return re.getBody();
		} catch (HttpClientErrorException e) {
			System.out.println("El videojuego no se ha dado de alta: " + v);
		    System.out.println("Codigo de respuesta: " + e.getStatusCode());
		    return null;
		}
	}
	public boolean modificar(Videojuego p){
		try {
			restTemplate.put(URL + p.getId(), p, Videojuego.class);
			return true;
		} catch (HttpClientErrorException e) {
			System.out.println("El videojuego no se ha modificado: " + p.getId());
		    System.out.println("Codigo de respuesta: " + e.getStatusCode());
		    return false;
		}
	}
	public boolean borrar(int id){
		try {

			restTemplate.delete(URL + id);
			return true;
		} catch (HttpClientErrorException e) {
			System.out.println("El videojuego no se ha podido borrar: " + id);
		    System.out.println("Codigo de respuesta: " + e.getStatusCode());
		    return false;
		}
	}
	
	public List<Videojuego> listar(String nombre){
		String queryParams = "";		
		if(nombre != null) {
			queryParams += "?nombre=" + nombre;
		}
		
		try {
			ResponseEntity<Videojuego[]> response =
					  restTemplate.getForEntity(URL + queryParams,Videojuego[].class);
			Videojuego[] arrayVideojuegos = response.getBody();
			return Arrays.asList(arrayVideojuegos);
		} catch (HttpClientErrorException e) {
			System.out.println("Error al obtener la lista de videojuegos");
		    System.out.println("Codigo de respuesta: " + e.getStatusCode());
		    return null;
		}
	}
}