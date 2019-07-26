package com.jwierzb.parkingspaces.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
import static lombok.AccessLevel.*;

import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.Proxy;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Collection;
import java.util.LinkedHashSet;

@Entity
@Table(name = "ROLE")
@FieldDefaults(level = PRIVATE)
@Data
@Proxy(lazy = false)
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Role implements GrantedAuthority {

    @Id
    @Column(name = "ID", updatable = false)
    Integer id;

    @NotNull
    @Column(name="NAME", unique = true)
    String name;

    @Override
    public String getAuthority() {
        return "ROLE_"+name;
    }


}
