package com.app.jdbc.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.boot.autoconfigure.domain.EntityScan;

@Entity
@Table(name = "STUDENT_AUDIT")
public class StudentAudit {

	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="STUDENT_AUDIT_PK")
	Long pk;
	
	@Column(name="SNO")
	Long sno;
	
	@Column(name="SNAME")
	String sname;
	
	@Column(name="ACCESSED_BY")
	String accessed_by;
	
	@Column(name="date")
	Date date;
	
	public Long getSno() {
		return sno;
	}
	public void setSno(Long sno) {
		this.sno = sno;
	}
	public String getSname() {
		return sname;
	}
	public void setSname(String sname) {
		this.sname = sname;
	}
	public Long getPk() {
		return pk;
	}
	public void setPk(Long pk) {
		this.pk = pk;
	}
	public String getAccessed_by() {
		return accessed_by;
	}
	public void setAccessed_by(String accessed_by) {
		this.accessed_by = accessed_by;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	
}
