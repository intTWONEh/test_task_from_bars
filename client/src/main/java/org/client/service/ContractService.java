package org.client.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.client.model.Contract;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

public class ContractService {
    public final String POSTS_API_URL = "http://localhost:9999/api/v1/contract";

    private List<Contract> get() {
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .GET()
                .header("accept", "application/json")
                .uri(URI.create(POSTS_API_URL))
                .build();
        HttpResponse<String> response;

        try {
            response = client.send(request, HttpResponse.BodyHandlers.ofString());
        } catch (Exception e) {
            //Добавить логгер
            System.err.println("Проблемы с соединением к серверу!");
            return null;
        }

        ObjectMapper mapper = new ObjectMapper();
        List<Contract> posts = null;

        try {
            posts = mapper.readValue(response.body(), new TypeReference<List<Contract>>() {});
        } catch (JsonProcessingException e) {
            //Добавить логгер
            System.err.println("Проблема с парсингом ответа!");
        }

        return posts;
    }

    public List<Contract> getContractList() {
        List<Contract> contractList = get();

        if(contractList == null || contractList.size() == 0) {
            return contractList;
        }

        contractList.forEach(contract -> {
            if(ChronoUnit.DAYS.between(contract.getContractLastUpdate(), LocalDateTime.now()) <= 60) contract.setActual(true);
        });

        return contractList;
    }

    public Boolean checkContractList(){
        return get() != null;
    }
}
