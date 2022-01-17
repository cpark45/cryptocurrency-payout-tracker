package com.badgermolemining.cryptopayouttracker.cryptopayouttracker.model;

import java.math.BigDecimal;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CryptocurrencyTransactionDetails {

    private String date;
    private String amount;
    private BigDecimal marketPrice;
    private BigDecimal value;
    
}
