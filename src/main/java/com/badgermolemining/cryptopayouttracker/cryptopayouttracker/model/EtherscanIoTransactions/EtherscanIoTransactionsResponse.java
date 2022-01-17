package com.badgermolemining.cryptopayouttracker.cryptopayouttracker.model.EtherscanIoTransactions;

import java.util.List;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class EtherscanIoTransactionsResponse {
    
    private String message;
    private List<EthTransactionDetails> result;

}
