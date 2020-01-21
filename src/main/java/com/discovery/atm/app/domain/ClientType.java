package com.discovery.atm.app.domain;

import lombok.Data;
import org.springframework.lang.NonNull;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name = "client_type")
@Data
public class ClientType implements Serializable {

    @Id
    @NonNull
    @Column(name = "client_type_code")
    private String clientTypeCode;

    @NonNull
    private String description;


}
