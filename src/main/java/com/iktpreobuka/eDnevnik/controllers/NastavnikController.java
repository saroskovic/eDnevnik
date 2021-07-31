package com.iktpreobuka.eDnevnik.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.iktpreobuka.eDnevnik.entities.EKorisnikRole;
import com.iktpreobuka.eDnevnik.entities.KorisnikEntity;
import com.iktpreobuka.eDnevnik.entities.NastavnikEntity;
import com.iktpreobuka.eDnevnik.repositories.KorisnikRepository;
import com.iktpreobuka.eDnevnik.repositories.NastavnikRepository;

@RestController
@RequestMapping(path = "/eDnevnik/nastavnik")
public class NastavnikController {

	@Autowired
	NastavnikRepository nastavnikRepo;
	@Autowired
	KorisnikRepository korisnikRepo;

	@RequestMapping(value = "", method = RequestMethod.POST)
	public NastavnikEntity dodajNastavnika(@RequestParam String ime, @RequestParam String prezime,
			@RequestParam String username, @RequestParam String password, @RequestParam String passwordConfirme) {
		KorisnikEntity korisnik = new KorisnikEntity();
		korisnik.setUsername(username);
		korisnik.setPassword(password);
		korisnik.setPasswordConfirme(passwordConfirme);
		korisnik.setUloga(EKorisnikRole.ROLE_NASTAVNIK);
		korisnikRepo.save(korisnik);
		NastavnikEntity nastavnik = new NastavnikEntity();
		nastavnik.setIme(ime);
		nastavnik.setPrezime(prezime);
		nastavnik.setKorisnik(korisnik);
		return nastavnikRepo.save(nastavnik);
	}

	@RequestMapping(value = "", method = RequestMethod.GET)
	public List<NastavnikEntity> sviNastavnici() {
		return (List<NastavnikEntity>) nastavnikRepo.findAll();
	}

	@RequestMapping(value = "/{id_nastavnika}", method = RequestMethod.GET)
	public NastavnikEntity pronadjiNastavnika(@PathVariable Integer id_nastavnika) {
		if (nastavnikRepo.existsById(id_nastavnika)) {
			return nastavnikRepo.findById(id_nastavnika).get();
		}
		return null;
	}

	@RequestMapping(value = "/{id_nastavnika}", method = RequestMethod.PUT)
	public NastavnikEntity izmeniNastavnika(@PathVariable Integer id_nastavnika, @RequestParam String ime,
			@RequestParam String prezime) {

		if (nastavnikRepo.existsById(id_nastavnika)) {
			NastavnikEntity nastavnik = nastavnikRepo.findById(id_nastavnika).get();
			if (!ime.isEmpty())
				nastavnik.setIme(ime);
			if (!prezime.isEmpty())
				nastavnik.setPrezime(prezime);
			return nastavnikRepo.save(nastavnik);
		}
		return null;
	}

	@RequestMapping(value = "/{id_nastavnika}", method = RequestMethod.DELETE)
	public NastavnikEntity izbrisiNastavnika(@PathVariable Integer id_nastavnika) {
		if (nastavnikRepo.existsById(id_nastavnika)) {
			NastavnikEntity nastavnik = nastavnikRepo.findById(id_nastavnika).get();
			KorisnikEntity korisnik = nastavnik.getKorisnik();
			korisnikRepo.delete(korisnik);
			return nastavnik;
		}
		return null;
	}
}
