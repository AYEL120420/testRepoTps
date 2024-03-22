package eu.fr.indyli.formation.business.dao.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import eu.fr.indyli.formation.business.dao.IEcolisUserDAO;
import eu.fr.indyli.formation.business.dto.EcolisUserDto;
import eu.fr.indyli.formation.business.ecolis.exception.EcolisBusinessException;

public class EcolisUserDAOImpl extends AbstractEntityDAOImpl<EcolisUserDto> implements IEcolisUserDAO{
	
	 private BCryptPasswordEncoder bcryptEncoder;
	@Override
	public EcolisUserDto findById(Integer id) {
		for (EcolisUserDto entity : entityInMemory) {
	        if (entity != null && entity.getId().equals(id)) {
	            return entity;
	        }
	    }
	    return null;
	}

	//à ne pas implementer 
	@Override
	public List<EcolisUserDto> findAuthorsCommentByDateAndPostedAnnonce(Date paramDatePivot,
			String paramVilleArrivee) {
		// TODO Auto-generated method stub
		return null;
	}
 // à ne pas faire 
	@Override
	public List<EcolisUserDto> getUtilisateurParDomaineEtPossedantTel(String domaineP) throws EcolisBusinessException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<EcolisUserDto> findByLoginOrEmail(String login, String email) throws EcolisBusinessException {
		if (login == null && email == null) {
	        throw new EcolisBusinessException("Login et email ne peuvent pas être tous les deux null");
	    }
		List<EcolisUserDto> result = new ArrayList<>();
		
		for (EcolisUserDto user : entityInMemory) {
	        // Vérifiez si l'utilisateur a le login ou l'email correspondant
	        if (user != null && (user.getLogin().equals(login) || user.getEmail().equals(email))) {
	            result.add(user);
	        }
	    }
	    return result;
	}
	@Override
	public EcolisUserDto findByLogin(String login) {
				
		for (EcolisUserDto user : entityInMemory) {
	        // Vérifiez si l'utilisateur a le login ou l'email correspondant
	        if (user != null && (user.getLogin().equals(login))) {
	          return user;
	        }
		}
		return null;
	}

	@Override
	public EcolisUserDto findByEmail(String email) {
		
		for (EcolisUserDto user : entityInMemory) {
	        // Vérifiez si l'utilisateur a le login ou l'email correspondant
	        if (user != null && (user.getLogin().equals(email))) {
	          return user;
	        }
	    }
		return null;
	}


	@Override
	public EcolisUserDto findByLoginAndPassword(String login, String password) throws EcolisBusinessException {
	    if (login == null || password == null) { 
	        throw new EcolisBusinessException("Login et mot de passe ne peuvent pas être tous les deux null");
	    }
	    
	    PasswordEncoder passwordEncoder = this.bcryptEncoder;
	    
	    for (EcolisUserDto user : entityInMemory) {
	        if (user != null && user.getLogin().equals(login)) {
	   
	            if (passwordEncoder.matches(password, user.getPassword())) {
	                return user; 
	            }
	        }
	    }
	    return null; 
	}

	

	@Override
	public void initData() {
		entityInMemory =  new ArrayList<EcolisUserDto>();
		EcolisUserDto user1 = new EcolisUserDto();
		String encodedPassword1 = this.getPasswordEncoder().encode("pBlankPass");
		user1.setYearOfBirth(1984);
		user1.setCivility("M.");
		user1.setRegistrationDate(new Date());
		user1.setEmail("czo@yahoo.fr");
		user1.setId(1);
		user1.setLogin("czo");
		user1.setName("Blanka");
		user1.setPassword(encodedPassword1);
		user1.setPhone("023698547");
		entityInMemory.add(user1);
		
		//User2
		EcolisUserDto user2 = new EcolisUserDto();
		String encodedPassword2 = this.getPasswordEncoder().encode("pLuc");
		user2.setYearOfBirth(1990);
		user2.setCivility("Mme.");
		user2.setRegistrationDate(new Date());
		user2.setEmail("linda.luc@yahoo.fr");
		user2.setId(2);
		user2.setLogin("lucd");
		user2.setName("Djeumeni Luc");
		user2.setPassword(encodedPassword2);
		user2.setPhone("14582236");
		entityInMemory.add(user2);
		
		//User3
		EcolisUserDto user3 = new EcolisUserDto();
		String encodedPassword3 = this.getPasswordEncoder().encode("abloti");
		user3.setYearOfBirth(1989);
		user3.setCivility("Mr.");
		user3.setRegistrationDate(new Date());
		user3.setEmail("abdou.zoyim@indyli-services.com");
		user3.setId(3);
		user3.setLogin("loti");
		user3.setName("Abdou Zoyim");
		user3.setPassword(encodedPassword3);
		user3.setPhone("069852417");
		entityInMemory.add(user3);
		
		//User4
		EcolisUserDto user4 = new EcolisUserDto();
		String encodedPassword4 = this.getPasswordEncoder().encode("fjocelin");
		user4.setYearOfBirth(1985);
		user4.setCivility("Mr.");
		user4.setRegistrationDate(new Date());
		user4.setEmail("fomekong.jocelin@indyli-services.com");
		user4.setId(4);
		user4.setLogin("jocelin");
		user4.setName("Fomekong Jocelin");
		user4.setPassword(encodedPassword4);
		user4.setPhone("06582596");
		entityInMemory.add(user4);
		
		//User5
		EcolisUserDto user5 = new EcolisUserDto();
		String encodedPassword5 = this.getPasswordEncoder().encode("galiou");
		user5.setYearOfBirth(1998);
		user5.setCivility("Mr.");
		user5.setRegistrationDate(new Date());
		user5.setEmail("aliou.garga@indyli-services.com");
		user5.setId(5);
		user5.setLogin("garga");
		user5.setName("Garga Aliou");
		user5.setPassword(encodedPassword5);
		user5.setPhone("069852413");
		entityInMemory.add(user5);
		
		//User6
		EcolisUserDto user6 = new EcolisUserDto();
		String encodedPassword6 = this.getPasswordEncoder().encode("tcyril");
		user6.setYearOfBirth(1992);
		user6.setCivility("Mr.");
		user6.setRegistrationDate(new Date());
		user6.setEmail("tingah.cyril@indyli-services.com");
		user6.setId(6);
		user6.setLogin("cyril");
		user6.setName("Tingah Cyril");
		user6.setPassword(encodedPassword6);
		user6.setPhone("065987412");
		entityInMemory.add(user6);
	}

	



}
