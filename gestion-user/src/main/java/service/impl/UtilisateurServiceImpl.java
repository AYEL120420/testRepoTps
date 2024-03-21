package service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.jws.WebParam;
import javax.jws.WebService;

import entity.Utilisateur;
import service.IUtilisateurService;

@WebService(serviceName = "user")
public class UtilisateurServiceImpl implements IUtilisateurService {
	
	List<Utilisateur> utilisateurs = new ArrayList<>();
	
	public UtilisateurServiceImpl () {
	Utilisateur user1 = new Utilisateur("0001", "Jhon", "doe");
	Utilisateur user2 = new Utilisateur("0002", "Jannette", "doe");
	Utilisateur user3 = new Utilisateur("0003", "Jocelin", "poe");
	
	this.utilisateurs.add(user1);
	this.utilisateurs.add(user2);
	this.utilisateurs.add(user3);
	
	}
	@Override
	public Utilisateur creerUtilisateur (@WebParam(name = "newUser") Utilisateur user) {
	utilisateurs.add(user);
		return user;
	}

	@Override
	public Utilisateur modifierUtilisateur(Utilisateur user) {
	Utilisateur utilisateur = rechercherUtilisateurParMatricule(user.getMatricule());
	if (utilisateur!=null) {
		utilisateur.setNom(user.getNom());
		utilisateur.setPrenom(user.getPrenom());
	}
		return null;
	}

	@Override
	public void supprimerUtilisateur(Utilisateur user) {
		Utilisateur utilisateur = rechercherUtilisateurParMatricule(user.getMatricule());
		if (utilisateur !=null) {
			utilisateurs.remove(utilisateur);
		}else {
			throw new IllegalArgumentException("L'utilisateur demandé n'a pas été trouvé");
		}
		
	}

	@Override
	public Utilisateur rechercherUtilisateurParMatricule(String matricule) {
		for (Utilisateur utilisateur : utilisateurs) {
			if (utilisateur.getMatricule().equals(matricule)) {
				return utilisateur;
			}
		}
		return null;
	}

	@Override
	public List<Utilisateur> listerUtilisateur() {
		if (utilisateurs.isEmpty()) {
			throw new IllegalArgumentException("la base de données est vide");
		}
		return utilisateurs;
	}

}
