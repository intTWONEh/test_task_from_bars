package com.example.server.service;

import com.example.server.model.Contract;
import com.example.server.repository.ContractRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.List;

@Service
public class ContractService {
    private final ContractRepository contractRepository;

    public ContractService(ContractRepository contractRepository) {
        this.contractRepository = contractRepository;
    }

    public List<Contract> getContract(){
        contractRepository.deleteAll();
        fillRandomContracts();
        return contractRepository.findAll();
    }

    private void fillRandomContracts(){
        List<Contract> contractList = new LinkedList<>();
        int numberGenerations = 1 + (int) (Math.random() * 15);

        for(int i = 0; i < numberGenerations; ++i){
            contractList.add(getRandomContract());
        }

        contractRepository.saveAll(contractList);
    }

    private Contract getRandomContract(){
        long number = 1L + (long) (Math.random() * 100L);

        LocalDateTime contractCreationTime = LocalDateTime.now()
                .minusDays(number)
                .minusHours(number)
                .minusMinutes(number)
                .minusSeconds(number)
                .withNano(0);

        LocalDateTime contractLastUpdate = contractCreationTime
                .plusDays(number % 10)
                .plusHours(number % 10)
                .plusMinutes(number % 10)
                .plusSeconds(number % 10);

        return new Contract(number, contractCreationTime, contractLastUpdate);
    }
}
