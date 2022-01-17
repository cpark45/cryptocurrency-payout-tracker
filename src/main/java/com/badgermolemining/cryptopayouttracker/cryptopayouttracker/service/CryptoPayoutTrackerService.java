package com.badgermolemining.cryptopayouttracker.cryptopayouttracker.service;

import com.badgermolemining.cryptopayouttracker.cryptopayouttracker.model.CoinGeckoPingResponse;
import com.badgermolemining.cryptopayouttracker.cryptopayouttracker.model.CryptocurrencyTransactionsResponse;

public interface CryptoPayoutTrackerService {
    
    CoinGeckoPingResponse sendPing();
    CryptocurrencyTransactionsResponse getEthereumTransactions(String walletAddress, String apiKey);
    CryptocurrencyTransactionsResponse getChiaTransactions(String walletAddress);
}
