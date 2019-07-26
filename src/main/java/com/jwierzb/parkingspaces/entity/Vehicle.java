package com.jwierzb.parkingspaces.entity;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

import static lombok.AccessLevel.*;

/**
 * UserEntity's vehilce class. Users can have one vehicle.
 * One vehicle can be assigned to exactly one UserEntity's account.
 * TODO let userEntity have more vehicles than one and let users share vehicles
 */

@Table(name = "VEHICLE")
@Entity
@FieldDefaults(level = PRIVATE)
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Vehicle {

    @Id
    @Column(name = "ID")
    @JsonIgnore
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "author_generator")
    @SequenceGenerator(name="author_generator", sequenceName = "vehicle_id_sequence")
    Long id;

    @Length(max = 10)
    @Column(name = "REGISTRATION_NUMBER", unique = true)
    String registrationNumber;

    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @NotNull
    @JsonIgnore
    UserEntity user;


    @JsonGetter
    String getUsername(){return user.getUsername();}

}
