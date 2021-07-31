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
import com.iktpreobuka.eDnevnik.entities.OdeljenjeEntity;
import com.iktpreobuka.eDnevnik.entities.RoditeljEntity;
import com.iktpreobuka.eDnevnik.entities.UcenikEntity;
import com.iktpreobuka.eDnevnik.repositories.KorisnikRepository;
import com.iktpreobuka.eDnevnik.repositories.OdeljenjeRepository;
import com.iktpreobuka.eDnevnik.repositories.RoditeljRepository;
import com.iktpreobuka.eDnevnik.repositories.UcenikRepository;

@RestController
@RequestMapping(path = "/eDnevnik/ucenik")
public class UcenikController {

	@Autowired
	UcenikRepository ucenikRepo;
	@Autowired
	KorisnikRepository korisnikRepo;
	@Autowired
	OdeljenjeRepository odeljenjeRepo;
	@Autowired
	RoditeljRepository roditeljRepo;
	
	@RequestMapping(value = "", method = RequestMethod.POST)
	public UcenikEntity dodajUcenika(@RequestParam String ime, @RequestParam String prezime,
			@RequestParam String username, @RequestParam String password, @RequestParam String passwordConfirme) {
		KorisnikEntity korisnik = new KorisnikEntity();
		korisnik.setUsername(username);
		korisnik.setPassword(password);
		korisnik.setPasswordConfirme(passwordConfirme);
		korisnik.setUloga(EKorisnikRole.ROLE_UCENIK);
		korisnikRepo.save(korisnik);
		UcenikEntity ucenik = new UcenikEntity();
		ucenik.setIme(ime);
		ucenik.setPrezime(prezime);
		ucenik.setKorisnik(korisnik);
		return ucenikRepo.save(ucenik);
	}

	@RequestMapping(value = "", method = RequestMethod.GET)
	public List<UcenikEntity> sviUcenici() {
		return (List<UcenikEntity>) ucenikRepo.findAll();
	}

	@RequestMapping(value = "/{id_ucenika}", method = RequestMethod.GET)
	public UcenikEntity pronadjiUcenika(@PathVariable Integer id_ucenika) {
		if (ucenikRepo.existsById(id_ucenika)) {
			return ucenikRepo.findById(id_ucenika).get();
		}
		return null;
	}

	@RequestMapping(value = "/{id_ucenika}", method = RequestMethod.PUT)
	public UcenikEntity izmeniUceniika(@PathVariable Integer id_ucenika, @RequestParam String ime,
			@RequestParam String prezime) {

		if (ucenikRepo.existsById(id_ucenika)) {
			UcenikEntity ucenik = ucenikRepo.findById(id_ucenika).get();
			if (!ime.isEmpty())
				ucenik.setIme(ime);
			if (!prezime.isEmpty())
				ucenik.setPrezime(prezime);
			return ucenikRepo.save(ucenik);
		}
		return null;
	}

	@RequestMapping(value = "/{id_ucenika}", method = RequestMethod.DELETE)
	public UcenikEntity izbrissiUcenikaa(@PathVariable Integer id_ucenika) {
		if (ucenikRepo.existsById(id_ucenika)) {
			UcenikEntity ucenik = ucenikRepo.findById(id_ucenika).get();
			KorisnikEntity korisnik = ucenik.getKorisnik();
			korisnikRepo.delete(korisnik);
			return ucenik;
		}
		return null;
	}
	
	@RequestMapping(value = "/odeljenje/{id_odeljenja}/ucenik/{id_ucenika}", method = RequestMethod.PUT)
	public UcenikEntity dodajUcenikaUOdeljenje(@PathVariable Integer id_ucenika, @PathVariable Integer id_odeljenja) {
		if(odeljenjeRepo.existsById(id_odeljenja)) {
			OdeljenjeEntity odeljenje = odeljenjeRepo.findById(id_odeljenja).get();
			if(ucenikRepo.existsById(id_ucenika)) {
				UcenikEntity ucenik = ucenikRepo.findById(id_ucenika).get();
				ucenik.setOdeljenje(odeljenje);
				return ucenikRepo.save(ucenik);
			}
		}
		return null;
	}
	@RequestMapping(value = "/ucenik/{id_ucenika}/roditelj/{id_roditelja}", method = RequestMethod.PUT)
	public UcenikEntity dodajRoditeljaUceniku(@PathVariable Integer id_ucenika, @PathVariable Integer  id_roditelja) {
		
		if (roditeljRepo.existsById(id_roditelja)) {
			RoditeljEntity roditelj = roditeljRepo.findById(id_roditelja).get();
			if (ucenikRepo.existsById(id_ucenika)) {
				UcenikEntity ucenik = ucenikRepo.findById(id_ucenika).get();
				ucenik.setRoditelj(roditelj);
				return ucenikRepo.save(ucenik);
			}
		}return null;
	}
	
}
