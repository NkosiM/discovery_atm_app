package com.discovery.atm.app.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.springframework.lang.NonNull;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "atm_allocation")
@Data
public class Atm_Allocation implements Serializable {

    @Id
    @NonNull
//    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "atm_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private ATM atm_id;

    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "denomination_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private Denomination denomination_id;

    @NonNull
    @Column(name = "count")
    private int count;
}
