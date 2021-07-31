package com.iktpreobuka.eDnevnik.controllers;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.iktpreobuka.eDnevnik.entities.NastavnikEntity;
import com.iktpreobuka.eDnevnik.entities.OcenaEntity;
import com.iktpreobuka.eDnevnik.entities.OdeljenjeEntity;
import com.iktpreobuka.eDnevnik.entities.PredajeEntity;
import com.iktpreobuka.eDnevnik.entities.PredmetEntity;
import com.iktpreobuka.eDnevnik.entities.UcenikEntity;
import com.iktpreobuka.eDnevnik.models.EmailObject;
import com.iktpreobuka.eDnevnik.repositories.NastavnikRepository;
import com.iktpreobuka.eDnevnik.repositories.OcenaRepository;
import com.iktpreobuka.eDnevnik.repositories.PredajeRepository;
import com.iktpreobuka.eDnevnik.repositories.PredmetRepository;
import com.iktpreobuka.eDnevnik.repositories.UcenikRepository;
import com.iktpreobuka.eDnevnik.services.EmailServiceImpl;

@RestController
@RequestMapping(path = "/eDnevnik/ocena")
public class OcenaController {

	

		@Autowired
		private OcenaRepository ocenaRepository;
		@Autowired
		private NastavnikRepository nastavnikRepository;
		@Autowired
		private PredmetRepository predmetRepository;
		@Autowired
		private UcenikRepository ucenikRepository;
		@Autowired
		private PredajeRepository predajeRepository;
		@Autowired
		EmailServiceImpl emailService;

		@RequestMapping(value = "/nastavnik/{id_nastavnika}/predmet/{id_predmeta}/ucenik/{id_ucenika}", method = RequestMethod.POST)
		public OcenaEntity dodajOcenu(@PathVariable Integer id_nastavnika, @PathVariable Integer id_predmeta,
				@PathVariable Integer id_ucenika, @RequestParam Integer vrednost,
				@RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate datumOcene) throws Exception {
			if (nastavnikRepository.existsById(id_nastavnika)) {
				NastavnikEntity nastavnik = nastavnikRepository.findById(id_nastavnika).get();
				if (predmetRepository.existsById(id_predmeta)) {
					PredmetEntity predmet = predmetRepository.findById(id_predmeta).get();
					if (ucenikRepository.existsById(id_ucenika)) {
						OdeljenjeEntity odeljenje = ucenikRepository.findById(id_ucenika).get().getOdeljenje();
						Integer id_odeljenja = odeljenje.getId();
						if (predajeRepository.existsByNastavnikIdAndPredmetIdAndOdeljenjeId(id_nastavnika, id_predmeta,
								id_odeljenja)) {
							PredajeEntity ocenio = predajeRepository.findPredajeByNastavnikIdAndPredmetIdAndOdeljenjeId(
									id_nastavnika, id_predmeta, id_odeljenja);
							UcenikEntity ucenik = ucenikRepository.findById(id_ucenika).get();
							if (odeljenje.getUcenici().contains(ucenik)) {
								OcenaEntity ocena = new OcenaEntity();
								ocena.setDatumOcene(datumOcene);
								ocena.setVrednost(vrednost);
								ocena.setUcenik(ucenik);
								ocena.setOcenio(ocenio);
								ocenaRepository.save(ocena);
								EmailObject email = new EmailObject();
								email.setSubject("Ocena!");
								email.setTo(ucenik.getRoditelj().getEmail());
								String text = "Postovani nastavnik: " + nastavnik.getIme() + " "
										+ nastavnik.getPrezime() + " je ocenio vase dete: " + ucenik.getIme() + " "
										+ ucenik.getPrezime() + " ocenom: " + ocena.getVrednost() + " dana: "
										+ ocena.getDatumOcene() + " iz predmeta: " + predmet.getNazivPredmeta();
								email.setText(text);
								emailService.sendTemplateMessage(email);
								return ocena;
							}
						}
					}
				}
			}
			return null;
		}

		@RequestMapping(value = "", method = RequestMethod.GET)
		public List<OcenaEntity> sveOcene() {
			return (List<OcenaEntity>) ocenaRepository.findAll();
		}
	
}
