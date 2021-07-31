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
import com.iktpreobuka.eDnevnik.repositories.KorisnikRepository;

@RestController
@RequestMapping(path = "/eDnevnik/korisnik")
public class KorisnikController {

	@Autowired
	KorisnikRepository korisnikRepo;

	@RequestMapping(value = "", method = RequestMethod.POST)
	public KorisnikEntity dodajKorisnika(@RequestParam String username, @RequestParam String password,
			@RequestParam String passwordConfirme, EKorisnikRole uloga) {
		KorisnikEntity korisnik = new KorisnikEntity();
		if (password.equals(passwordConfirme)) {
			korisnik.setUsername(username);
			korisnik.setPassword(password);
			korisnik.setPasswordConfirme(passwordConfirme);
			korisnik.setUloga(uloga);
			return korisnikRepo.save(korisnik);
		}
		return null;
	}

	@RequestMapping(value = "", method = RequestMethod.GET)
	public List<KorisnikEntity> sviKorisnici() {
		return (List<KorisnikEntity>) korisnikRepo.findAll();
	}

	@RequestMapping(value = "/{id_korisnika}", method = RequestMethod.GET)
	public KorisnikEntity pronadjiKorisnika(@PathVariable Integer id_korisnika) {
		return korisnikRepo.findById(id_korisnika).get();
	}

	@RequestMapping(value = "/{id_korisnika}", method = RequestMethod.DELETE)
	public KorisnikEntity obrissiKorisnika(@PathVariable Integer id_korisnika) {
		KorisnikEntity korisnik = korisnikRepo.findById(id_korisnika).get();
		korisnikRepo.delete(korisnik);
		return korisnik;
	}

	@RequestMapping(value = "/{id_korisnika}", method = RequestMethod.PUT)
	public KorisnikEntity izmeniKorisnika(@RequestBody KorisnikEntity izmKorisnik, @PathVariable Integer id_korisnika) {
		KorisnikEntity korisnik = korisnikRepo.findById(id_korisnika).get();
		if (izmKorisnik.getUsername() != null)
			korisnik.setUsername(izmKorisnik.getUsername());
		if (izmKorisnik.getPassword() != null)
			korisnik.setPassword(izmKorisnik.getPassword());
		if (izmKorisnik.getPasswordConfirme() != null)
			korisnik.setPasswordConfirme(izmKorisnik.getPasswordConfirme());
		if (izmKorisnik.getUloga() != null)
			korisnik.setUloga(izmKorisnik.getUloga());
		return korisnikRepo.save(korisnik);
	}

}
