package eu.fr.indyli.formation.business.ecolis.service.impl;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;


import eu.fr.indyli.formation.business.dao.IEcolisUserDAO;
import eu.fr.indyli.formation.business.dao.IEntityDAO;
import eu.fr.indyli.formation.business.dao.impl.EcolisUserDAOImpl;
import eu.fr.indyli.formation.business.dto.EcolisUserDto;
import eu.fr.indyli.formation.business.ecolis.exception.EcolisBusinessException;
import eu.fr.indyli.formation.business.ecolis.service.IEcolisUserService;
import eu.fr.indyli.formation.business.utils.EcolisConstantes.EcolisConstantesService;

@Service(EcolisConstantesService.USER_SERVICE_KEY)
public class EcolisUserServiceImpl extends AbstractEntityServiceImpl<EcolisUserDto> implements IEcolisUserService, UserDetailsService {

  private IEcolisUserDAO userDAO = new EcolisUserDAOImpl();

  //@Autowired
  private BCryptPasswordEncoder bcryptEncoder;

  public EcolisUserServiceImpl() {
	  this.bcryptEncoder = new BCryptPasswordEncoder();
  }

  @Override
  public List<EcolisUserDto> findAuthorsCommentByDateAndPostedAnnonce(Date paramDatePivot,
      String paramVilleArrivee) throws EcolisBusinessException {
    if (StringUtils.isBlank(paramVilleArrivee) || paramDatePivot == null) {
      throw new EcolisBusinessException("VOUS DEVEZ RENSEINGER LES 2 CHAMPS");
    }
    return userDAO.findAuthorsCommentByDateAndPostedAnnonce(paramDatePivot, paramVilleArrivee);
  }

  @Override
  public List<EcolisUserDto> findAll() {
    // TODO Auto-generated method stub
    List<EcolisUserDto> userList = this.userDAO.findAll();
    return userList;
  }

  @Override
  public EcolisUserDto findById(Integer id) throws EcolisBusinessException {
    EcolisUserDto utilisateur =
        this.userDAO.findById(id);
           
    return utilisateur;
  }

  @Override
  public EcolisUserDto create(EcolisUserDto user) throws EcolisBusinessException {
    if (user.getId() != null) {
      EcolisUserDto userInBase = this.userDAO.findById(user.getId());
      userInBase.setName(user.getName());
      userInBase.setLogin(user.getLogin());
      userInBase.setEmail(user.getEmail());
      userInBase.setPhone(user.getPhone());
      userInBase.setYearOfBirth(user.getYearOfBirth());
      userInBase.setRegistrationDate(new Date());
      user = userInBase;
    }
    // On verifie si l'email et le login n'existent pas déjà en base
    List<EcolisUserDto> listeUserExistantEnBaseAvecLoginOuEmail =
        this.userDAO.findByLoginOrEmail(user.getLogin(), user.getEmail());
    if (!listeUserExistantEnBaseAvecLoginOuEmail.isEmpty()) {
      throw new EcolisBusinessException("Le login :" + user.getLogin() + " Ou L'email :"
          + user.getEmail() + " semblent déjà pris");
    }
    String encryptPassword = bcryptEncoder.encode(user.getLogin());
    user.setPassword(encryptPassword);
    user = this.userDAO.create(user);
    return user;
  }

  public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
    EcolisUserDto user = userDAO.findByLogin(login);
    if (user == null) {
      throw new UsernameNotFoundException("Invalid Login or Password.");
    }
    return new org.springframework.security.core.userdetails.User(user.getLogin(),
        user.getPassword(), getAuthority());
  }

  private List<SimpleGrantedAuthority> getAuthority() {
    return Arrays.asList(new SimpleGrantedAuthority("ROLE_ADMIN"),
        new SimpleGrantedAuthority("ROLE_ANONYMOUS"));
     //ROLE_ANONYMOUS
  }

  @Override
  public List<EcolisUserDto> findByLoginOrEmail(String login, String email)
      throws EcolisBusinessException {
    return this.userDAO.findByLoginOrEmail(login, email);
  }

	@Override
	public IEntityDAO<EcolisUserDto> getDAO() {
		return this.userDAO;
	}
	
	@Override
	public EcolisUserDto update(EcolisUserDto user) throws EcolisBusinessException {
		EcolisUserDto existingUser  = this.userDAO.findById(user.getId());
		
		if(user.getPassword() != null && !user.getPassword().isEmpty()) {
			existingUser.setName(user.getName());
			existingUser.setLogin(user.getLogin());
			existingUser.setEmail(user.getEmail());
			existingUser.setPhone(user.getPhone());
			existingUser.setYearOfBirth(user.getYearOfBirth());
			existingUser.setRegistrationDate(user.getRegistrationDate());
			existingUser.setPassword(bcryptEncoder.encode(user.getPassword()));
			
			EcolisUserDto updatedUser = this.userDAO.create(existingUser);
		    return updatedUser;
		}else {
			existingUser.setName(user.getName());
			existingUser.setLogin(user.getLogin());
			existingUser.setEmail(user.getEmail());
			existingUser.setPhone(user.getPhone());
			existingUser.setYearOfBirth(user.getYearOfBirth());
			existingUser.setRegistrationDate(user.getRegistrationDate());
			
    		return this.userDAO.create(existingUser); 
		}
	}

	@Override
	public EcolisUserDto findByLogin(String login) {
	
		return this.userDAO.findByLogin(login);
	}


	}

