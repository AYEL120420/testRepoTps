package eu.fr.indyli.formation.transactionnel.ecolis.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import eu.fr.indyli.formation.business.dto.EcolisAlertDto;
import eu.fr.indyli.formation.business.ecolis.exception.EcolisBusinessException;
import eu.fr.indyli.formation.business.ecolis.service.IAlertService;
import eu.fr.indyli.formation.business.ecolis.service.impl.AlertServiceImpl;
import eu.fr.indyli.formation.business.utils.EcolisConstantes.EcolisConstantesService;
import eu.fr.indyli.formation.transactionnel.ecolis.utils.EcolisConstantesWeb.EcolisConstantesURI;
import jakarta.annotation.Resource;

@RestController
@RequestMapping(EcolisConstantesURI.PATH_ALERT)
public class EcolisAlertController {
	@Resource(name = EcolisConstantesService.ALERTE_SERVICE_KEY)
	IAlertService alertService = new AlertServiceImpl();
	
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<EcolisAlertDto>> findAll(){
		List <EcolisAlertDto> alerts= this.alertService.findAll();
		return ResponseEntity.ok(alerts) ;
	}
	
	@RequestMapping(value= EcolisConstantesURI.PATH_ALERT_ID, method = RequestMethod.GET)
	public ResponseEntity<EcolisAlertDto> findById(@PathVariable( "alertId") Integer id) {
		
			EcolisAlertDto alert;
			try {
				alert = this.alertService.findById(id);
				return ResponseEntity.ok(alert);
			} catch (EcolisBusinessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			
			}
		
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		
	}
		
	@RequestMapping(value= EcolisConstantesURI.PATH_ALERT_CITY, method = RequestMethod.GET)
	public ResponseEntity<List<EcolisAlertDto>> findByCity(@PathVariable( "villeDep") String villeDep ){
		List <EcolisAlertDto> alerts= this.alertService.findByVilleDepart(villeDep);
		return ResponseEntity.ok(alerts) ;
	}
	
	@RequestMapping(value = EcolisConstantesURI.PATH_ALERT_ID, method = RequestMethod.DELETE)
	public ResponseEntity<Void> deleteAlert (@PathVariable("alertId") Integer alertId) throws EcolisBusinessException {
		this.alertService.deleteById(alertId);
		return ResponseEntity.noContent().build();
	}
	

	@RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> createAlert(@RequestBody EcolisAlertDto alerte) {
		try {
			return ResponseEntity.ok(this.alertService.create(alerte));
		} catch (EcolisBusinessException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
		}
	}

	@PutMapping(value = EcolisConstantesURI.PATH_ALERT_ID, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<EcolisAlertDto> updateAlert(@RequestBody EcolisAlertDto alerte,
			@PathVariable("alertId") Integer alertId) throws EcolisBusinessException {
		EcolisAlertDto existingAlert = this.alertService.findById(alertId);
		if (existingAlert == null) {
			return ResponseEntity.notFound().build();
		}

		EcolisAlertDto updatedAlert = this.alertService.update(alerte);

		return ResponseEntity.ok().body(updatedAlert);
	}
}
