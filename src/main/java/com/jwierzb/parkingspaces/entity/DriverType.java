package com.jwierzb.parkingspaces.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.Proxy;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

import static lombok.AccessLevel.PRIVATE;

@Entity
@Data
@Table(name = "DRIVER_TYPE")
@FieldDefaults(level = PRIVATE)
@Proxy(lazy = false)
@AllArgsConstructor
@NoArgsConstructor
public class DriverType {

    @Id
    @Column(name = "ID")
    @JsonIgnore
    Integer id;

    @NotNull
    @Column(name="NAME")
    String name;

    /**
     * @firstHourPrice
     * @secondHourPrice are in PLN for default
     */
    @NotNull
    @Column(name = "FIRST_HOUR_PRICE")
    Double firstHourPrice;

    @NotNull
    @Column(name = "SECOND_HOUR_PRICE")
    Double secondHourPrice;

    @NotNull
    @Column(name = "NEXT_HOURS_MULTIPILER")
    Double nextHoursMultipiler;

}
