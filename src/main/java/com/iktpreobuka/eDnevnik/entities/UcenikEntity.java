package com.iktpreobuka.eDnevnik.entities;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonView;


@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Table(name = "ucenici")
public class UcenikEntity {

	//@JsonView(Views.Administrator.class)
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	//@JsonView(Views.Ucenik.class)
	//@NotBlank(message = "ime ne sme biti prazno polje")
	//@Size(min = 2, max = 15, message = "ime mora imati izmedju {min} i {max} karaktera")
	private String ime;
	
	//@JsonView(Views.Ucenik.class)
	//@NotBlank(message = "prezime ne sme biti prazno polje")
	//@Size(min = 2, max = 15, message = "prezime mora imati izmedju {min} i {max} karaktera")
	private String prezime;
	
	//@JsonView(Views.Ucenik.class)
	@JsonIgnore
	@ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
	@JoinColumn(name = "roditelj")
	private RoditeljEntity roditelj;
	
	//@JsonView(Views.Ucenik.class)
	@JsonIgnore
	@ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
	@JoinColumn(name = "odeljenje")
	private OdeljenjeEntity odeljenje;
	
	//@JsonView(Views.Ucenik.class)
	@JsonIgnore
	@OneToMany(mappedBy = "ucenik", fetch = FetchType.LAZY, cascade = {CascadeType.REMOVE})
	private List<OcenaEntity> ocene = new ArrayList<OcenaEntity>();
	@JsonIgnore
	@OneToOne(cascade = CascadeType.REMOVE, orphanRemoval = true, fetch = FetchType.LAZY)
	@JoinColumn(name = "korisnik")
	private KorisnikEntity korisnik;
	
	public UcenikEntity() {
		super();
		// TODO Auto-generated constructor stub
	}

	public OdeljenjeEntity getOdeljenje() {
		return odeljenje;
	}
	public void setOdeljenje(OdeljenjeEntity odeljenje) {
		this.odeljenje = odeljenje;
	}
	public RoditeljEntity getRoditelj() {
		return roditelj;
	}
	public void setRoditelj(RoditeljEntity roditelj) {
		this.roditelj = roditelj;
	}

	public String getIme() {
		return ime;
	}

	public void setIme(String ime) {
		this.ime = ime;
	}

	public String getPrezime() {
		return prezime;
	}

	public void setPrezime(String prezime) {
		this.prezime = prezime;
	}

	public List<OcenaEntity> getOcene() {
		return ocene;
	}

	public void setOcene(List<OcenaEntity> ocene) {
		this.ocene = ocene;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public KorisnikEntity getKorisnik() {
		return korisnik;
	}

	public void setKorisnik(KorisnikEntity korisnik) {
		this.korisnik = korisnik;
	}
	
	

}
