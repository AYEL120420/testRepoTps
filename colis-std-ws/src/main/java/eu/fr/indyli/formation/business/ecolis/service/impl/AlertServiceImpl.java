package eu.fr.indyli.formation.business.ecolis.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Service;

import eu.fr.indyli.formation.business.dao.IEcolisAlertDAO;
import eu.fr.indyli.formation.business.dao.IEntityDAO;
import eu.fr.indyli.formation.business.dao.impl.EcolisAlertDAOImpl;
import eu.fr.indyli.formation.business.dto.EcolisAlertDto;
import eu.fr.indyli.formation.business.ecolis.service.IAlertService;
import eu.fr.indyli.formation.business.utils.EcolisConstantes.EcolisConstantesService;

@Service(EcolisConstantesService.ALERTE_SERVICE_KEY)
public class AlertServiceImpl extends AbstractEntityServiceImpl<EcolisAlertDto> implements IAlertService {

	private IEcolisAlertDAO alertDao = new EcolisAlertDAOImpl();
	@Override
	public List<EcolisAlertDto> findByVilleDepart(String villeDep) {
		List<EcolisAlertDto> alerts = this.alertDao.findByVilleDepart(villeDep);
		return alerts;
	}

	@Override
	public IEntityDAO<EcolisAlertDto> getDAO() {
		// TODO Auto-generated method stub
		return this.alertDao;
	}

	@Override
	public EcolisAlertDto update(EcolisAlertDto alerte) {
		EcolisAlertDto existingAlerte  = this.alertDao.findById(alerte.getId());
		existingAlerte.setDepositDate(new Date());;
		existingAlerte.setStartCity(alerte.getStartCity());;
		existingAlerte.setEndCity(alerte.getEndCity());
		alerte.setUser(existingAlerte.getUser());;
		
	  	return this.alertDao.create(existingAlerte);
	}

}
