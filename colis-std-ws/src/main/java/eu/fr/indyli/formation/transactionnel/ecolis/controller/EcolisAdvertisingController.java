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

import eu.fr.indyli.formation.business.dto.EcolisAdvertisingDto;
import eu.fr.indyli.formation.business.ecolis.exception.EcolisBusinessException;
import eu.fr.indyli.formation.business.ecolis.service.IAdvertisingService;
import eu.fr.indyli.formation.business.ecolis.service.impl.AdvertisingServiceImpl;
import eu.fr.indyli.formation.business.utils.EcolisConstantes.EcolisConstantesService;
import eu.fr.indyli.formation.transactionnel.ecolis.utils.EcolisConstantesWeb.EcolisConstantesURI;
import jakarta.annotation.Resource;

@RestController
@RequestMapping(EcolisConstantesURI.PATH_ADVERTISING)
public class EcolisAdvertisingController {

	@Resource(name = EcolisConstantesService.ADVERTISING_SERVICE_KEY)
IAdvertisingService advertisingService = new AdvertisingServiceImpl();
	
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<EcolisAdvertisingDto>> findAll(){
		List <EcolisAdvertisingDto> advertisings= this.advertisingService.findAll();
		return ResponseEntity.ok(advertisings) ;
	}
	
	@RequestMapping(value= EcolisConstantesURI.PATH_ADVERTISING_ID, method = RequestMethod.GET)
	public ResponseEntity<EcolisAdvertisingDto> findById(@PathVariable( "advertisingId") Integer id) {
		
			EcolisAdvertisingDto advertising;
			try {
				advertising = this.advertisingService.findById(id);
				return ResponseEntity.ok(advertising);
			} catch (EcolisBusinessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			
			}
		
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		
	}
		
	@RequestMapping(value= EcolisConstantesURI.PATH_ADVERTISING_CITY, method = RequestMethod.GET)
	public ResponseEntity<List<EcolisAdvertisingDto>> findByCity(@PathVariable( "villeDep") String villeDep ){
		List <EcolisAdvertisingDto> advirtisings= this.advertisingService.findByVilleDepartContaining(villeDep);
		return ResponseEntity.ok(advirtisings) ;
	}
	
	@RequestMapping(value = EcolisConstantesURI.PATH_ADVERTISING_ID, method = RequestMethod.DELETE)
	public ResponseEntity<Void> deleteAdvertising (@PathVariable("advertisingId") Integer advertisingtId) throws EcolisBusinessException {
		this.advertisingService.deleteById(advertisingtId);
		return ResponseEntity.noContent().build();
	}
	

	@RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> createAlert(@RequestBody EcolisAdvertisingDto alerte) {
		try {
			return ResponseEntity.ok(this.advertisingService.create(alerte));
		} catch (EcolisBusinessException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
		}
	}

	@PutMapping(value = EcolisConstantesURI.PATH_ADVERTISING_ID, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<EcolisAdvertisingDto> updateAlert(@RequestBody EcolisAdvertisingDto advertising,
			@PathVariable("advertisingId") Integer advertisingId) throws EcolisBusinessException {
		EcolisAdvertisingDto existingAdvertising = this.advertisingService.findById(advertisingId);
		if (existingAdvertising == null) {
			return ResponseEntity.notFound().build();
		}

		EcolisAdvertisingDto updatedAdvertising = this.advertisingService.update(advertising);

		return ResponseEntity.ok().body(updatedAdvertising);
	}
}

