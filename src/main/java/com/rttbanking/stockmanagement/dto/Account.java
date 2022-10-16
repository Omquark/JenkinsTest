package com.rttbanking.stockmanagement.dto;

import lombok.*;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "tbl_account")
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long accountId;

    @ManyToOne(targetEntity = AccountType.class)
    @JoinColumn(name = "typeId")
    AccountType type;
    int userId;
    float balance;
    LocalDate statementDate;
    boolean closed;
}
