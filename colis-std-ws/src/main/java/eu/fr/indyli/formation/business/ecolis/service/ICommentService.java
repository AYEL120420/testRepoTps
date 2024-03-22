package eu.fr.indyli.formation.business.ecolis.service;

import eu.fr.indyli.formation.business.dto.EcolisCommentDto;

public interface ICommentService extends IEntityService<EcolisCommentDto>{

	EcolisCommentDto update(EcolisCommentDto comment);

}
