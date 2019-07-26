package com.jwierzb.parkingspaces.dao;

import com.jwierzb.parkingspaces.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface UserDao extends JpaRepository<UserEntity, Long> {


    @Modifying
    @Query(
            value = "INSERT INTO USER (EMAIL, FIRST_NAME, LAST_NAME, USER_NAME, PASSWORD, PHONE_NUMBER, CURRENCY_ID, DRIVER_TYPE_ID) values " +
                    "( :#{#userEntity.email},:#{#userEntity.firstName}, :#{#userEntity.lastName}, :#{#userEntity.username}, :#{#userEntity.password}, :#{#userEntity.phoneNumber}, :#{#userEntity.currency.id}, :#{#userEntity.driverType.id})",
            nativeQuery = true
    )
    void saveUserEntity(@Param("userEntity")UserEntity userEntity);

    UserEntity save(UserEntity userEntity);
    @Query(
            value = "SELECT * FROM USER t where t.USER_NAME=:name",
            nativeQuery = true
    )
    UserEntity findByUsername(@Param("name") String name);


    @Query(
            value = "SELECT CASE WHEN COUNT(USER_NAME) > 0 THEN true ELSE false END FROM USER t WHERE t.USER_NAME = :name",
            nativeQuery = true
    )
    Boolean existsByUsername(@Param("name") String name);

    @Query(
            value = "SELECT CASE WHEN COUNT(USER_NAME) > 0 THEN true ELSE false END FROM USER t WHERE t.EMAIL = :email",
            nativeQuery = true
    )
    Boolean existsByEmail(@Param("email") String email);
}
