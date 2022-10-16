package com.rttbanking.stockmanagement.dto;


import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table (name = "tbl_stock")
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class Stock {

    @Getter @Setter @NoArgsConstructor @AllArgsConstructor
    @Embeddable @EqualsAndHashCode
    public static class StockUserKey implements Serializable{
        private String stockId;
        private long userId;
    }

    @Id @Embedded
    private StockUserKey stockUserKey;

    int count;
}
