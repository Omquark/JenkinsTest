package com.rttbanking.stockmanagement.service;

import com.rttbanking.stockmanagement.dto.Stock;
import com.rttbanking.stockmanagement.repository.StockRepository;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class StockServiceTest {

    @Autowired
    StockService stockService;

    @Autowired
    StockRepository stockRepo;

    @Test
    @Order(1)
    void getStockCount() {
        assertEquals(5, stockService.getStockCount(1, "ibm"));
        assertEquals(13, stockService.getStockCount(1, "MSC"));
        assertEquals(10, stockService.getStockCount(2, "IBM"));
        assertEquals(0, stockService.getStockCount(3, "IBM"));
        assertEquals(10, stockService.getStockCount(3, "IBMA"));
        assertEquals(100, stockService.getStockCount(4, "stk"));
    }

    @Test
    @Order(2)
    void buyStock() {

        Stock tempStock = new Stock();
        Stock.StockUserKey stockUserKey = new Stock.StockUserKey();
        stockUserKey.setStockId("TST");
        stockUserKey.setUserId(1);
        tempStock.setStockUserKey(stockUserKey);
        tempStock.setCount(3);

        assertTrue(stockService.buyStock(tempStock));
        assertEquals(19, stockService.getStockCount(1, "tst"));

        stockUserKey.setStockId("123");
        tempStock.setStockUserKey(stockUserKey);
        tempStock.setCount(10);
        assertTrue(stockService.buyStock(tempStock));
        assertEquals(10, stockService.getStockCount(1, "123"));

        tempStock.setCount(-10);
        assertFalse(stockService.buyStock(tempStock));
        assertEquals(10, stockService.getStockCount(1, "123"));

    }

    @Test
    @Order(3)
    void sellStock() {
        Stock tempStock = new Stock();
        Stock.StockUserKey stockUserKey = new Stock.StockUserKey();
        stockUserKey.setUserId(2);
        stockUserKey.setStockId("ABC");
        tempStock.setCount(10);
        tempStock.setStockUserKey(stockUserKey);

        assertFalse(stockService.sellStock(tempStock));

        stockUserKey.setStockId("IBM");
        tempStock.setStockUserKey(stockUserKey);
        assertTrue(stockService.sellStock(tempStock));
        assertEquals(0, stockService.getStockCount(2, "ibm"));

        assertFalse(stockService.sellStock(tempStock));

        assertEquals(5, stockService.getStockCount(1, "ibm"));
    }

    @Test
    @Order(4)
    void GetAllStocks(){
        Stock.StockUserKey stockUserKey;
        Stock testStock;
        List<Stock> stocks = stockService.getAllStocks();

        assertEquals(10, stocks.size());
        stockUserKey = new Stock.StockUserKey();
        stockUserKey.setStockId("IBM");
        stockUserKey.setUserId(1);
        testStock = stockRepo.findById(stockUserKey).orElse(null);
        assertNotNull(testStock);
        stockRepo.delete(testStock);
        stocks = stockService.getAllStocks();
        assertEquals(9, stocks.size());
    }

    @Test
    @Order(5)
    void updateStock(){
        Stock testStock;
        Stock.StockUserKey stockUserKey = new Stock.StockUserKey();
        stockUserKey.setStockId("IBM");
        stockUserKey.setUserId(1);
        testStock = stockRepo.findById(stockUserKey).orElse(null);
        assertNotNull(testStock);
        testStock.setCount(10);
        assertTrue(stockService.updateStock(testStock, "IBM"));
        testStock = stockRepo.findById(stockUserKey).orElse(null);
        assertNotNull(testStock);
        assertEquals(10, testStock.getCount());
        stockUserKey.setStockId("OTHER");
        testStock.setStockUserKey(stockUserKey);
        assertTrue(stockService.updateStock(testStock, "IBM"));
        testStock = stockRepo.findById(stockUserKey).orElse(null);
        assertNotNull(testStock);
        testStock = stockRepo.findById(stockUserKey).orElse(null);
        assertNotNull(testStock);
        assertEquals(10, testStock.getCount());
        stockUserKey.setStockId("IBM");
        testStock = stockRepo.findById(stockUserKey).orElse(null);
        assertNotNull(testStock);
        assertEquals(0, testStock.getCount());

    }

}