package eu.fr.indyli.formation.business.dto;
// Generated 25 juin 2017 02:21:10 by Hibernate Tools 5.2.0.CR1

import java.util.Date;

public class EcolisMessageDto extends AbstractEcolisEntity implements IEntity {

/**
   * 
   */
	private static final long serialVersionUID = 5315428355602942960L;
  	private Integer messageId;
  	private EcolisAdvertisingDto announcement;
  	private EcolisUserDto user;
  	private String corps;
  	private Date creationDate;

  	public EcolisMessageDto() {}
  
  	public EcolisMessageDto(Integer messageId, String corps, Date creationDate) {
		super();
		this.messageId = messageId;
		this.corps = corps;
		this.creationDate = creationDate;
	}

  	public EcolisMessageDto(Integer messageId, EcolisUserDto user, String corps, Date creationDate) {
		super();
		this.messageId = messageId;
		this.user = user;
		this.corps = corps;
		this.creationDate = creationDate;
	}

	public EcolisMessageDto(EcolisAdvertisingDto announcement, Date creationDate) {
	  this.announcement = announcement;
    	this.creationDate = creationDate;
  	}
  
  	@Override
  	public Integer getId() {
  		return this.messageId;
  	}

  	@Override
  	public void setId(Integer id) {
  		this.messageId = id;
  	}

    public EcolisAdvertisingDto getAnnouncement() {
    	return announcement;
	}
	
	public void setAnnouncement(EcolisAdvertisingDto announcement) {
		this.announcement = announcement;
	}
	
	public EcolisUserDto getUser() {
		return user;
	}
	
	public void setUser(EcolisUserDto user) {
		this.user = user;
	}
	
	public String getCorps() {
		return corps;
	}
	
	public void setCorps(String corps) {
		this.corps = corps;
	}
	
	public Date getCreationDate() {
		return creationDate;
	}
	
	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}

	public String toString() {
		return "EcolisMessageDto{" +
                "id=" + messageId +
                ", user=" + user +
                ", corps='" + corps + '\'' +
                ", creationDate=" + creationDate +
                '}';
	}

}
