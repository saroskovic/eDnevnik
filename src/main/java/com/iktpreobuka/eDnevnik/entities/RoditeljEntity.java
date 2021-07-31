package com.iktpreobuka.eDnevnik.entities;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;


import org.hibernate.annotations.Type;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonView;


@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Table(name = "roditelji")
public class RoditeljEntity {

	//@JsonView(Views.Administrator.class)
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	//@JsonView(Views.Roditelj.class)
	//@NotBlank(message = "ime ne sme biti prazno polje")
	//@Size(min = 2, max = 15, message = "ime mora imati izmedju {min} i {max} karaktera")
	private String ime;
	
	//@JsonView(Views.Roditelj.class)
	//@NotBlank(message = "Prezime ne sme biti prazno polje")
	//@Size(min = 2, max = 15, message = "Prezime mora imati izmedju {min} i {max} karaktera")
	private String prezime;
	
	//@JsonView(Views.Roditelj.class)
	//@NotBlank(message = "email mora biti unet")
	//@Pattern(regexp = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"+"[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})$",
	//		message = "email nije validan")
	private String email;
	
	//@JsonView(Views.Roditelj.class)
	@JsonIgnore
	@OneToMany(mappedBy = "roditelj", fetch = FetchType.LAZY, cascade = {CascadeType.REMOVE})
	private List<UcenikEntity> deca = new ArrayList<UcenikEntity>();
	@JsonIgnore
	@OneToOne(cascade = CascadeType.REMOVE, orphanRemoval = true, fetch = FetchType.LAZY)
	@JoinColumn(name = "korisnik")
	private KorisnikEntity korisnik;

	public RoditeljEntity() {
		super();
		// TODO Auto-generated constructor stub
	}

	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	

	public List<UcenikEntity> getDeca() {
		return deca;
	}

	public void setDeca(List<UcenikEntity> deca) {
		this.deca = deca;
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
