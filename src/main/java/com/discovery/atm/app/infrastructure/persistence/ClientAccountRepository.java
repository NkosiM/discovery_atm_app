package com.discovery.atm.app.infrastructure.persistence;

import com.discovery.atm.app.domain.ClientAccount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

public interface ClientAccountRepository extends JpaRepository<ClientAccount, Integer> {

    @Query(value = "Select ca.client_account_number, act.description, ca.display_balance " +
            "  from client_account ca " +
            "     join account_type act on ca.account_type_code = act.account_type_code" +
            "  where ca.client_id = :clientId" +
            "    and act.transactional = :transactional " +
            " ORDER by ca.display_balance desc ", nativeQuery = true)

    List<Object[]> findAllByClientId(@Param("clientId") Integer clientId, @Param("transactional") boolean transactional);


    @Query(value = "Select ca.client_account_number, ca.display_balance, ccr.*" +
            "    from client_account ca " +
            "    join account_type a on ca.account_type_code = a.account_type_code" +
            "    join currency_conversion_rate ccr on ca.currency_code = ccr.currency_code" +
            "    where ca.client_id = :id" +
            "    and a.transactional = :transactional", nativeQuery = true)
    List<Object[]> findAllCurrencyAccountsByClientId(Integer id, @Param("transactional") boolean transactional);


    ClientAccount findByClientAccountNumber(String accountNumber);

    @Transactional
    @Modifying
    @Query(value =" UPDATE client_account " +
            " SET  display_balance= :amountAfterWithdrawal " +
            " WHERE client_account_number= :clientAccNum ", nativeQuery = true)
    void updateClientAccountBalance(@Param("amountAfterWithdrawal")BigDecimal amountAfterWithdrawal, @Param("clientAccNum")String clientAccNum);
}
