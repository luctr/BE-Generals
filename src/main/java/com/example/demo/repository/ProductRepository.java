package com.example.demo.repository;

import com.example.demo.model.Product;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public interface ProductRepository extends CrudRepository<Product, Long> {
    @Query("select p from Product p where p.name like concat('%',?1,'%')")
    List<Product> findByName(String name);

    @Query(value = "select * from Product order by id desc", nativeQuery = true)
    Iterable<Product> findAll();
}
