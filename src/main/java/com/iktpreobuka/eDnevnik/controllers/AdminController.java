package com.iktpreobuka.eDnevnik.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.iktpreobuka.eDnevnik.entities.AdminEntity;
import com.iktpreobuka.eDnevnik.entities.EKorisnikRole;
import com.iktpreobuka.eDnevnik.entities.KorisnikEntity;
import com.iktpreobuka.eDnevnik.entities.NastavnikEntity;
import com.iktpreobuka.eDnevnik.repositories.AdminRepository;
import com.iktpreobuka.eDnevnik.repositories.KorisnikRepository;

@RestController
@RequestMapping(path = "/eDnevnik/admin")
public class AdminController {

	@Autowired
	AdminRepository adminRepo;
	@Autowired
	KorisnikRepository korisnikRepo;
	
	@RequestMapping(value = "", method = RequestMethod.POST)
	public AdminEntity dodajAdmina(@RequestParam String ime, @RequestParam String prezime, @RequestParam String username,
							@RequestParam String password, @RequestParam String passwordConfirme) {
		KorisnikEntity korisnik = new KorisnikEntity();
		if (password.equals(passwordConfirme)) {
			korisnik.setUsername(username);
			korisnik.setPassword(password);
			korisnik.setPasswordConfirme(passwordConfirme);
			korisnik.setUloga(EKorisnikRole.ROLE_ADMIN);
			korisnikRepo.save(korisnik);
			AdminEntity admin = new AdminEntity();
			admin.setIme(ime);
			admin.setPrezime(prezime);
			admin.setKorisnik(korisnik);
			return adminRepo.save(admin);
		}
		return null;
	}

	@RequestMapping(value = "", method = RequestMethod.GET)
	public List<AdminEntity> sviAdmini() {
		return (List<AdminEntity>) adminRepo.findAll();
	}

	@RequestMapping(value = "/{id_admina}", method = RequestMethod.GET)
	public AdminEntity pronadjiAdmin(@PathVariable Integer id_admina) {
		if (adminRepo.existsById(id_admina)) {
			return adminRepo.findById(id_admina).get();
		}
		return null;
	}

	@RequestMapping(value = "/{id_admina}", method = RequestMethod.PUT)
	public AdminEntity izmeniAdmina(@PathVariable Integer id_admina, @RequestParam String ime,
			@RequestParam String prezime) {

		if (adminRepo.existsById(id_admina)) {
			AdminEntity admin = adminRepo.findById(id_admina).get();
			if (!ime.isEmpty())
				admin.setIme(ime);
			if (!prezime.isEmpty())
				admin.setPrezime(prezime);
			return adminRepo.save(admin);
		}
		return null;
	}

	@RequestMapping(value = "/{id_admina}", method = RequestMethod.DELETE)
	public AdminEntity izbrisiAdmina(@PathVariable Integer id_admina) {
		if (adminRepo.existsById(id_admina)) {
			AdminEntity admin = adminRepo.findById(id_admina).get();
			KorisnikEntity korisnik = admin.getKorisnik();
			korisnikRepo.delete(korisnik);
			return admin;
		}
		return null;
	}
	
}
