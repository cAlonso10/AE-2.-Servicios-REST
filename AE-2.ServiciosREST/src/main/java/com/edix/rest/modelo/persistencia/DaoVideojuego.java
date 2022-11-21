package com.edix.rest.modelo.persistencia;

import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Component;
import com.edix.rest.modelo.entidad.Videojuego;

@Component
public class DaoVideojuego {
	
	public List<Videojuego> listaVideojuegos;
	public int contador;
	
	public DaoVideojuego() {
		System.out.println("Creando lista de videojuegos...");
		listaVideojuegos = new ArrayList<Videojuego>();
		Videojuego v1 = new Videojuego(contador++,"God of war","SCE",10);
		Videojuego v2 = new Videojuego(contador++,"Minecraft","Mojang",6);
		Videojuego v3 = new Videojuego(contador++,"League of Legends","Riot Games",3);
		Videojuego v4 = new Videojuego(contador++,"Fortnite","Epic Games",8);
		Videojuego v5 = new Videojuego(contador++,"Call of Duty","Activision",1);
		listaVideojuegos.add(v1);
		listaVideojuegos.add(v2);
		listaVideojuegos.add(v3);
		listaVideojuegos.add(v4);
		listaVideojuegos.add(v5);
	}
	
	
	//Dar de alta un videojuego
	//http://localhost:8080/videojuegos  -> Con el metodo POST se le pasa la información en el body
	public void add(Videojuego v) {
		v.setId(contador++);
		listaVideojuegos.add(v);
	}
	
	//Dar de baja un videojuego
	//http://localhost:8080/videojuegos/ID  -> Se utiliza el metodo delete
	public Videojuego delete(int posicion) {
		try {
			return listaVideojuegos.remove(posicion);
		} catch (IndexOutOfBoundsException e) {
			System.out.println("No se encuentra el videojuego");
			return null;
		}
	}
	
	//Modificar un videojuego
	//http://localhost:8080/videojuegos/ID  -> Se utiliza el metodo PUT y se pasa la información en el body
	public Videojuego update(Videojuego v) {
		try {
			Videojuego vAux = listaVideojuegos.get(v.getId());
			vAux.setNombre(v.getNombre());
			vAux.setCompañia(v.getCompañia());
			vAux.setNota(v.getNota());

			return vAux;
		} catch (IndexOutOfBoundsException iobe) {
			System.out.println("No se encuentra el videojuego");
			return null;
		}
	}
	
	//Listar videojuego por id
	//http://localhost:8080/videojuegos/ID  -> Con el metodo GET se indica la ID
	public Videojuego get(int posicion) {
		try {
			return listaVideojuegos.get(posicion);
		} catch (IndexOutOfBoundsException iobe) {
			System.out.println("Videojuego no encontrado");
			return null;
		}
	}
	
	//Listar todos los videojuegos
	//http://localhost:8080/videojuegos  -> Con el metodo GET
	public List<Videojuego> list() {
		return listaVideojuegos;
	}
	

}
