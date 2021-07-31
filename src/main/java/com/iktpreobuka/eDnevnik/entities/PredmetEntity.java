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
import javax.persistence.Table;


import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonView;


@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Table(name = "predmeti")
public class PredmetEntity {

	//@JsonView(Views.Nastavnik.class)
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	
	//@JsonView(Views.Nastavnik.class)
	//@NotBlank(message = "Prezime ne sme biti prazno polje")
	//@Size(min = 2, max = 30, message = "Prezime mora imati izmedju {min} i {max} karaktera")
	private String nazivPredmeta;
	
	//@JsonView(Views.Nastavnik.class)
	//@NotNull(message = "fond ne sme biti null ili blank")
	//@Min(value = 1, message = "fond mora biti u rasponu od 1 do 5")
	//@Max(value = 5, message = "fond mora biti u rasponu od 1 do 5")
	@Column(nullable = true)
	private Integer fond;
	
	//@JsonView(Views.Nastavnik.class)
	@JsonIgnore
	@OneToMany(mappedBy = "predmet", fetch = FetchType.LAZY, orphanRemoval = true, cascade = {CascadeType.REMOVE})
	private List<PredajeEntity> predmeti = new ArrayList<PredajeEntity>();
	
	public PredmetEntity() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNazivPredmeta() {
		return nazivPredmeta;
	}

	public void setNazivPredmeta(String nazivPredmeta) {
		this.nazivPredmeta = nazivPredmeta;
	}

	public Integer getFond() {
		return fond;
	}

	public void setFond(Integer fond) {
		this.fond = fond;
	}

	public List<PredajeEntity> getPredmeti() {
		return predmeti;
	}

	public void setPredmeti(List<PredajeEntity> predmeti) {
		this.predmeti = predmeti;
	}

	
}
