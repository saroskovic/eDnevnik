package com.iktpreobuka.eDnevnik.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.iktpreobuka.eDnevnik.entities.EKorisnikRole;
import com.iktpreobuka.eDnevnik.entities.KorisnikEntity;
import com.iktpreobuka.eDnevnik.entities.NastavnikEntity;
import com.iktpreobuka.eDnevnik.entities.RoditeljEntity;
import com.iktpreobuka.eDnevnik.repositories.KorisnikRepository;
import com.iktpreobuka.eDnevnik.repositories.RoditeljRepository;

@RestController
@RequestMapping(path = "/eDnevnik/roditelj")
public class RoditeljController {

	@Autowired
	RoditeljRepository roditeljRepo;
	@Autowired
	KorisnikRepository korisnikRepo;

	@RequestMapping(value = "", method = RequestMethod.POST)
	public RoditeljEntity dodajRoditelja(@RequestParam String ime, @RequestParam String prezime,
			@RequestParam String email, @RequestParam String username, @RequestParam String password,
			@RequestParam String passwordConfirme) {
		KorisnikEntity korisnik = new KorisnikEntity();
		korisnik.setUsername(username);
		korisnik.setPassword(password);
		korisnik.setPasswordConfirme(passwordConfirme);
		korisnik.setUloga(EKorisnikRole.ROLE_RODITELJ);
		korisnikRepo.save(korisnik);
		RoditeljEntity roditelj = new RoditeljEntity();
		roditelj.setIme(ime);
		roditelj.setPrezime(prezime);
		roditelj.setEmail(email);
		roditelj.setKorisnik(korisnik);
		return roditeljRepo.save(roditelj);
	}

	@RequestMapping(value = "", method = RequestMethod.GET)
	public List<RoditeljEntity> sviRoditelji() {
		return (List<RoditeljEntity>) roditeljRepo.findAll();
	}

	@RequestMapping(value = "/{id_roditelja}", method = RequestMethod.GET)
	public RoditeljEntity pronadjiRoditelja(@PathVariable Integer id_roditelja) {

		if (roditeljRepo.existsById(id_roditelja)) {
			return roditeljRepo.findById(id_roditelja).get();
		}
		return null;
	}

	@RequestMapping(value = "/{id_roditelja}", method = RequestMethod.PUT)
	public RoditeljEntity izmeniRoditelja(@PathVariable Integer id_roditelja, @RequestParam String ime,
			@RequestParam String prezime, @RequestParam String email) {
		if (roditeljRepo.existsById(id_roditelja)) {
			RoditeljEntity roditelj = roditeljRepo.findById(id_roditelja).get();
			if (!ime.isEmpty())
				roditelj.setIme(ime);
			if (!prezime.isEmpty())
				roditelj.setPrezime(prezime);
			if (!email.isEmpty())
				roditelj.setEmail(email);
			return roditeljRepo.save(roditelj);
		}
		return null;
	}

	@RequestMapping(value = "/{id_roditelja}", method = RequestMethod.DELETE)
	public RoditeljEntity izbrissiRoditelja(@PathVariable Integer id_roditelja) {
		if (roditeljRepo.existsById(id_roditelja)) {
			RoditeljEntity roditelj = roditeljRepo.findById(id_roditelja).get();
			KorisnikEntity korisnik = roditelj.getKorisnik();
			korisnikRepo.delete(korisnik);
			return roditelj;
		}
		return null;
	}
}
