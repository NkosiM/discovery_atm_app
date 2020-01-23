package com.discovery.atm.app.domain;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.lang.NonNull;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name = "client_type")
@Getter
@Setter
@NoArgsConstructor
public class ClientType implements Serializable {

    @Id
    @NonNull
    @Column(name = "client_type_code")
    private String clientTypeCode;

    @NonNull
    private String description;


}
