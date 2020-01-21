package com.discovery.atm.app.domain;

import lombok.Data;
import org.springframework.lang.NonNull;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "denomination_type")
@Data
public class DenominationTypeCode implements Serializable {

    @Id
    @NonNull
//    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "denomination_type_code")
    private String denominationTypeCode;

    @Column(name = "description")
    private String description;
}
