package com.jwierzb.parkingspaces.entity;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import javax.transaction.Transactional;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

import static java.lang.Math.pow;
import static lombok.AccessLevel.*;

@Entity
@Data
@Table(name = "TRANSACTION")
@FieldDefaults(level = PRIVATE)
@Builder
@AllArgsConstructor
@Transactional
public class Transaction {

    @Id
    @Column(name = "ID")
    @JsonIgnore
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "author_generator")
    @SequenceGenerator(name="author_generator", sequenceName = "transaction_id_sequence")
    Long id;

    @Column(name = "END_TIME")
    @JsonIgnore
    LocalDateTime endTime;

    @NotNull
    @OneToOne(fetch = FetchType.EAGER)
    @JsonIgnore
    UserEntity user;

    @NotNull
    @OneToOne(fetch = FetchType.EAGER)
    @JsonIgnore
    Vehicle vehicle;

    @NotNull
    @JsonIgnore
    @Column(name = "START_TIME", nullable = false, updatable = false)
    LocalDateTime startTime;

    @Column(name = "PRICE")
    @JsonIgnore
    BigDecimal price;

    @Column(name = "PAYED")
    @Type(type = "org.hibernate.type.NumericBooleanType")
    @Builder.Default
    Boolean payed=false;


    @JsonGetter
    String getParkingPrice()
    {
        if(price == null) return "N/A";
        return String.format("%.3f", price.divide(BigDecimal.valueOf(user.getCurrency().getExchangeRate()))) + user.getCurrency().getName();
    }

    /**
     * Update price with value in PLN
     */
    @PreUpdate
    void setPrice()
    {
        if(endTime==null) return;

        Currency currency = user.getCurrency();
        DriverType driverType = user.getDriverType();
        long hours = ChronoUnit.HOURS.between(startTime, endTime)+1;

        if(driverType.getNextHoursMultipiler()==1) price = BigDecimal.valueOf(driverType.getFirstHourPrice()+(hours-1)*driverType.getSecondHourPrice());
        price = BigDecimal.valueOf(driverType.getFirstHourPrice()+driverType.getSecondHourPrice()*(1-pow(driverType.getNextHoursMultipiler(), hours-1))/(1-driverType.getNextHoursMultipiler()));

    }


    @PrePersist
    void startTime(){
        this.startTime = LocalDateTime.now();
    }

    @JsonGetter
    String getEndTime()
    {
        if(endTime==null) return "N/A";
        return endTime.toString();
    }
    @JsonGetter
    String getStartTime()
    {
        return startTime.toString();    }

    @JsonGetter
    String getUserName() { return user.getUsername();}

    @JsonGetter
    String getVehicleRegistrationNumber(){return vehicle.getRegistrationNumber();
    }
    public Transaction(){}


}
