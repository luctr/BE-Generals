package com.example.demo.repository;

import com.example.demo.model.Species;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SpeciesRepository extends CrudRepository<Species,Long> {
}
