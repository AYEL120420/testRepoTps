package eu.fr.indyli.formation.business.dto;
// Generated 25 juin 2017 02:21:10 by Hibernate Tools 5.2.0.CR1

import java.util.Date;

public class EcolisAlertDto extends AbstractEcolisEntity implements IEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4567351989909736794L;
	private Integer alertId;
	private EcolisUserDto user;
	private String startCity;
	private String endCity;
	private Date depositDate;

	public EcolisAlertDto() {
		this.depositDate = new Date();
	}
	
	@Override
	public Integer getId() {
		return this.alertId;
	}

	@Override
	public void setId(Integer id) {
		this.alertId = id;
	}

	public EcolisUserDto getUser() {
		return user;
	}

	public void setUser(EcolisUserDto user) {
		this.user = user;
	}

	public String getStartCity() {
		return startCity;
	}

	public void setStartCity(String startCity) {
		this.startCity = startCity;
	}

	public String getEndCity() {
		return endCity;
	}

	public void setEndCity(String endCity) {
		this.endCity = endCity;
	}

	public Date getDepositDate() {
		return depositDate;
	}

	public void setDepositDate(Date depositDate) {
		this.depositDate = depositDate;
	}

}
