package com.jwierzb.parkingspaces.dao;

import com.jwierzb.parkingspaces.entity.Transaction;
import com.jwierzb.parkingspaces.entity.UserEntity;
import com.jwierzb.parkingspaces.entity.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface TransactionDao extends JpaRepository<Transaction, Long> {

    @Query(
            value = "SELECT t FROM TRANSACTION t WHERE t.USER_ID = :#{#userEntity.id} and t.vehicle_id = :#{#vehicle.id}",
            nativeQuery = true
    )
    Optional<Transaction> findByUserAndVehicleAndEndTimeIsNull(@Param("user") UserEntity userEntity, @Param("vehicle") Vehicle vehicle);

    @Query(
            value = "SELECT CASE WHEN COUNT(USER_ID) > 0 THEN true ELSE false END FROM TRANSACTION t WHERE t.USER_ID = :#{#userEntity.id} and t.VEHICLE_ID = :#{#vehicle.id} and t.END_TIME IS NULL",
            nativeQuery = true
    )
    Boolean existsByUserAndVehicleAndEndTimeIsNull(@Param("user") UserEntity userEntity, @Param("vehicle") Vehicle vehicle);

    @Query(
            value = "SELECT t FROM TRANSACTION t WHERE t.USER_ID = :#{#userEntity.id} AND t.END_TINE IS NOT NULL",
            nativeQuery = true
    )
    List<Transaction> findAllByUserAndEndTimeNotNull(@Param("user") UserEntity userEntity);

    @Query(
            value = "SELECT t FROM TRANSACTION t WHERE t.VEHICLE_ID = :#{#vehicle.id} AND t.END_TIME IS NULL",
            nativeQuery = true
    )
    Optional<Transaction> findByVehicleAndEndTimeIsNull(@Param("vehicle") Vehicle vehicle);

    @Query(
            value = "SELECT COUNT(ID) FROM TRANSACTION t WHERE t.END_TIME IS NULL",
            nativeQuery = true
    )
    Integer countAllByEndTimeNull();

    @Query(
            value = "SELECT COUNT(ID) FROM TRANSACTION t WHERE t.END_TIME <= :#{#right} AND t.END_TIME >= :#{#left}",
            nativeQuery = true
    )
    Integer countAllByEndTimeBetween(@Param("left") LocalDateTime left, @Param("right") LocalDateTime right);

    @Query(
            value = "SELECT t FROM TRANSACTION t WHERE t.END_TIME <= :#{#right} AND t.END_TIME >= :#{#left}",
            nativeQuery = true
    )
    List<Transaction> findAllByEndTimeBetween(LocalDateTime left, LocalDateTime right);

}
