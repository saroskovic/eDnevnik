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
import javax.persistence.OneToMany;
import javax.persistence.Table;


import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonView;


@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Table(name = "odeljenje")
public class OdeljenjeEntity {

	//@JsonView(Views.Nastavnik.class)
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	//@JsonView(Views.Nastavnik.class)
	//@NotNull(message = "razred ne sme biti blank ili null")
	//@Min(value = 1, message = "razred mora biti u rasponu od 1 do 8")
	//@Max(value = 8, message = "razred mora biti u rasponu od 1 do 8")
	private Integer razred;
	
	//@JsonView(Views.Nastavnik.class)
	//@NotBlank(message = "oznakaOdeljenja ne sme biti prazno polje")
	//@Size(min = 1, max = 5, message = "Prezime mora imati izmedju {min} i {max} karaktera")
	private String oznakaOdeljenja;
	
	//@JsonView(Views.Nastavnik.class)
	@JsonIgnore
	@OneToMany(mappedBy = "odeljenje", fetch = FetchType.LAZY, cascade = {CascadeType.REFRESH})
	private List<UcenikEntity> ucenici = new ArrayList<UcenikEntity>();
	
	//@JsonView(Views.Nastavnik.class)
	@JsonIgnore
	@OneToMany(mappedBy = "odeljenje", fetch = FetchType.LAZY, orphanRemoval = true, cascade = {CascadeType.REMOVE})
	private List<PredajeEntity> ocenjuje = new ArrayList<PredajeEntity>();
	
	public OdeljenjeEntity() {
		super();
		// TODO Auto-generated constructor stub
	}


	public Integer getId() {
		return id;
	}


	public void setId(Integer id) {
		this.id = id;
	}


	public Integer getRazred() {
		return razred;
	}

	public void setRazred(Integer razred) {
		this.razred = razred;
	}

	public String getOznakaOdeljenja() {
		return oznakaOdeljenja;
	}

	public void setOznakaOdeljenja(String oznakaOdeljenja) {
		this.oznakaOdeljenja = oznakaOdeljenja;
	}

	
	public List<UcenikEntity> getUcenici() {
		return ucenici;
	}


	public void setUcenici(List<UcenikEntity> ucenici) {
		this.ucenici = ucenici;
	}


	public List<PredajeEntity> getOcenjuje() {
		return ocenjuje;
	}


	public void setOcenjuje(List<PredajeEntity> ocenjuje) {
		this.ocenjuje = ocenjuje;
	}

	
}
