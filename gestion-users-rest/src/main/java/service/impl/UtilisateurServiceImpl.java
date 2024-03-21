package service.impl;

import java.util.ArrayList;
import java.util.List;

import entity.Utilisateur;
import service.IUtilisateurService;


public class UtilisateurServiceImpl implements IUtilisateurService{
	
	private List<Utilisateur> utilisateurs = new ArrayList<Utilisateur>();
	
	public UtilisateurServiceImpl() {
		Utilisateur user1 = new Utilisateur("00001", "John", "Doe");
		Utilisateur user2 = new Utilisateur("00002", "Jeanette", "Doe");
		Utilisateur user3 = new Utilisateur("00003", "Jocelin", "Fomekong");
		
		this.utilisateurs.add(user1);
		this.utilisateurs.add(user2);
		this.utilisateurs.add(user3);
	}

	public Utilisateur creerUtilisateur( Utilisateur user) {
		// TODO Auto-generated method stub
		System.out.println(utilisateurs.toString());
		utilisateurs.add(user);
		System.out.println(utilisateurs.toString());
		return user;
	}

	public Utilisateur modifierUtilisateur(Utilisateur user) {
		// TODO Auto-generated method stub
		System.out.println(utilisateurs.toString());
		Utilisateur utilisateur = rechercherUtilisateurParMatricule(user.getMatricule());
		if(utilisateur != null) {
			utilisateur.setNom(user.getNom());
			utilisateur.setPrenom(user.getPrenom());
			System.out.println(utilisateurs.toString());
		}
		return null;
	}

	public void supprimerUtilisateur(Utilisateur user) {
		// TODO Auto-generated method stub
		System.out.println(utilisateurs.toString());
		Utilisateur utilisateur = rechercherUtilisateurParMatricule(user.getMatricule());
		if(utilisateur != null) {
			utilisateurs.remove(utilisateur);
			System.out.println(utilisateurs.toString());
		} else {
			throw new IllegalStateException("Aucun utilisateur a supprimer");
		}
		
	}

	public Utilisateur rechercherUtilisateurParMatricule(String matricule) {
		// TODO Auto-generated method stub
		for(Utilisateur utilisateur : utilisateurs) {
			if(utilisateur.getMatricule().equals(matricule)){
				return utilisateur;
			};
		}
		return null;
	}

	public List<Utilisateur> listerUtilisateur() {
		// TODO Auto-generated method stub
		if(utilisateurs.isEmpty()) {
			throw new IllegalStateException("Votre Base est vide");
		}
		return utilisateurs;
	}

	
}
