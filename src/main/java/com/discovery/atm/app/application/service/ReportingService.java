package com.discovery.atm.app.application.service;

import com.discovery.atm.app.domain.Client;
import com.discovery.atm.app.domain.dto.AccountReportDto;
import com.discovery.atm.app.domain.dto.AggregateReportDto;
import com.discovery.atm.app.exception.ResourceNotFound;
import com.discovery.atm.app.infrastructure.persistence.ClientAccountRepository;
import com.discovery.atm.app.infrastructure.persistence.ClientRepository;
import com.discovery.atm.app.infrastructure.persistence.ReportingRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ReportingService {

    private ReportingRepository reportingRepository;

    private ClientAccountRepository clientAccountRepository;

    private ClientRepository clientRepository;

    public ReportingService(ReportingRepository reportingRepository, ClientAccountRepository clientAccountRepository, ClientRepository clientRepository) {
        this.reportingRepository = reportingRepository;
        this.clientRepository = clientRepository;
        this.clientAccountRepository = clientAccountRepository;
    }

    public List<AccountReportDto> getHighestTransactionalAccountPerUser(boolean transactional) throws ResourceNotFound {

        List<Object[]> listOfHighestTransactionalAcc = reportingRepository.findAllTransactionalAccountsPerClientId(transactional);

        if (listOfHighestTransactionalAcc.size() == 0) {
            throw new ResourceNotFound();
        }

        return listOfHighestTransactionalAcc.stream()
                .map(AccountReportDto::mapToAccountReportDto)
                .collect(Collectors.toList());
    }

    public AggregateReportDto getAggregateFinancialPositions(Integer id) throws ResourceNotFound {

         Client client = clientRepository.findById(id).get();

        if (client == null) {
            throw new ResourceNotFound();
        }

        AggregateReportDto aggregateReportDto = new AggregateReportDto();

        aggregateReportDto.setClientTitle(client.getTitle());
        aggregateReportDto.setClientName(client.getName());
        aggregateReportDto.setClientSurname(client.getSurname());

        //  retrieve user transactional account balance
        aggregateReportDto.setTransactionalAccBalance(new BigDecimal(reportingRepository.findSumOfTransactionalAccById(id).toString()));

        //  retrieve user loan account balance
        aggregateReportDto.setLoanAccBalance(new BigDecimal(reportingRepository.findSumOfLoanAccById(id).toString()));

        //get the Net position
        aggregateReportDto.setNetPosition(aggregateReportDto.getTransactionalAccBalance().add(aggregateReportDto.getLoanAccBalance()));

        return aggregateReportDto;
    }
}
