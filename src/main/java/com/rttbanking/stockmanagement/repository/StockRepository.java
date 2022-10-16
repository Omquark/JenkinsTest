package com.rttbanking.stockmanagement.repository;

import com.rttbanking.stockmanagement.dto.Stock;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface StockRepository extends JpaRepository<Stock, Stock.StockUserKey> {

    @Query(value = "SELECT count FROM tbl_stock WHERE tbl_stock.userId = ?1 AND tbl_stock.stockId = ?2", nativeQuery = true)
    Optional<Integer> getCountByStockUserKey(int userId, String stockId);
}
