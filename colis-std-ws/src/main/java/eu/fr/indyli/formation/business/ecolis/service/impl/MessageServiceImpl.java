package eu.fr.indyli.formation.business.ecolis.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Service;

import eu.fr.indyli.formation.business.dao.IEcolisMessageDAO;
import eu.fr.indyli.formation.business.dao.IEntityDAO;
import eu.fr.indyli.formation.business.dao.impl.EcolisMessageDaoImpl;
import eu.fr.indyli.formation.business.dto.EcolisAlertDto;
import eu.fr.indyli.formation.business.dto.EcolisMessageDto;
import eu.fr.indyli.formation.business.ecolis.exception.EcolisBusinessException;
import eu.fr.indyli.formation.business.ecolis.service.IMessageService;
import eu.fr.indyli.formation.business.utils.EcolisConstantes.EcolisConstantesService;

@Service(EcolisConstantesService.MESSAGE_SERVICE_KEY)
public class MessageServiceImpl extends AbstractEntityServiceImpl<EcolisMessageDto> implements IMessageService {

	private IEcolisMessageDAO messageDao = new EcolisMessageDaoImpl();
	 @Override
	    public void setMessageBodyByUserIdAndCreatedDate(String newMsg, Integer userId, Date dtCreation)
	            throws EcolisBusinessException {
	        messageDao.setMessageBodyByUserIdAndCreatedDate(newMsg, userId, dtCreation);
	    }

	    @Override
	    public List<EcolisMessageDto> getMessageByEmailUser(String email) throws EcolisBusinessException {
	        return messageDao.getMessageByEmailUser(email);
	    }


	@Override
	public IEntityDAO<EcolisMessageDto> getDAO() {
		// TODO Auto-generated method stub
		return this.messageDao;
	}

	@Override
	public EcolisMessageDto update(EcolisMessageDto message) {
		EcolisMessageDto existingMessage  = this.messageDao.findById(message.getId());
		existingMessage.setCorps(message.getCorps());
		message.setCreationDate(existingMessage.getCreationDate());
		message.setAnnouncement(existingMessage.getAnnouncement());
		message.setUser(existingMessage.getUser());;
		
	  	return this.messageDao.create(existingMessage);
	}
	}

