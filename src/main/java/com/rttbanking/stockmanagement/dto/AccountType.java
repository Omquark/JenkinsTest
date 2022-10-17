package com.rttbanking.stockmanagement.dto;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "tbl_account_type")
@Getter @Setter
@NoArgsConstructor
@EqualsAndHashCode
public class AccountType {
    @Id
    long typeId;
    String accountType;
}