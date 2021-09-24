package com.example.demo.service.Species;

import com.example.demo.model.Species;
import com.example.demo.repository.SpeciesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
@Service
public class SpeciesService implements ISpeciesService{

    @Autowired
    private SpeciesRepository speciesRepository;

    @Override
    public Iterable<Species> findAll() {
        return speciesRepository.findAll();
    }

    @Override
    public Optional<Species> findById(Long id) {
        return speciesRepository.findById(id);
    }

    @Override
    public void save(Species species) {
        speciesRepository.save(species);
    }

    @Override
    public void delete(Long id) {
        speciesRepository.deleteById(id);
    }
}
