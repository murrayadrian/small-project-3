package fa.training.entities;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotEmpty;

@Entity
public class Clazz {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="clazz_id")
	private int id;
	
	@NotEmpty(message="{clazz.code.invalid}")
	@Column(name="clazz_code", unique=true)
	private String code;
	
	@NotEmpty(message="{clazz.name.invalid}")
	@Column(name="clazz_name")
	private String name;
	
	@Column(name="accepted_trainee_number")
	private int acceptedNumber;
	
	@Column(name="actual_trainee_number")
	private int actualNumber;
	
	@Column(name="skill")
	private String skill;
	
	@Column(columnDefinition = "DATE",name="start_date")
	private LocalDate startDate;
	
	@Column(columnDefinition = "DATE",name="end_date")
	private LocalDate endDate;
	
	@Column(name="clazz_admin")
	private String admin;
	
	@Column(name="status")
	private int status;
	
	@OneToMany(mappedBy="clazzId",cascade = CascadeType.PERSIST)
	private List<Audit> auditList = new ArrayList<>();
	
	
	public Clazz() {
		
	}
	
	public Clazz(String code, String name,String admin,LocalDate startDate,String skill,int status) {
		this.code = code;
		this.name = name;
		this.admin = admin;
		this.startDate = startDate;
		this.skill = skill;
		this.status = status;
	}


	public List<Audit> getAuditList() {
		return auditList;
	}
	public void setAuditList(List<Audit> auditList) {
		this.auditList = auditList;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getAcceptedNumber() {
		return acceptedNumber;
	}
	public void setAcceptedNumber(int acceptedNumber) {
		this.acceptedNumber = acceptedNumber;
	}
	public int getActualNumber() {
		return actualNumber;
	}
	public void setActualNumber(int actualNumber) {
		this.actualNumber = actualNumber;
	}
	public String getSkill() {
		return skill;
	}
	public void setSkill(String skill) {
		this.skill = skill;
	}
	public LocalDate getStartDate() {
		return startDate;
	}
	public void setStartDate(LocalDate startDate) {
		this.startDate = startDate;
	}
	public LocalDate getEndDate() {
		return endDate;
	}
	public void setEndDate(LocalDate endDate) {
		this.endDate = endDate;
	}
	public String getAdmin() {
		return admin;
	}
	public void setAdmin(String admin) {
		this.admin = admin;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}


	@Override
	public String toString() {
		return "Clazz [code=" + code + ", name=" + name + ", admin=" + admin + "]";
	}
	
}
