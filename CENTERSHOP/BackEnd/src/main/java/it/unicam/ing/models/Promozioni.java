package it.unicam.ing.models;



import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "PROMOZIONI")
public class Promozioni {

	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	
	
	@Column(nullable = false)
	private int percsconto;
	
	
	public int getPercsconto() {
		return percsconto;
	}

	public void setPercsconto(int percsconto) {
		this.percsconto = percsconto;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	
	
}
