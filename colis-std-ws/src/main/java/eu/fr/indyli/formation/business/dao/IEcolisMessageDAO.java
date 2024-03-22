package eu.fr.indyli.formation.business.dao;

import java.util.Date;
import java.util.List;

import eu.fr.indyli.formation.business.dto.EcolisMessageDto;
import eu.fr.indyli.formation.business.ecolis.exception.EcolisBusinessException;

public interface IEcolisMessageDAO extends IEntityDAO<EcolisMessageDto> {

	public void setMessageBodyByUserIdAndCreatedDate(String newMsg,Integer userId,Date dtCreation) throws EcolisBusinessException;
	public List<EcolisMessageDto> getMessageByEmailUser(String email) throws EcolisBusinessException;
}
