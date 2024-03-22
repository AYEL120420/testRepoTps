package eu.fr.indyli.formation.business.dto;
// Generated 25 juin 2017 02:21:10 by Hibernate Tools 5.2.0.CR1

import java.util.Date;

public class EcolisImageDto extends AbstractEcolisEntity implements  IEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6055938211600328115L;
	private Integer idImage;
	private EcolisUserDto utilisateur;
	private String chemin;
	private Date dateCreation;

	public EcolisImageDto() {
	}

	public EcolisImageDto(EcolisUserDto utilisateur) {
		this.utilisateur = utilisateur;
	}

	public EcolisImageDto(EcolisUserDto utilisateur, String chemin, Date dateCreation) {
		this.utilisateur = utilisateur;
		this.chemin = chemin;
		this.dateCreation = dateCreation;
	}
	
	@Override
	public Integer getId() {
		return this.idImage;
	}

	@Override
	public void setId(Integer id) {
		this.idImage = id;
	}

	public EcolisUserDto getUtilisateur() {
		return this.utilisateur;
	}

	public void setUtilisateur(EcolisUserDto utilisateur) {
		this.utilisateur = utilisateur;
	}

	public String getChemin() {
		return this.chemin;
	}

	public void setChemin(String chemin) {
		this.chemin = chemin;
	}

	public Date getDateCreation() {
		return this.dateCreation;
	}

	public void setDateCreation(Date dateCreation) {
		this.dateCreation = dateCreation;
	}

}
