package com.iktpreobuka.eDnevnik.entities;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonView;


@Entity
@Table(name = "korisnici")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class KorisnikEntity {

	//@JsonView(Views.Administrator.class)
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	
	//@JsonView(Views.Administrator.class)
	@Column(unique = true)
	//@NotBlank(message = "username ne sme biti prazno polje")
	//@Size(min = 2, max = 15, message = "username mora imati izmedju {min} i {max} karaktera")
	private String username;
	
	//@JsonView(Views.Administrator.class)
	//@NotBlank(message = "password ne sme biti prazno polje")
	//@Size(min = 2, max = 10, message = "password mora imati izmedju {min} i {max} karaktera")
	private String password;
	//@JsonView(Views.Administrator.class)
	//@NotBlank(message = "passwordConfirme ne sme biti prazno polje")
	//@Size(min = 2, max = 10, message = "passwordConfirme mora imati izmedju {min} i {max} karaktera")
	private String passwordConfirme;
	
	//@JsonView(Views.Administrator.class)
	@Enumerated(EnumType.STRING)
	private EKorisnikRole uloga;
	
	@JsonIgnore
	@OneToOne(mappedBy ="korisnik",  cascade = CascadeType.REMOVE, orphanRemoval = true, fetch =	FetchType.LAZY)
	private NastavnikEntity nastavnik;
	@JsonIgnore
	@OneToOne(mappedBy ="korisnik",  cascade = CascadeType.REMOVE, orphanRemoval = true, fetch =	FetchType.LAZY)
	private RoditeljEntity roditelj;
	@JsonIgnore
	@OneToOne(mappedBy ="korisnik",  cascade = CascadeType.REMOVE, orphanRemoval = true, fetch =	FetchType.LAZY)
	private UcenikEntity ucenik;
	@JsonIgnore
	@OneToOne(mappedBy ="korisnik",  cascade = CascadeType.REMOVE, orphanRemoval = true, fetch =	FetchType.LAZY)
	private AdminEntity admin;
	
	public KorisnikEntity() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Integer getId() {
		return id;
		
	}
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public EKorisnikRole getUloga() {
		return uloga;
	}
	

	public void setUloga(EKorisnikRole uloga) {
		this.uloga = uloga;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getPasswordConfirme() {
		return passwordConfirme;
	}

	public void setPasswordConfirme(String passwordConfirme) {
		this.passwordConfirme = passwordConfirme;
	}

	public NastavnikEntity getNastavnik() {
		return nastavnik;
	}

	public void setNastavnik(NastavnikEntity nastavnik) {
		this.nastavnik = nastavnik;
	}

	public RoditeljEntity getRoditelj() {
		return roditelj;
	}

	public void setRoditelj(RoditeljEntity roditelj) {
		this.roditelj = roditelj;
	}

	public UcenikEntity getUcenik() {
		return ucenik;
	}

	public void setUcenik(UcenikEntity ucenik) {
		this.ucenik = ucenik;
	}

	public AdminEntity getAdmin() {
		return admin;
	}

	public void setAdmin(AdminEntity admin) {
		this.admin = admin;
	}
	
	
	
}
