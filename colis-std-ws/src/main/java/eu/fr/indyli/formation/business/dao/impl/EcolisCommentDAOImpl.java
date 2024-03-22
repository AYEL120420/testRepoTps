package eu.fr.indyli.formation.business.dao.impl;

import java.util.ArrayList;
import java.util.Date;

import eu.fr.indyli.formation.business.dao.IEcolisCommentDAO;
import eu.fr.indyli.formation.business.dto.EcolisAlertDto;
import eu.fr.indyli.formation.business.dto.EcolisCommentDto;
import eu.fr.indyli.formation.business.dto.EcolisUserDto;

public class EcolisCommentDAOImpl extends AbstractEntityDAOImpl<EcolisCommentDto> implements IEcolisCommentDAO {

	@Override
	public EcolisCommentDto findById(Integer id) {
		for (EcolisCommentDto comment : entityInMemory) {
			if (comment !=null && comment.getId().equals(id)){
				return comment;
			}
		}
		return null;
	}

	@Override
	public void initData() {
		EcolisUserDto user1 = new EcolisUserDto();
		String encodedPassword1 = this.getPasswordEncoder().encode("pBlankPass");
		user1.setYearOfBirth(1984);
		user1.setCivility("M.");
		user1.setRegistrationDate(new Date());
		user1.setEmail("czo@yahoo.fr");
		user1.setId(1);
		user1.setLogin("czo");
		user1.setName("Blanka");
		user1.setPassword(encodedPassword1);
		user1.setPhone("023698547");
		entityInMemory = new ArrayList<>();
		EcolisCommentDto comment1 = new EcolisCommentDto();
		comment1.setId(1);
		comment1.setCorps("je suis corps de comment1");
		comment1.setCreationDate(new Date());
		comment1.setUser(user1);
		entityInMemory.add(comment1);
		EcolisUserDto user2 = new EcolisUserDto();
		String encodedPassword2 = this.getPasswordEncoder().encode("pLuc");
		user2.setYearOfBirth(1990);
		user2.setCivility("Mme.");
		user2.setRegistrationDate(new Date());
		user2.setEmail("linda.luc@yahoo.fr");
		user2.setId(2);
		user2.setLogin("lucd");
		user2.setName("Djeumeni Luc");
		user2.setPassword(encodedPassword2);
		user2.setPhone("14582236");
		EcolisCommentDto comment2 = new EcolisCommentDto();
		comment2.setId(2);
		comment2.setCorps("je suis corps de comment2");
		comment2.setCreationDate(new Date());
		comment2.setUser(user2);
		entityInMemory.add(comment2);
	}
}
