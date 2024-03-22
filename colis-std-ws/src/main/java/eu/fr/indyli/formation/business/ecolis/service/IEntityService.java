package eu.fr.indyli.formation.business.ecolis.service;

import java.util.List;

import eu.fr.indyli.formation.business.ecolis.exception.EcolisBusinessException;

public interface IEntityService<E> {
	public List<E> findAll();
	public E findById(Integer id) throws EcolisBusinessException;
	public void deleteById(Integer id) throws EcolisBusinessException;
	public E create(E ent) throws EcolisBusinessException;
}
