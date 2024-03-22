package eu.fr.indyli.formation.business.dao.impl;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import eu.fr.indyli.formation.business.dao.IEcolisAdvertisingDAO;
import eu.fr.indyli.formation.business.dto.EcolisAdvertisingDto;
import eu.fr.indyli.formation.business.dto.EcolisUserDto;
import eu.fr.indyli.formation.business.ecolis.exception.EcolisBusinessException;

public class EcolisAdvertisingDAOImpl extends AbstractEntityDAOImpl<EcolisAdvertisingDto> implements IEcolisAdvertisingDAO {

	@Override
	public EcolisAdvertisingDto findById(Integer id) {
		for (EcolisAdvertisingDto advertising : entityInMemory) {
			if (advertising !=null && advertising.getId().equals(id)){
				return advertising;
			}
		}
		return null;
	}

	@Override
	public List<EcolisAdvertisingDto> findByVilleDepartContaining(String departureTown) {
		
	List <EcolisAdvertisingDto> advertissements = new ArrayList<>();
		for (EcolisAdvertisingDto advertising : entityInMemory) {
			if (advertising !=null && advertising.getStartCity().equalsIgnoreCase(departureTown)){
			advertissements.add(advertising);
			}
			return advertissements;
		}
		return null;
	}

	@Override
	public void setPrimeForSomeDeparture(String departureTown, long prime) throws EcolisBusinessException {
		
		List<EcolisAdvertisingDto> advertissements = findByVilleDepartContaining(departureTown);
		for (EcolisAdvertisingDto advertising : advertissements) {
			advertising.setPrime(prime);
		}
	}

	@Override
	public void initData() {
		
		entityInMemory =  new ArrayList<EcolisAdvertisingDto>();
		
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
	        entityInMemory.add(advertising1);
	        
	        // Instanciation du deuxième objet EcolisAdvertisingDto
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
	        entityInMemory.add(advertising2);
	}

}
