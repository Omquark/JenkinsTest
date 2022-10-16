package com.rttbanking.stockmanagement.controller;

import com.fasterxml.jackson.databind.util.JSONPObject;
import com.rttbanking.stockmanagement.dto.Stock;
import com.rttbanking.stockmanagement.service.StockService;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.json.Json;
import java.util.ArrayList;
import java.util.List;

@RestController
public class StockController {

    @Autowired
    StockService stockService;

    @CrossOrigin(origins = "http://localhost:3000")
    @PutMapping(value = "/stock/buy", consumes = "application/json")
    @ResponseBody
    public ResponseEntity<String> buyStock(@RequestBody Stock stock){
        if(stockService.buyStock(stock)){
            return ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON).body("Success");
        }
        else{
            return ResponseEntity.notFound().header("Failed").build();
        }
    }

    @CrossOrigin(origins = "http://localhost:3000")
    @PutMapping(value = "/stock/sell", consumes = "application/json")
    @ResponseBody
    public ResponseEntity<String> sellStock(@RequestBody Stock stock){
        if(stockService.sellStock(stock)){
            return ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON).body("Success");
        }
        else{
            return ResponseEntity.notFound().header("Failed").build();
        }
    }

    @CrossOrigin(origins = "http://localhost:3000")
    @GetMapping(value = "/stock/count/{stockId}")
    @ResponseBody
    public ResponseEntity<Integer> getStockCount(@PathVariable String stockId){
        return ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON).body(stockService.getStockCount(4, stockId));
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping(value = "/stock/view/all")
    @ResponseBody
    public ResponseEntity<List<Stock>> getAllStocks(){
        return ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON).body(stockService.getAllStocks());
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @PutMapping(value = "/stock/edit/{oldId}")
    @ResponseBody
    public ResponseEntity<String> setStockCount(@RequestBody Stock stock, @PathVariable String oldId){

        if(stockService.updateStock(stock, oldId)) {
            return ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON).header("Success").build();
        }
        return ResponseEntity.noContent().header("Failed").build();
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping(value = "/stock/user/{username}")
    @ResponseBody
    public ResponseEntity<List<Stock>> getStocksByUser(@RequestBody int userId){
        return ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON).body(new ArrayList<Stock>());
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping(value = "/stock/view/{stockId}/{userId}")
    @ResponseBody
    public ResponseEntity<Stock> getStockByStockId(@PathVariable String stockId, @PathVariable int userId){
        Stock stock = stockService.getStock(stockId, userId);
        if(stock == null){
            return ResponseEntity.noContent().header("No Stock").build();
        }
        return ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON).body(stock);
    }
}
