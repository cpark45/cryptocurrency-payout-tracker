package com.badgermolemining.cryptopayouttracker.cryptopayouttracker.service.impl;

import com.badgermolemining.cryptopayouttracker.cryptopayouttracker.dao.CoinGeckoDao;
import com.badgermolemining.cryptopayouttracker.cryptopayouttracker.dao.EtherscanIoDao;
import com.badgermolemining.cryptopayouttracker.cryptopayouttracker.model.CoinGeckoPingResponse;
import com.badgermolemining.cryptopayouttracker.cryptopayouttracker.model.CoinGeckoPriceHistory.CoinGeckoPriceHistoryResponse;
import com.badgermolemining.cryptopayouttracker.cryptopayouttracker.model.EtherscanIoTransactions.EtherscanIoTransactionsResponse;
import com.badgermolemining.cryptopayouttracker.cryptopayouttracker.service.CryptoPayoutTrackerService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class CryptoPayoutTrackerServiceImpl implements CryptoPayoutTrackerService {

    private final CoinGeckoDao coinGeckoDao;
    private final EtherscanIoDao etherscanIoDao;
    
    public ResponseEntity<CoinGeckoPingResponse> sendPing() {
        return coinGeckoDao.sendPing();
    }

    public ResponseEntity<CoinGeckoPriceHistoryResponse> getCoinPriceHistoryByDate() {
        return coinGeckoDao.getCoinPriceHistoryByDate("ethereum", "31-12-2021");
    }

    public ResponseEntity<EtherscanIoTransactionsResponse> getEthereumTransactions() {
        return etherscanIoDao.getEthereumTransactions("");
    }
}
