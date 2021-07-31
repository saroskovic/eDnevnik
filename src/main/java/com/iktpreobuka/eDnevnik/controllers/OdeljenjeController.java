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
import com.iktpreobuka.eDnevnik.entities.OdeljenjeEntity;
import com.iktpreobuka.eDnevnik.repositories.OdeljenjeRepository;

@RestController
@RequestMapping(path = "/eDnevnik/odeljenje")
public class OdeljenjeController {
	
	@Autowired
	OdeljenjeRepository odeljenjeRepo;
	
	@RequestMapping(value = "", method = RequestMethod.POST)
	public OdeljenjeEntity dodajOdeljenje(@RequestParam Integer razred, @RequestParam String oznakaOdeljenja) {
		OdeljenjeEntity odeljenje = new OdeljenjeEntity();
			odeljenje.setRazred(razred);
			odeljenje.setOznakaOdeljenja(oznakaOdeljenja);
			return odeljenjeRepo.save(odeljenje);
		
	}

	@RequestMapping(value = "", method = RequestMethod.GET)
	public List<OdeljenjeEntity> svaOdeljenja() {
		return (List<OdeljenjeEntity>) odeljenjeRepo.findAll();
	}

	@RequestMapping(value = "/{id_odeljenja}", method = RequestMethod.GET)
	public OdeljenjeEntity pronadjiOdeljenje(@PathVariable Integer id_odeljenja) {
		return odeljenjeRepo.findById(id_odeljenja).get();
	}

	@RequestMapping(value = "/{id_odeljenja}", method = RequestMethod.DELETE)
	public OdeljenjeEntity obrissiOdeljenje(@PathVariable Integer id_odeljenja) {
		OdeljenjeEntity odeljenje = odeljenjeRepo.findById(id_odeljenja).get();
		odeljenjeRepo.delete(odeljenje);
		return odeljenje;
	}

	@RequestMapping(value = "/{id_odeljenja}", method = RequestMethod.PUT)
	public OdeljenjeEntity izmeniOdeljenje(@RequestParam Integer razred, @RequestParam String oznakaOdeljenja,
									@PathVariable Integer id_odeljenja) {
		if (odeljenjeRepo.existsById(id_odeljenja)) {
			OdeljenjeEntity odeljenje = odeljenjeRepo.findById(id_odeljenja).get();
			if (razred != 0)
				odeljenje.setRazred(razred);
			if (!oznakaOdeljenja.isEmpty())
				odeljenje.setOznakaOdeljenja(oznakaOdeljenja);
			return odeljenjeRepo.save(odeljenje);
		}
		return null;
	}

}
