package com.jwierzb.parkingspaces.dao;

import com.jwierzb.parkingspaces.entity.Currency;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CurrencyDao extends JpaRepository<Currency, Integer> {

    @Query(
            value = "SELECT * FROM  CURRENCY c WHERE c.name = :name",
            nativeQuery = true)
    Optional<Currency> findByName(@Param("name") String name);
}
