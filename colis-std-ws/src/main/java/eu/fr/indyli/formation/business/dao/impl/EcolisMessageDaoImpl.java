package eu.fr.indyli.formation.business.dao.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import eu.fr.indyli.formation.business.dao.IEcolisMessageDAO;
import eu.fr.indyli.formation.business.dto.EcolisAdvertisingDto;
import eu.fr.indyli.formation.business.dto.EcolisMessageDto;
import eu.fr.indyli.formation.business.dto.EcolisUserDto;
import eu.fr.indyli.formation.business.ecolis.exception.EcolisBusinessException;

public class EcolisMessageDaoImpl extends AbstractEntityDAOImpl<EcolisMessageDto> implements IEcolisMessageDAO {

	@Override
	public EcolisMessageDto findById(Integer id) {
		for (EcolisMessageDto message : entityInMemory) {
			if (message != null && message.getId().equals(id)) {
				return message;
			}
		}
		return null;
	}

	@Override
	public void setMessageBodyByUserIdAndCreatedDate(String newMsg, Integer userId, Date dtCreation)
			throws EcolisBusinessException {

		if (newMsg == null || userId == null || dtCreation == null) {
			throw new IllegalArgumentException("Les arguments ne doivent pas être null.");
		}
		for (EcolisMessageDto message : entityInMemory) {
			if (message.getUser() != null && userId.equals(message.getUser().getId())
					&& dtCreation.equals(message.getCreationDate())) {
				message.setCorps(newMsg);
			}
		}

	}

	@Override
	public List<EcolisMessageDto> getMessageByEmailUser(String email) throws EcolisBusinessException {

	    if (email == null) {
	        throw new IllegalArgumentException("L'email ne doit pas être null.");
	    }

	    List<EcolisMessageDto> messages = new ArrayList<>();
	    for (EcolisMessageDto message : entityInMemory) {
	        if (message.getUser() != null && message.getUser().getEmail() != null && message.getUser().getEmail().equalsIgnoreCase(email)) {
	            messages.add(message);
	        }
	    }
	    return messages;
	}


	@Override
	public void initData() {
		
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
		EcolisAdvertisingDto advertising1 = new EcolisAdvertisingDto();
        advertising1.setId(1);
        advertising1.setUser(user1); 
        advertising1.setParcelContents("Contenu du colis 1");
        advertising1.setPoids(5L);
        advertising1.setUnitePoids(1);
        advertising1.setDepositDate(new Date());
        advertising1.setStartDate(new Date());
        advertising1.setEndDate(new Date());
        advertising1.setStartCity("Paris");
        advertising1.setEndCity("Lyon");
        advertising1.setPrime(100L);
        advertising1.setDevise(1);
        advertising1.setStatut("Actif");
        advertising1.setAnnouncementType(1);
        advertising1.setDetail("Détails de l'annonce 1");
        EcolisAdvertisingDto advertising2 = new EcolisAdvertisingDto();
        advertising2.setId(2);
        advertising2.setUser(user2); 
        advertising2.setParcelContents("Contenu du colis 2");
        advertising2.setPoids(10L);
        advertising2.setUnitePoids(1);
        advertising2.setDepositDate(new Date());
        advertising2.setStartDate(new Date());
        advertising2.setEndDate(new Date());
        advertising2.setStartCity("Marseille");
        advertising2.setEndCity("Nice");
        advertising2.setPrime(200L);
        advertising2.setDevise(1);
        advertising2.setStatut("Inactif");
        advertising2.setAnnouncementType(2);
        advertising2.setDetail("Détails de l'annonce 2");
		entityInMemory = new ArrayList<EcolisMessageDto>();
		EcolisMessageDto message1 = new EcolisMessageDto();
		message1.setId(1);
		message1.setAnnouncement(advertising1);
		message1.setCorps("je suis corps de message1");
		message1.setCreationDate(new Date());
		message1.setUser(user1);
		entityInMemory.add(message1);
		EcolisMessageDto message2 = new EcolisMessageDto();
		message2.setId(2);
		message2.setAnnouncement(advertising2);
		message2.setCorps("je suis corps de message2");
		message2.setCreationDate(new Date());
		message2.setUser(user2);
		entityInMemory.add(message2);
	}

}
