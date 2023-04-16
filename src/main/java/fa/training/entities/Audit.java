package fa.training.entities;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.Pattern;

@Entity
public class Audit {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="audit_id")
	private int id;
	
	@Column(columnDefinition = "DATE",name="audit_date")
	private LocalDate date;
	
	@Pattern(regexp = "^[a-zA-Z0-9_]+$",message= "{audit.pic.invalid}")
	@Column(name="pic")
	private String pic;
	
	@Column(name="audit_note")
	private String note;
	
	@ManyToOne
	@JoinColumn(name="clazz_id")
	private Clazz clazzId;
	
	
	
	public Audit() {
		
	}
	public Audit(String pic) {
		this.pic = pic;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public LocalDate getDate() {
		return date;
	}
	public void setDate(LocalDate date) {
		this.date = date;
	}
	public String getPic() {
		return pic;
	}
	public void setPic(String pic) {
		this.pic = pic;
	}
	public String getNote() {
		return note;
	}
	public void setNote(String note) {
		this.note = note;
	}
	public Clazz getClazzId() {
		return clazzId;
	}
	public void setClazzId(Clazz clazzId) {
		this.clazzId = clazzId;
	}

	@Override
	public String toString() {
		return "Audit [id=" + id + ", date=" + date + ", pic=" + pic + "]";
	}
	
	
	
}
