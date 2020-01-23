package com.discovery.atm.app.infrastructure.persistence;

import com.discovery.atm.app.domain.Atm_Allocation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface AtmAllocationRepository extends JpaRepository<Atm_Allocation, Integer> {

    @Query(value = "SELECT aa.*, de.value,(aa.count*de.value) as demominationSum" +
            " FROM atm_allocation aa " +
            "   join denomination de on aa.denomination_id = de.denomination_id " +
            " where aa.atm_id = :atmId " +
            " order by de.value desc", nativeQuery = true)
    List<Object[]> getListOfDenominationsAndSumByAtmId(@Param("atmId") Integer atmId);
}
