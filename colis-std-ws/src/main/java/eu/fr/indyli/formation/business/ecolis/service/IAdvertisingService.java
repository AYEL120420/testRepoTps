package eu.fr.indyli.formation.business.ecolis.service;

import java.util.List;

import eu.fr.indyli.formation.business.dto.EcolisAdvertisingDto;
import eu.fr.indyli.formation.business.ecolis.exception.EcolisBusinessException;

public interface IAdvertisingService extends IEntityService<EcolisAdvertisingDto> {

	public List<EcolisAdvertisingDto> findByVilleDepartContaining(String departureTown);  
	public void setPrimeForSomeDeparture(String departureTown, long prime) throws EcolisBusinessException;
	public EcolisAdvertisingDto update(EcolisAdvertisingDto advertising);
}
