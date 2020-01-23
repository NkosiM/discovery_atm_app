package com.discovery.atm.app.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.springframework.lang.NonNull;

import javax.persistence.*;
import java.io.Serializable;


@Entity
@Table(name = "client_sub_type")
@Getter
@Setter
@NoArgsConstructor
public class ClientSubType implements Serializable {

    @Id
    @NonNull
    @Column(name = "client_sub_type_code")
    private String client_sub_type_code;

    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "client_type_code", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    @NonNull
    private ClientType clientTypeCode;

    @NonNull
    private String description;


}
