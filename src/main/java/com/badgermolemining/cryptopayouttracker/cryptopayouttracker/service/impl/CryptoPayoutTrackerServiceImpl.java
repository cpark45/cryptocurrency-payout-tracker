package com.badgermolemining.cryptopayouttracker.cryptopayouttracker.service.impl;

import java.util.ArrayList;
import java.util.List;

import com.badgermolemining.cryptopayouttracker.cryptopayouttracker.dao.CoinGeckoDao;
import com.badgermolemining.cryptopayouttracker.cryptopayouttracker.dao.EtherscanIoDao;
import com.badgermolemining.cryptopayouttracker.cryptopayouttracker.dao.XchscanDao;
import com.badgermolemining.cryptopayouttracker.cryptopayouttracker.model.CoinGeckoPingResponse;
import com.badgermolemining.cryptopayouttracker.cryptopayouttracker.model.CryptocurrencyTransactionsResponse;
import com.badgermolemining.cryptopayouttracker.cryptopayouttracker.model.EtherscanIoTransactions.EthTransactionDetails;
import com.badgermolemining.cryptopayouttracker.cryptopayouttracker.model.EtherscanIoTransactions.EtherscanIoTransactionsResponse;
import com.badgermolemining.cryptopayouttracker.cryptopayouttracker.model.XchscanTransactions.XchscanTransactionDetails;
import com.badgermolemining.cryptopayouttracker.cryptopayouttracker.model.XchscanTransactions.XchscanTransactionsResponse;
import com.badgermolemining.cryptopayouttracker.cryptopayouttracker.service.CryptoPayoutTrackerService;
import com.badgermolemining.cryptopayouttracker.cryptopayouttracker.util.CryptocurrencyTransactionsProcessor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class CryptoPayoutTrackerServiceImpl implements CryptoPayoutTrackerService {

    private final CoinGeckoDao coinGeckoDao;
    private final EtherscanIoDao etherscanIoDao;
    private final XchscanDao xchscanDao;
    private final CryptocurrencyTransactionsProcessor cryptocurrencyTransactionsProcessor;
    
    public CoinGeckoPingResponse sendPing() {
        return coinGeckoDao.sendPing().getBody();
    }

    public CryptocurrencyTransactionsResponse getEthereumTransactions(String walletAddress, String apiKey) {

        ResponseEntity<EtherscanIoTransactionsResponse> response = etherscanIoDao.getEthereumTransactions(walletAddress, apiKey);
        List<EthTransactionDetails> ethereumTransactionDetailsList = response.getBody().getResult();

        return cryptocurrencyTransactionsProcessor.processEthereumTransactions(ethereumTransactionDetailsList);
    }

    public CryptocurrencyTransactionsResponse getChiaTransactions(String walletAddress) {

        List<ResponseEntity<XchscanTransactionsResponse>> response = xchscanDao.getChiaTransactions(walletAddress);
        List<XchscanTransactionDetails> chiaTransactionDetailsList = new ArrayList<XchscanTransactionDetails>();

        for (int i = 0; i < response.size(); i++) {
            chiaTransactionDetailsList.addAll(response.get(i).getBody().getTxns());
        }

        return cryptocurrencyTransactionsProcessor.processChiaTransactions(chiaTransactionDetailsList);

    }
}
