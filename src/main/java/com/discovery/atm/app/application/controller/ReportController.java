package com.discovery.atm.app.application.controller;

import com.discovery.atm.app.application.service.ReportingService;
import com.discovery.atm.app.domain.dto.AccountReportDto;
import com.discovery.atm.app.domain.dto.AggregateReportDto;
import com.discovery.atm.app.exception.ResourceNotFound;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/reports")
@RestController
public class ReportController {

    private ReportingService reportingService;

    public ReportController(ReportingService reportingService) {
        this.reportingService = reportingService;
    }

    @GetMapping("/highestTransactionalAccount")
    public List<AccountReportDto> getHighestTransactionalAccountPerUser(@RequestParam boolean transactional) throws ResourceNotFound {

        return reportingService.getHighestTransactionalAccountPerUser(transactional);
    }


    @GetMapping("/{id}/aggregateFinPosition")
    public AggregateReportDto getAggregateFinancialPositions(@PathVariable Integer id) throws ResourceNotFound {

        return reportingService.getAggregateFinancialPositions(id);
    }

}
