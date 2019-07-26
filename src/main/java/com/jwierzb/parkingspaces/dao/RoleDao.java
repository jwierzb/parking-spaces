package com.jwierzb.parkingspaces.dao;

import com.jwierzb.parkingspaces.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.SequenceGenerator;
import java.util.List;
import java.util.Optional;


@Repository
public interface RoleDao extends JpaRepository<Role, Integer> {

    @Query(
            value = "SELECT * FROM  ROLE c WHERE c.name = :name",
            nativeQuery = true)
    Optional<Role> findByName(@Param("name") String name);

    @Query(
            value = "SELECT * FROM  ROLE c WHERE c.name = :name",
            nativeQuery = true)
    List<Role> findAllByName(@Param("name") String name);
}
