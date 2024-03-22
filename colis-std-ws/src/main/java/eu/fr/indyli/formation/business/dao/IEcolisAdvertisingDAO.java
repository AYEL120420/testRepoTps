package eu.fr.indyli.formation.business.dao;

import java.util.List;

import eu.fr.indyli.formation.business.dto.EcolisAdvertisingDto;
import eu.fr.indyli.formation.business.dto.EcolisCommentDto;
import eu.fr.indyli.formation.business.ecolis.exception.EcolisBusinessException;

public interface IEcolisAdvertisingDAO extends IEntityDAO<EcolisAdvertisingDto> {

	public List<EcolisAdvertisingDto> findByVilleDepartContaining(String departureTown);  
	public void setPrimeForSomeDeparture(String departureTown, long prime) throws EcolisBusinessException;
	 
}
