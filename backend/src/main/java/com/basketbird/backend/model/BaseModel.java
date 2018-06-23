package com.basketbird.backend.model;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import lombok.*;

import javax.print.attribute.standard.DateTimeAtCompleted;

import org.hibernate.annotations.CreationTimestamp;
import org.springframework.data.annotation.CreatedDate;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;

import java.util.Calendar;
import java.util.Date;
import java.util.UUID;

/**
 * Created by Mohiuddin on 2/27/2017.
 */

@Inheritance
@Getter
@Setter
public abstract class BaseModel implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static final String dateFormat = "dd-MMM-yyyy";
	@Transient
	private Date date = new Date();
    
	public BaseModel(){
		java.sql.Date sqlDate = new java.sql.Date(Calendar.getInstance().getTime().getTime());
		whenUpdated = whenCreated = sqlDate;
		
	}

	public long getVersion() {
		return version;
	}

	public void setVersion(long version) {
		this.version = version;
	}

	public String getMessageFromServer() {
		return messageFromServer;
	}

	public void setMessageFromServer(String messageFromServer) {
		this.messageFromServer = messageFromServer;
	}

	public void setWhenCreated(Date whenCreated) {
		this.whenCreated = whenCreated;
	}

	public void setWhenUpdated(Date whenUpdated) {
		this.whenUpdated = whenUpdated;
	}

	@Version
    public long version;

	
	@CreatedDate    
    @Column(name = "creationTime")    
    public Date whenCreated;    
    
	@CreatedDate 
    @Column(name = "updateTime")
    public Date whenUpdated;
    
	
	@Transient
	public String messageFromServer;
	
}
