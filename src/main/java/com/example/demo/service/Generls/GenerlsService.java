package com.example.demo.service.Generls;

import com.example.demo.model.Generals;
import com.example.demo.repository.GenerlsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class GenerlsService implements IGenerlsService{

    @Autowired
    private GenerlsRepository generlsRepository;

    @Override
    public Iterable<Generals> findAll() {
        return generlsRepository.findAll();
    }

    @Override
    public Optional<Generals> findById(Long id) {
        return generlsRepository.findById(id);
    }

    @Override
    public void save(Generals generals) {
        generlsRepository.save(generals);
    }

    @Override
    public void delete(Long id) {
        generlsRepository.deleteById(id);
    }
}
