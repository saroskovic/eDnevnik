package com.iktpreobuka.eDnevnik.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.annotation.JsonView;
import com.iktpreobuka.eDnevnik.entities.NastavnikEntity;
import com.iktpreobuka.eDnevnik.entities.OdeljenjeEntity;
import com.iktpreobuka.eDnevnik.entities.PredajeEntity;
import com.iktpreobuka.eDnevnik.entities.PredmetEntity;
import com.iktpreobuka.eDnevnik.repositories.NastavnikRepository;
import com.iktpreobuka.eDnevnik.repositories.OdeljenjeRepository;
import com.iktpreobuka.eDnevnik.repositories.PredajeRepository;
import com.iktpreobuka.eDnevnik.repositories.PredmetRepository;

@RestController
@RequestMapping(path = "/eDnevnik/predaje")
public class PredajeKontroler {

	@Autowired
	PredajeRepository predajeRepo;
	@Autowired
	NastavnikRepository nastavnikRepo;
	@Autowired
	PredmetRepository predmetRepo;
	@Autowired
	OdeljenjeRepository odeljenjeRepo;

	@RequestMapping(value = "/nastavnik/{id_nastavnika}/odeljenje/{id_odeljenja}/predmet/{id_predmeta}", method = RequestMethod.POST)
	public PredajeEntity dodajPredmetNastavniku(@PathVariable Integer id_nastavnika, @PathVariable Integer id_odeljenja,
			@PathVariable Integer id_predmeta) {
		if (nastavnikRepo.existsById(id_nastavnika)) {
			NastavnikEntity nastavnik = nastavnikRepo.findById(id_nastavnika).get();
			if (odeljenjeRepo.existsById(id_odeljenja)) {
				OdeljenjeEntity odeljenje = odeljenjeRepo.findById(id_odeljenja).get();
				if (predmetRepo.existsById(id_predmeta)) {
					PredmetEntity predmet = predmetRepo.findById(id_predmeta).get();
					PredajeEntity predaje = new PredajeEntity();
					predaje.setNastavnik(nastavnik);
					predaje.setOdeljenje(odeljenje);
					predaje.setPredmet(predmet);
					return predajeRepo.save(predaje);

				}
			}
		}
		return null;
	}

	@RequestMapping(value = "", method = RequestMethod.GET)
	public List<PredajeEntity> izlistajPredaje() {
		return (List<PredajeEntity>) predajeRepo.findAll();
	}

	@RequestMapping(value = "/{id_predaje}", method = RequestMethod.GET)
	public PredajeEntity pronadjiPoId(@PathVariable Integer id_predaje) {
		if (predajeRepo.existsById(id_predaje)) {
			PredajeEntity predaje = predajeRepo.findById(id_predaje).get();
			return predaje;
		}
		return null;
	}

	@RequestMapping(value = "/{id_predaje}", method = RequestMethod.DELETE)
	public PredajeEntity izbrisiPredaje(@PathVariable Integer id_predaje) {
		if (predajeRepo.existsById(id_predaje)) {
			PredajeEntity predaje = predajeRepo.findById(id_predaje).get();
			predajeRepo.delete(predaje);
			return predaje;
		}
		return null;
	}

	@RequestMapping(value = "/nastavnik/{id_nastavnika}/odeljenje/{id_odeljenja}/predmet/{id_predmeta}", method = RequestMethod.PUT)
	public PredajeEntity izmeniPredaje(@RequestParam Integer id_predaje, @PathVariable Integer id_nastavnika,
			@PathVariable Integer id_odeljenja, @PathVariable Integer id_predmeta) {
		if (predajeRepo.existsById(id_predaje)) {
			PredajeEntity predaje = predajeRepo.findById(id_predaje).get();
			if (nastavnikRepo.existsById(id_nastavnika)) {
				NastavnikEntity nastavnik = nastavnikRepo.findById(id_nastavnika).get();
				if (odeljenjeRepo.existsById(id_odeljenja)) {
					OdeljenjeEntity odeljenje = odeljenjeRepo.findById(id_odeljenja).get();
					if (predmetRepo.existsById(id_predmeta)) {
						PredmetEntity predmet = predmetRepo.findById(id_predmeta).get();
						predaje.setNastavnik(nastavnik);
						predaje.setOdeljenje(odeljenje);
						predaje.setPredmet(predmet);
						return predajeRepo.save(predaje);
					}
				}
			}
		}
		return null;

	}
}
