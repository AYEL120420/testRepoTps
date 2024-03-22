package eu.fr.indyli.formation.transactionnel.ecolis.controller;

import java.util.Date;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import eu.fr.indyli.formation.business.dto.EcolisAlertDto;
import eu.fr.indyli.formation.business.dto.EcolisMessageDto;
import eu.fr.indyli.formation.business.ecolis.exception.EcolisBusinessException;
import eu.fr.indyli.formation.business.ecolis.service.IMessageService;
import eu.fr.indyli.formation.business.utils.DateUtils;
import eu.fr.indyli.formation.business.utils.EcolisConstantes.EcolisConstantesService;
import eu.fr.indyli.formation.transactionnel.ecolis.utils.EcolisConstantesWeb.EcolisConstantesURI;
import jakarta.annotation.Resource;

@RestController
@RequestMapping(EcolisConstantesURI.PATH_MESSAGE)
public class EcolisMessageController {

	@Resource(name = EcolisConstantesService.MESSAGE_SERVICE_KEY)
	IMessageService messageService;

	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<EcolisMessageDto>> findAll(){
		List <EcolisMessageDto> messages= this.messageService.findAll();
		return ResponseEntity.ok(messages) ;
	}

	@RequestMapping(value = EcolisConstantesURI.PATH_MESSAGE_ID, method = RequestMethod.GET)
	public ResponseEntity<EcolisMessageDto> messageById(@PathVariable Integer messageId) {
		try {
			return ResponseEntity.ok(this.messageService.findById(messageId));
		} catch (EcolisBusinessException e) {

			e.printStackTrace();
		}
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
	}

	@RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<EcolisMessageDto> addMessage(@RequestBody EcolisMessageDto message) {
		try {
			return ResponseEntity.ok(this.messageService.create(message));
		} catch (EcolisBusinessException e) {
			// TODO Auto-generated catch block
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);

		}
	}
	@RequestMapping(value = EcolisConstantesURI.PATH_USER_MESSAGE, method = RequestMethod.GET)
	public ResponseEntity<List<EcolisMessageDto>> getMessageByEmailUser(@PathVariable("email") String email) {
	    try {
	        List<EcolisMessageDto> messages = messageService.getMessageByEmailUser(email);
	        return ResponseEntity.ok(messages);
	    } catch (Exception e) {
	        return ResponseEntity.badRequest().body(null);
	    }
	}

	
	@RequestMapping(value = EcolisConstantesURI.PATH_MESSAGE_ID, method = RequestMethod.DELETE)
	public ResponseEntity<Void> deleteMessageById(@PathVariable("messageId") Integer messageId) {
		try {
			messageService.deleteById(messageId);
		} catch (EcolisBusinessException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}
		return ResponseEntity.noContent().build();
	}
	@PutMapping(value = EcolisConstantesURI.PATH_MESSAGE_ID, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<EcolisMessageDto> updateMessage(@RequestBody EcolisMessageDto message,
			@PathVariable("messageId") Integer messageId) throws EcolisBusinessException {

		EcolisMessageDto updatedMessage = this.messageService.update(message);

		return ResponseEntity.ok().body(updatedMessage);
	}
	@RequestMapping(value = EcolisConstantesURI.PATH_USER_MESSAGE_DATE, method = RequestMethod.PUT)
	public ResponseEntity<?> setMessageBodyByUserIdAndCreatedDate(
	        @RequestBody String newMsg, 
	        @PathVariable("userId") Integer userId, 
	        @PathVariable("dtCreation") Long dtCreationTimestamp) {
	    try {
	        Date dtCreation = new Date(dtCreationTimestamp); // Convertir le timestamp en Date
	        messageService.setMessageBodyByUserIdAndCreatedDate(newMsg, userId, dtCreation);

	        return ResponseEntity.ok().build();
	    } catch (EcolisBusinessException e) {
	        return ResponseEntity.badRequest().body(e.getMessage());
	    } catch (Exception e) {
	        return ResponseEntity.internalServerError().body("An unexpected error occurred.");
	    }
	}

}
   
