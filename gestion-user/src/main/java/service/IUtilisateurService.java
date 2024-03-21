package service;

import java.util.List;

import entity.Utilisateur;

public interface IUtilisateurService {

	Utilisateur creerUtilisateur(Utilisateur user);
	Utilisateur modifierUtilisateur(Utilisateur user);
	void supprimerUtilisateur(Utilisateur user);
	Utilisateur rechercherUtilisateurParMatricule(String matricule);
	List<Utilisateur> listerUtilisateur();
}
