package com.badgermolemining.cryptopayouttracker.cryptopayouttracker.service;

import com.badgermolemining.cryptopayouttracker.cryptopayouttracker.model.CoinGeckoPingResponse;
import com.badgermolemining.cryptopayouttracker.cryptopayouttracker.model.CoinGeckoPriceHistory.CoinGeckoPriceHistoryResponse;
import com.badgermolemining.cryptopayouttracker.cryptopayouttracker.model.EtherscanIoTransactions.EtherscanIoTransactionsResponse;

import org.springframework.http.ResponseEntity;

public interface CryptoPayoutTrackerService {
    
    ResponseEntity<CoinGeckoPingResponse> sendPing();
    ResponseEntity<CoinGeckoPriceHistoryResponse> getCoinPriceHistoryByDate();
    ResponseEntity<EtherscanIoTransactionsResponse> getEthereumTransactions();
}
