package eu.fr.indyli.formation.business.ecolis.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import eu.fr.indyli.formation.business.dao.IEntityDAO;
import eu.fr.indyli.formation.business.dao.IEcolisAdvertisingDAO;
import eu.fr.indyli.formation.business.dao.impl.EcolisAdvertisingDAOImpl;
import eu.fr.indyli.formation.business.dto.EcolisAdvertisingDto;
import eu.fr.indyli.formation.business.dto.EcolisUserDto;
import eu.fr.indyli.formation.business.ecolis.exception.EcolisBusinessException;
import eu.fr.indyli.formation.business.ecolis.service.IAdvertisingService;
import eu.fr.indyli.formation.business.utils.EcolisConstantes.EcolisConstantesService;

@Service(EcolisConstantesService.ADVERTISING_SERVICE_KEY)
public class AdvertisingServiceImpl extends AbstractEntityServiceImpl<EcolisAdvertisingDto> implements IAdvertisingService {

	private IEcolisAdvertisingDAO advertisingDao = new EcolisAdvertisingDAOImpl();
	@Override
	public List<EcolisAdvertisingDto> findByVilleDepartContaining(String departureTown) {
		List<EcolisAdvertisingDto> advertisings = this.advertisingDao.findByVilleDepartContaining(departureTown);
		return advertisings;
	}


	@Override
	public void setPrimeForSomeDeparture(String departureTown, long prime) throws EcolisBusinessException {
		 this.advertisingDao.setPrimeForSomeDeparture(departureTown, prime);	
	}

	@Override
	public IEntityDAO<EcolisAdvertisingDto> getDAO() {
		return this.advertisingDao;
	}



	@Override
	public EcolisAdvertisingDto update(EcolisAdvertisingDto advertising) {
		EcolisAdvertisingDto existingAdvertising  = this.advertisingDao.findById(advertising.getId());

		existingAdvertising.setAnnouncementType(advertising.getAnnouncementType());
		existingAdvertising.setDepositDate(existingAdvertising.getDepositDate());
		existingAdvertising.setDetail(advertising.getDetail());		
		existingAdvertising.setDevise(advertising.getDevise());
		existingAdvertising.setEndCity(advertising.getEndCity());
		existingAdvertising.setEndDate(existingAdvertising.getEndDate());
		existingAdvertising.setMessages(advertising.getMessages());
		existingAdvertising.setParcelContents(advertising.getParcelContents());
		existingAdvertising.setPoids(advertising.getPoids());
		existingAdvertising.setPrime(advertising.getPrime());
		existingAdvertising.setStartCity(advertising.getStartCity());
		existingAdvertising.setStartDate(existingAdvertising.getStartDate());
		existingAdvertising.setStatut(advertising.getStatut());
		existingAdvertising.setUnitePoids(advertising.getUnitePoids());
		advertising.setUser(existingAdvertising.getUser());
		EcolisAdvertisingDto updatedAdvertising = this.advertisingDao.create(existingAdvertising);
	    return updatedAdvertising;
		
}
}
