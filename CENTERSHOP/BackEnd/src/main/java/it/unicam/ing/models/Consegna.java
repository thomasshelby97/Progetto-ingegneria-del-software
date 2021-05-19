package it.unicam.ing.models;



import java.time.LocalTime;
import java.util.List;
import java.util.Locale;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;




@Entity
@Table(name = "CONSEGNA")
public class Consegna {


	
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	
	@Column(nullable = false)
	private double importo;
	@Column(nullable = false)
	private LocalTime ora;
	@Column(nullable = false)
	private String puntoritiro;
	@Column(nullable = false, unique = true)
	private String codiceritiro;
	@Column(nullable = false)
	private String nominativo;
	@ManyToMany(cascade=CascadeType.ALL)
	@Column(nullable = false)
	private List<Prodotto> prodotti;
	
	
	public Consegna() {
		
		
	}
	public Consegna(double importo, String puntoritiro, LocalTime ora, String nominativo, List<Prodotto> prodotti) {
		this.importo = importo;
		this.puntoritiro = puntoritiro;
		this.ora = ora;
		this.codiceritiro = genereteRandomString(50);
		this.nominativo =nominativo;
		this.prodotti = prodotti;
	
	}
	
	private String genereteRandomString(int m) {
		if(m<8) m=8;
		final String upper = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
	    final String lower = upper.toLowerCase(Locale.ROOT);
	    final String digits = "0123456789";
	    final String alphanum = upper + lower + digits;
	    
	    String random = "";
	    for(int i =0 ; i<m ;i++) {
	    	double num = Math.random()*(alphanum.length());
	    	int num1 = (int)num;
	    	random += alphanum.charAt(num1);
	    }

		return random;
	}
	public double getImporto() {
		return importo;
	}
	public void setImporto(double importo) {
		this.importo = importo;
	}
	public void setOra(LocalTime ora) {
		this.ora = ora;
	}
	public LocalTime getOra() {
		return ora;
	}
	
	public String getPuntoritiro() {
		return puntoritiro;
	}
	public void setPuntoritiro(String puntoritiro) {
		this.puntoritiro = puntoritiro;
	}
	public String getCodiceritiro() {
		return codiceritiro;
	}
	
	public void setCodiceritiro(String codiceritiro) {
		this.codiceritiro = codiceritiro;
	}
	public String getCliente() {
		return nominativo;
	}
	public void setCliente(String cliente) {
		this.nominativo = cliente;
	}
	public List<Prodotto> getProdotti() {
		return prodotti;
	}
	public void setProdotti(List<Prodotto> prodotti) {
		this.prodotti = prodotti;
	}
	
	
	
}
