package com.discovery.atm.app.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.springframework.lang.NonNull;

import javax.persistence.*;
import java.io.Serializable;


@Entity
@Table(name = "client_sub_type")
@Data
public class ClientSubType implements Serializable {

    @Id
    @NonNull
//    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "client_sub_type_code")
    private String clientSubTypeCode;

    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "client_type_code", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    @NonNull
    private ClientType clientTypeCode;

    @NonNull
    private String description;


}
