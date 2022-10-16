package com.rttbanking.stockmanagement.repository;

import com.rttbanking.stockmanagement.dto.AccountType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountTypeRepository extends JpaRepository<AccountType, Long> {
    AccountType getAccountTypeByAccountType(String accountType);
}
