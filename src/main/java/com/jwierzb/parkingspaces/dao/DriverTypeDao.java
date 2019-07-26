package com.jwierzb.parkingspaces.dao;

import com.jwierzb.parkingspaces.entity.DriverType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DriverTypeDao extends JpaRepository<DriverType, Integer> {

    @Query(
            value = "SELECT * FROM  DRIVER_TYPE c WHERE c.name = :name",
            nativeQuery = true)
    Optional<DriverType> findByName(@Param("name") String name);
}
