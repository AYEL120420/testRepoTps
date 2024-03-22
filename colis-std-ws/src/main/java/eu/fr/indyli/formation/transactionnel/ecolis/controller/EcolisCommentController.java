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

import eu.fr.indyli.formation.business.dto.EcolisCommentDto;
import eu.fr.indyli.formation.business.ecolis.exception.EcolisBusinessException;
import eu.fr.indyli.formation.business.ecolis.service.ICommentService;
import eu.fr.indyli.formation.business.ecolis.service.impl.CommentServiceImpl;
import eu.fr.indyli.formation.business.utils.EcolisConstantes.EcolisConstantesService;
import eu.fr.indyli.formation.transactionnel.ecolis.utils.EcolisConstantesWeb.EcolisConstantesURI;
import jakarta.annotation.Resource;

@RestController
@RequestMapping(EcolisConstantesURI.PATH_COMMENT)
public class EcolisCommentController {

	@Resource(name = EcolisConstantesService.COMMENT_SERVICE_KEY)
	ICommentService commentService = new CommentServiceImpl();
	
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<EcolisCommentDto>> findAll(){
		List <EcolisCommentDto> comments= this.commentService.findAll();
		return ResponseEntity.ok(comments) ;
	}
	
	@RequestMapping(value= EcolisConstantesURI.PATH_COMMENT_ID, method = RequestMethod.GET)
	public ResponseEntity<EcolisCommentDto> findById(@PathVariable( "commentId") Integer id) {
		
			EcolisCommentDto alert;
			try {
				alert = this.commentService.findById(id);
				return ResponseEntity.ok(alert);
			} catch (EcolisBusinessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			
			}
		
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		
	}
		

	
	@RequestMapping(value = EcolisConstantesURI.PATH_COMMENT_ID, method = RequestMethod.DELETE)
	public ResponseEntity<Void> deleteComment (@PathVariable("commentId") Integer commentId) throws EcolisBusinessException {
		this.commentService.deleteById(commentId);
		return ResponseEntity.noContent().build();
	}
	

	@RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> createComment(@RequestBody EcolisCommentDto comment) {
		try {
			return ResponseEntity.ok(this.commentService.create(comment));
		} catch (EcolisBusinessException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
		}
	}

	@PutMapping(value = EcolisConstantesURI.PATH_COMMENT_ID, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<EcolisCommentDto> updateComment(@RequestBody EcolisCommentDto comment,
			@PathVariable("commentId") Integer commentId) throws EcolisBusinessException {
		EcolisCommentDto existingComment = this.commentService.findById(commentId);
		if (existingComment == null) {
			return ResponseEntity.notFound().build();
		}

		EcolisCommentDto updatedComment = this.commentService.update(comment);

		return ResponseEntity.ok().body(updatedComment);
	}
}
