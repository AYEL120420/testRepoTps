package eu.fr.indyli.formation.business.ecolis.service;

import java.util.List;

import eu.fr.indyli.formation.business.dto.EcolisAlertDto;

public interface IAlertService  extends IEntityService<EcolisAlertDto> {

	public List<EcolisAlertDto> findByVilleDepart(String villeDep);

	public EcolisAlertDto update(EcolisAlertDto alerte);
}
