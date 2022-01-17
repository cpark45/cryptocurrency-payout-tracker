package com.badgermolemining.cryptopayouttracker.cryptopayouttracker.model;

import java.util.List;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CryptocurrencyTransactionsResponse {

    private List<CryptocurrencyTransactionDetails> transactions;
    
}
