package com.rttbanking.stockmanagement.service;

import com.rttbanking.stockmanagement.dto.Account;
import com.rttbanking.stockmanagement.dto.Stock;
import com.rttbanking.stockmanagement.repository.AccountRepository;
import com.rttbanking.stockmanagement.repository.StockRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class StockService {

    @Autowired
    StockRepository stockRepo;

    @Transactional
    public boolean buyStock(Stock stock){
        stock.getStockUserKey().setStockId(stock.getStockUserKey().getStockId().toUpperCase());
        Optional<Stock> tempStock = stockRepo.findById(stock.getStockUserKey());

        if(stock.getCount() < 1){
            return false;
        }
        tempStock.ifPresent(value -> value.setCount(value.getCount() + stock.getCount()));
        stockRepo.save(tempStock.orElse(stock));
        return true;
    }

    @Transactional
    public boolean sellStock(Stock stock){
        stock.getStockUserKey().setStockId(stock.getStockUserKey().getStockId().toUpperCase());
        Optional<Stock> userOptStock = stockRepo.findById(stock.getStockUserKey());
        if((userOptStock.isEmpty()) || stock.getCount() < 1 || userOptStock.get().getCount() < stock.getCount()) {
            return false;
        }
        Stock userStock = userOptStock.get();
        userStock.setCount(userStock.getCount() - stock.getCount());
        stockRepo.save(userStock);
        return true;
    }

    public int getStockCount(int userId, String stockId){
        Optional<Integer> stockCount = stockRepo.getCountByStockUserKey(userId, stockId.toUpperCase());
        return stockCount.orElse(0);
    }

    public List<Stock> getAllStocks(){
        return stockRepo.findAll();
    }

    public Stock getStock(String stockId, int userId){
        if(stockId.isEmpty() || userId == 0){
            return null;
        }
        Stock.StockUserKey stockUserKey = new Stock.StockUserKey();
        stockUserKey.setStockId(stockId);
        stockUserKey.setUserId(userId);
        return stockRepo.findById(stockUserKey).orElse(null);
    }

    @Transactional
    public boolean updateStock(Stock stock, String oldId){
        if(stock.getCount() < 0 ||
                stock.getStockUserKey().getUserId() == 0 ||
                stock.getStockUserKey().getStockId().isEmpty()) {
            return false;
        }
        stockRepo.save(stock);

        if(!oldId.equals(stock.getStockUserKey().getStockId())){
            Stock newStock = new Stock();
            Stock.StockUserKey stockUserKey = new Stock.StockUserKey();
            stockUserKey.setStockId(oldId);
            stockUserKey.setUserId(stock.getStockUserKey().getUserId());
            newStock.setStockUserKey(stockUserKey);
            newStock.setCount(0);
            stockRepo.save(newStock);
        }
        return true;
    }
}
