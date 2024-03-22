package eu.fr.indyli.formation.business.ecolis.service;

import java.util.Date;
import java.util.List;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import eu.fr.indyli.formation.business.dto.EcolisUserDto;
import eu.fr.indyli.formation.business.ecolis.exception.EcolisBusinessException;

/**
 * 
 * @author CHZOME
 *
 */
public interface IEcolisUserService extends IEntityService<EcolisUserDto> {

    /**
     * Remonte les auteurs de comment postés après une date et ayant déposé une annonce pour ville arrivee donnée
     * @param pDatePivot : Date pivot de depot du commentaire
     * @param pVilleArrivee : Ville d'arrivée
     * @return
     */
    public List<EcolisUserDto> findAuthorsCommentByDateAndPostedAnnonce(Date paramDatePivot,String paramVilleArrivee) throws EcolisBusinessException;
    
	/**
	 * Recherche les utilisateurs par email ou login
	 * @param login : Login pour lesquels on recherche des utilisateurs
	 * @param email : Email pour lesquels on recherche des utilisateurs
	 * @return
	 * @throws EcolisBusinessException
	 */
	 public List<EcolisUserDto> findByLoginOrEmail(String login,String email) throws EcolisBusinessException;
	 
	 public EcolisUserDto update(EcolisUserDto user) throws EcolisBusinessException;
	 public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException;
	 public EcolisUserDto findByLogin(String login);
}
