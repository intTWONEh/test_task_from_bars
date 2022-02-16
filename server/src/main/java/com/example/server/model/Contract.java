package com.example.server.model;

import lombok.Data;
import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Enumeration;

@Data
@Entity
public class Contract{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Long number;

    @Column(nullable = false)
    private LocalDateTime contractCreationTime;

    @Column(nullable = false)
    private LocalDateTime contractLastUpdate;

    public Contract(){}

    public Contract(Long number, LocalDateTime contractCreationTime, LocalDateTime contractLastUpdate) {
        this.number = number;
        this.contractCreationTime = contractCreationTime;
        this.contractLastUpdate = contractLastUpdate;
    }
}
