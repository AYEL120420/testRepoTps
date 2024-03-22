package eu.fr.indyli.formation.business.ecolis.service.impl;

import java.util.Date;

import org.springframework.stereotype.Service;

import eu.fr.indyli.formation.business.dao.IEcolisCommentDAO;
import eu.fr.indyli.formation.business.dao.IEntityDAO;
import eu.fr.indyli.formation.business.dao.impl.EcolisCommentDAOImpl;
import eu.fr.indyli.formation.business.dto.EcolisCommentDto;
import eu.fr.indyli.formation.business.dto.EcolisCommentDto;
import eu.fr.indyli.formation.business.ecolis.service.ICommentService;
import eu.fr.indyli.formation.business.utils.EcolisConstantes.EcolisConstantesService;

@Service(EcolisConstantesService.COMMENT_SERVICE_KEY)
public class CommentServiceImpl extends AbstractEntityServiceImpl<EcolisCommentDto> implements ICommentService {

	private IEcolisCommentDAO commentDao = new EcolisCommentDAOImpl();
	@Override
	public IEntityDAO<EcolisCommentDto> getDAO() {
		// TODO Auto-generated method stub
		return this.commentDao;
	}
	@Override
	public EcolisCommentDto update(EcolisCommentDto comment) {
		
		EcolisCommentDto existingComment  = this.commentDao.findById(comment.getId());
		existingComment.setCorps(comment.getCorps());
		comment.setCreationDate(existingComment.getCreationDate());
		comment.setUser(existingComment.getUser());
		
	  	return this.commentDao.create(existingComment);
	}

	}

