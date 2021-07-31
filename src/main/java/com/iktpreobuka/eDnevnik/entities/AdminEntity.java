package com.iktpreobuka.eDnevnik.entities;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonView;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Table(name = "admini")
public class AdminEntity{


	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;

	//@NotBlank(message = "Ime ne sme biti prazno polje")
	//@Size(min = 2, max = 15, message = "Ime mora imati izmedju {min} i {max} karaktera")
	private String ime;
	

	//@NotBlank(message = "Prezime ne sme biti prazno polje")
	//@Size(min = 2, max = 15, message = "Prezime mora imati izmedju {min} i {max} karaktera")
	private String prezime;

	@OneToOne(cascade = CascadeType.REMOVE, orphanRemoval = true, fetch = FetchType.LAZY)
	@JoinColumn(name = "korisnik")
	private KorisnikEntity korisnik;
	
	public AdminEntity() {
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
