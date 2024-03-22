package eu.fr.indyli.formation.transactionnel.ecolis.controller;

import java.util.List;

import jakarta.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import eu.fr.indyli.formation.business.dto.EcolisUserDto;
import eu.fr.indyli.formation.business.ecolis.exception.EcolisBusinessException;
import eu.fr.indyli.formation.business.ecolis.service.IEcolisUserService;
import eu.fr.indyli.formation.business.utils.EcolisConstantes.EcolisConstantesService;
import eu.fr.indyli.formation.transactionnel.ecolis.utils.EcolisConstantesWeb.EcolisConstantesURI;

@RestController
@RequestMapping(EcolisConstantesURI.PATH_USER)
public class EcolisUserRestController {

  @Resource(name = EcolisConstantesService.USER_SERVICE_KEY)
  IEcolisUserService userService;

  @RequestMapping(method = RequestMethod.GET)
  public List<EcolisUserDto> listEmployees() {
    List<EcolisUserDto> usersList = userService.findAll();
    return usersList;
  }

  @RequestMapping(method = RequestMethod.POST,
      consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<?> creerNewUser(@RequestBody EcolisUserDto user) throws EcolisBusinessException {
	  
	  	if(StringUtils.isBlank(user.getEmail()) || StringUtils.isBlank(user.getLogin())) {
	  		return ResponseEntity.status(HttpStatus.PRECONDITION_REQUIRED) 
	  	            .body("L'email ou le login semble non renseigné...");
	  	}
	  	
		return ResponseEntity.ok(this.userService.create(user));
  }

  @PutMapping(value = "/{userId}", consumes = MediaType.APPLICATION_JSON_VALUE,produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<EcolisUserDto> updateUser(@RequestBody EcolisUserDto userACreerView, @PathVariable("userId") Integer userId) throws EcolisBusinessException {
	  EcolisUserDto userOne = this.userService.findById(userId);
	  if(userOne == null) {
		  return ResponseEntity.notFound().build(); 
	  }
	  
	  EcolisUserDto updateNewUser =  this.userService.update(userACreerView);
	  
	  return ResponseEntity.ok().body(updateNewUser);
  }

  @RequestMapping(value="/{userId}",method = RequestMethod.DELETE)
  public ResponseEntity<EcolisUserDto> deleteUserById(@PathVariable Integer userId) throws EcolisBusinessException  {
	this.userService.deleteById(userId);
	return ResponseEntity.ok().build(); 
  }

  @GetMapping(value = EcolisConstantesURI.PATH_USER_ID, produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<Object> findUser(@PathVariable Integer userId) {
    EcolisUserDto user;
    try {
      user = this.userService.findById(userId);
    } catch (EcolisBusinessException e) {
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
    }
    return ResponseEntity.ok(user);
  }

}
