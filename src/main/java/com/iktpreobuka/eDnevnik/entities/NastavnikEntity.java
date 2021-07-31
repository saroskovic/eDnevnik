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
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
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
@Table(name = "nastavnici")
public class NastavnikEntity {

	//@JsonView(Views.Administrator.class)
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	//@JsonView(Views.Nastavnik.class)
	//@NotBlank(message = "Ime ne sme biti prazno polje")
	//@Size(min = 2, max = 15, message = "Ime mora imati izmedju {min} i {max} karaktera")
	private String ime;
	//@JsonView(Views.Nastavnik.class)
	//@NotBlank(message = "Prezime ne sme biti prazno polje")
	//@Size(min = 2, max = 15, message = "Prezime mora imati izmedju {min} i {max} karaktera")
	private String prezime;
	
	//@JsonView(Views.Nastavnik.class)
	@JsonIgnore
	@OneToMany(mappedBy = "nastavnik", fetch = FetchType.LAZY, orphanRemoval = true, cascade = {CascadeType.REMOVE})
	private List<PredajeEntity> predaje = new ArrayList<PredajeEntity>();
	@JsonIgnore
	@OneToOne(cascade = CascadeType.REMOVE, orphanRemoval = true, fetch = FetchType.LAZY)
	@JoinColumn(name = "korisnik")
	private KorisnikEntity korisnik;
	
	public NastavnikEntity() {
		super();
		// TODO Auto-generated constructor stub
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

	public List<PredajeEntity> getPredaje() {
		return predaje;
	}

	public void setPredaje(List<PredajeEntity> predaje) {
		this.predaje = predaje;
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
