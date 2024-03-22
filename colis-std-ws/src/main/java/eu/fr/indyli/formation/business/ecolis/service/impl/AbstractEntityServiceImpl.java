package eu.fr.indyli.formation.business.ecolis.service.impl;

import java.util.List;

import eu.fr.indyli.formation.business.dao.IEntityDAO;
import eu.fr.indyli.formation.business.ecolis.exception.EcolisBusinessException;
import eu.fr.indyli.formation.business.ecolis.service.IEntityService;

public abstract class AbstractEntityServiceImpl<E> implements IEntityService<E>{

	public abstract IEntityDAO<E> getDAO();
	@Override
	public List<E> findAll() {
		// TODO Auto-generated method stub
		return this.getDAO().findAll();
	}

	@Override
	public E findById(Integer id) throws EcolisBusinessException {
		// TODO Auto-generated method stub
		return this.getDAO().findById(id);
	}

	@Override
	public void deleteById(Integer id) {
		this.getDAO().deleteById(id);
	}

	@Override
	public E create(E ent) throws EcolisBusinessException {
		this.getDAO().create(ent);
		return ent;

	}

}
