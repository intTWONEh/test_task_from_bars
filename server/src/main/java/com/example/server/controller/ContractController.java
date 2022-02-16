package com.example.server.controller;

import com.example.server.model.Contract;
import com.example.server.service.ContractService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ContractController {
    private final ContractService contractService;

    public ContractController(ContractService contractService) {
        this.contractService = contractService;
    }

    @GetMapping("/api/v1/contract")
    public List<Contract> getContract(){
        return contractService.getContract();
    }

}
