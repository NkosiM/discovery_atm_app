package com.discovery.atm.app.infrastructure.persistence;

import com.discovery.atm.app.domain.ClientAccount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ReportingRepository extends JpaRepository<ClientAccount, Integer> {

    @Query(value = "SELECT ca.client_id, c.surname ,ca.client_account_number,t.description, ca.display_balance" +
            " FROM client_account ca " +
            " join client c on c.client_id = ca.client_id" +
            " join account_type t on ca.account_type_code =  t.account_type_code" +
            " WHERE (ca.client_id,ca.display_balance) IN " +
            " (SELECT c.client_id, MAX(c.display_balance)" +
            "  FROM client_account c" +
            "  join account_type t on c.account_type_code =  t.account_type_code" +
            "  where t.transactional = true" +
            "  GROUP BY c.client_id) " +
            " order by ca.display_balance desc", nativeQuery = true)
    List<Object[]> findAllTransactionalAccountsPerClientId(boolean transactional);


    @Query(value = "SELECT sum(c.display_balance) as Transactional_Balance " +
            "  FROM client_account c " +
            "  join account_type t on c.account_type_code = t.account_type_code " +
            "  where c.client_id = :id " +
            "  and t.transactional = true " +
            "  GROUP BY c.client_id", nativeQuery = true)
    Object findSumOfTransactionalAccById(Integer id);


    @Query(value = "  select sum(c.display_balance) as Loan_Balance " +
            "  FROM client_account c " +
            "  join account_type t on c.account_type_code =  t.account_type_code " +
            "  where c.client_id = :id " +
            "  and c.account_type_code like '%LOAN%' " +
            "  and t.transactional = false", nativeQuery = true)
    Object findSumOfLoanAccById(Integer id);
}
