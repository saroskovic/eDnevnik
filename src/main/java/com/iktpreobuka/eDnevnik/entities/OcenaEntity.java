package com.iktpreobuka.eDnevnik.entities;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonView;


@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Table(name = "ocene")
public class OcenaEntity {

	//@JsonView(Views.Ucenik.class)
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	
	//@JsonView(Views.Ucenik.class)
	//@NotNull(message = "Ocena ne sme biti null ili blank")
	//@Min(value = 1, message = "Ocena mora biti u rasponu od 1 do 5")
	//@Max(value = 5, message = "Ocena mora biti u rasponu od 1 do 5")
	private Integer vrednost;
	
	//@JsonView(Views.Ucenik.class)
	@Column(columnDefinition = "DATE")
	//@NotNull(message = "datumOcene ne sme biti null ili blank")
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
	private LocalDate datumOcene;
	
	//@JsonView(Views.Ucenik.class)
	@JsonIgnore
	@ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
	@JoinColumn(name = "ucenik")
	private UcenikEntity ucenik;
	
	//@JsonView(Views.Ucenik.class)
	@JsonIgnore
	@OneToOne(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
	@JoinColumn(name = "ocenio")
	private PredajeEntity ocenio;
	
	
	public OcenaEntity() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getVrednost() {
		return vrednost;
	}

	public void setVrednost(Integer vrednost) {
		this.vrednost = vrednost;
	}

	public LocalDate getDatumOcene() {
		return datumOcene;
	}

	public void setDatumOcene(LocalDate datumOcene) {
		this.datumOcene = datumOcene;
	}

	public UcenikEntity getUcenik() {
		return ucenik;
	}

	public void setUcenik(UcenikEntity ucenik) {
		this.ucenik = ucenik;
	}
	
	public PredajeEntity getOcenio() {
		return ocenio;
	}

	public void setOcenio(PredajeEntity ocenio) {
		this.ocenio = ocenio;
	}
	
	public NastavnikEntity nastavnik(PredajeEntity ocenio) {
		NastavnikEntity nastavnik = ocenio.getNastavnik();
		return nastavnik;
	}
	
	public PredmetEntity izPredmeta(PredajeEntity ocenio) {
		PredmetEntity izPredmeta = ocenio.getPredmet();
		return izPredmeta;
	}

}
