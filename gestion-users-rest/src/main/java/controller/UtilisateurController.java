package controller;

import java.util.List;

import entity.Utilisateur;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import service.IUtilisateurService;
import service.impl.UtilisateurServiceImpl;

@Path("/users")
public class UtilisateurController {
	
	private final IUtilisateurService utilisateurService = new UtilisateurServiceImpl();

//	@GET
//	@Produces(MediaType.APPLICATION_JSON)
//	public String sayHello() {
//		
//		return "hello";
//	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getAllUsers(){
		List<Utilisateur> utilisateurs = utilisateurService.listerUtilisateur(); 
		return Response.ok(utilisateurs).build();
	}
	
	@GET
	@Path("{matricule}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response chercherUtilisateurParMatricule(@PathParam("matricule") String matricule) {
		Utilisateur utilisateur = utilisateurService.rechercherUtilisateurParMatricule(matricule);
		if(utilisateur!=null) {
			return Response.ok(utilisateur).build();
		}
		return Response.status(Response.Status.NOT_FOUND).build();
	}
	
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response creerUtilisateur(Utilisateur utilisateur) {
		
		utilisateurService.creerUtilisateur(utilisateur);
		
		return Response.status(Response.Status.CREATED)
                .entity(utilisateur)
                .link(getUtilisateurURI(utilisateur.getMatricule()), "self")
                .build();
}
	@PUT
	@Path("{matricule}")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response modifierUtilisateur(@PathParam("matricule") String matricule, Utilisateur utilisateur) {
		Utilisateur utilisateurAmodif = utilisateurService.rechercherUtilisateurParMatricule(matricule);
		if(utilisateur!=null) {
			utilisateur.setMatricule(utilisateurAmodif.getMatricule());
			utilisateurService.modifierUtilisateur(utilisateur);
			return Response.ok(utilisateurAmodif).link(getUtilisateurURI(utilisateur.getMatricule()), "self").build();
		}
		return Response.status(Response.Status.NOT_FOUND).build();
		
	}
	
	@DELETE
	@Path("{matricule}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response supprimerUtilisateur(@PathParam("matricule") String matricule) {
		Utilisateur utilisateurAsupp = utilisateurService.rechercherUtilisateurParMatricule(matricule);
		if(utilisateurAsupp !=null) {
			
			utilisateurService.supprimerUtilisateur(utilisateurAsupp);
			return Response.noContent()
                    .link(suppUtilisateurURI(), "users")
                    .build();
		}
		return null;
		
	}
	private String suppUtilisateurURI() {
		// TODO Auto-generated method stub
		return "/gestion-users-rest/api/users/";
	}



	private String getUtilisateurURI(String matricule) {
		// TODO Auto-generated method stub
		return "/gestion-users-rest/api/users/" + matricule;
	}
}
