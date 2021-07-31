package com.iktpreobuka.eDnevnik.repositories;

import org.springframework.data.repository.CrudRepository;

import com.iktpreobuka.eDnevnik.entities.PredajeEntity;

public interface PredajeRepository extends CrudRepository<PredajeEntity, Integer> {

	boolean existsByNastavnikIdAndPredmetIdAndOdeljenjeId(Integer id_nastavnika, Integer id_predmeta,
			Integer id_odeljenja);

	PredajeEntity findPredajeByNastavnikIdAndPredmetIdAndOdeljenjeId(Integer id_nastavnika, Integer id_predmeta,
			Integer id_odeljenja);

}
