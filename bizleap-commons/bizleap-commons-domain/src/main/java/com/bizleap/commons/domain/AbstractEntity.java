package com.bizleap.commons.domain;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

import org.apache.commons.lang3.builder.ToStringBuilder;

@MappedSuperclass
public abstract class AbstractEntity {
	@Id
	@GeneratedValue
	private long id;	
	private String name;
	private String boId;
	private String email;
	private String phone;
	
	public AbstractEntity(){
		
	}
	
	public AbstractEntity(String boId) {
		this.boId=boId;
	}
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getBoId() {
		return boId;
	}

	public void setBoId(String boId) {
		this.boId = boId;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}
	
	public boolean sameBoId(AbstractEntity entity) {
		if(entity==null)
			return false;
		if(getBoId()==null)
			return false;
		return this.getBoId().equals(entity.getBoId());
	}
	
	public boolean isSameBoId(AbstractEntity entity) {
		  if (entity == null)
		   return false;
		  if (!entity.getClass().equals(getClass()))
		   return false;
		  if(entity.getBoId()==null) return false;
		  return entity.getBoId().equals(getBoId());
	}
	
	public boolean isBoIdRequired() {
		return SystemConstant.BOID_REQUIRED.equals(getBoId());
	}
	
	public String toString() {
		return new ToStringBuilder(this)
				.append("boId",boId)
				.append("name",name)
				.append("email",email)
				.append("phone",phone)
				.toString();
	}

}
