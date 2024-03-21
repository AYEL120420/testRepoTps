package serveur;

import javax.xml.ws.Endpoint;

import entity.Utilisateur;
import service.impl.UtilisateurServiceImpl;

public class Serveur {

	public static void main(String[] args) {
		
		//Utilisateur user4 = new Utilisateur("0004", "toto", "tata");
		UtilisateurServiceImpl service =new UtilisateurServiceImpl();
		//service.creerUtilisateur(user4);
		System.out.println(service.listerUtilisateur());
	
	Endpoint.publish("http://127.0.0.1:8888/user", service);
	}

}
