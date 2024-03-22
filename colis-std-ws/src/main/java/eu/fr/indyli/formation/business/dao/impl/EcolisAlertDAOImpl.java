package eu.fr.indyli.formation.business.dao.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import eu.fr.indyli.formation.business.dao.IEcolisAlertDAO;
import eu.fr.indyli.formation.business.dto.EcolisAlertDto;
import eu.fr.indyli.formation.business.dto.EcolisUserDto;

public class EcolisAlertDAOImpl extends AbstractEntityDAOImpl<EcolisAlertDto> implements IEcolisAlertDAO {

	@Override
	public EcolisAlertDto findById(Integer id) {
		for (EcolisAlertDto alert : entityInMemory) {
			if (alert !=null && alert.getId().equals(id)){
				return alert;
			}
		}
		return null;
	}

	@Override
	public List<EcolisAlertDto> findByVilleDepart(String villeDep) {
	    List<EcolisAlertDto> alerts = new ArrayList<>();
	    for (EcolisAlertDto alert : entityInMemory) {
	        if (alert != null && alert.getStartCity().equalsIgnoreCase(villeDep)) {
	            alerts.add(alert);
	        }
	    }
	    return alerts;
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
		
		EcolisAlertDto alerte1 = new EcolisAlertDto();
		alerte1.setId(1);
        alerte1.setUser(user1);
        alerte1.setStartCity("Paris");
        alerte1.setEndCity("Lyon");
        alerte1.setDepositDate(new Date());
        entityInMemory.add(alerte1);
        // Instanciation de la deuxième alerte
        EcolisAlertDto alerte2 = new EcolisAlertDto();
        
        alerte2.setId(2);
        alerte2.setUser(user2);
        alerte2.setStartCity("Marseille");
        alerte2.setEndCity("Nice");
        alerte2.setDepositDate(new Date());
        entityInMemory.add(alerte2);
		
	}

}
