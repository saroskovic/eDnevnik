package com.iktpreobuka.eDnevnik.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.iktpreobuka.eDnevnik.entities.OdeljenjeEntity;
import com.iktpreobuka.eDnevnik.entities.PredmetEntity;
import com.iktpreobuka.eDnevnik.repositories.PredmetRepository;

@RestController
@RequestMapping(path = "/eDnevnik/predmet")
public class PredmetController {

	@Autowired
	PredmetRepository predmetRepo;
	
	@RequestMapping(value = "", method = RequestMethod.POST)
	public PredmetEntity dodajPredmet(@RequestParam String nazivPredmeta, @RequestParam Integer fond) {
		PredmetEntity predmet = new PredmetEntity();
			predmet.setFond(fond);
			predmet.setNazivPredmeta(nazivPredmeta);
			return predmetRepo.save(predmet);
		
	}

	@RequestMapping(value = "", method = RequestMethod.GET)
	public List<PredmetEntity> sviPredmeti() {
		return (List<PredmetEntity>) predmetRepo.findAll();
	}

	@RequestMapping(value = "/{id_predmeta}", method = RequestMethod.GET)
	public PredmetEntity pronadjiPredmet(@PathVariable Integer id_predmeta) {
		if(predmetRepo.existsById(id_predmeta)) {
		return predmetRepo.findById(id_predmeta).get();
		}
		return null;
	}

	@RequestMapping(value = "/{id_predmeta}", method = RequestMethod.DELETE)
	public PredmetEntity obrissiPredmet(@PathVariable Integer id_predmeta) {
		PredmetEntity predmet = predmetRepo.findById(id_predmeta).get();
		predmetRepo.delete(predmet);
		return predmet;
	}

	@RequestMapping(value = "/{id_predmeta}", method = RequestMethod.PUT)
	public PredmetEntity izmeniPredmet(@PathVariable Integer id_predmeta, @RequestParam String nazivPredmeta,
									@RequestParam Integer fond) {
		if (predmetRepo.existsById(id_predmeta)) {
			PredmetEntity predmet = predmetRepo.findById(id_predmeta).get();
			if (!fond.equals(null) )
				predmet.setFond(fond);
			if (!nazivPredmeta.isEmpty())
				predmet.setNazivPredmeta(nazivPredmeta);
			return predmetRepo.save(predmet);
		}
		return null;
	}

}
