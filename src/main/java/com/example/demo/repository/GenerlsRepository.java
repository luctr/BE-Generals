package com.example.demo.repository;

import com.example.demo.model.Generals;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GenerlsRepository extends CrudRepository<Generals, Long> {
}
