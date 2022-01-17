package com.badgermolemining.cryptopayouttracker.cryptopayouttracker.util;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import com.badgermolemining.cryptopayouttracker.cryptopayouttracker.constants.Constants;
import com.badgermolemining.cryptopayouttracker.cryptopayouttracker.dao.CoinGeckoDao;
import com.badgermolemining.cryptopayouttracker.cryptopayouttracker.model.CryptocurrencyTransactionDetails;
import com.badgermolemining.cryptopayouttracker.cryptopayouttracker.model.CryptocurrencyTransactionsResponse;
import com.badgermolemining.cryptopayouttracker.cryptopayouttracker.model.CoinGeckoPriceHistory.CoinGeckoPriceHistoryResponse;
import com.badgermolemining.cryptopayouttracker.cryptopayouttracker.model.EtherscanIoTransactions.EthTransactionDetails;
import com.badgermolemining.cryptopayouttracker.cryptopayouttracker.model.XchscanTransactions.XchscanTransactionDetails;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class CryptocurrencyTransactionsProcessor {

    private final CoinGeckoDao coinGeckoDao;

    public CryptocurrencyTransactionsResponse processEthereumTransactions(List<EthTransactionDetails> ethereumTransactionDetailsList) {

        CryptocurrencyTransactionsResponse cryptocurrencyTransactionsResponse = new CryptocurrencyTransactionsResponse();
        List<CryptocurrencyTransactionDetails> transactionDetailsList = new ArrayList<CryptocurrencyTransactionDetails>();

        for (int i = 0; i < ethereumTransactionDetailsList.size(); i++) {

            CryptocurrencyTransactionDetails cryptocurrencyTransactionDetails = new CryptocurrencyTransactionDetails();

            String date = EpochToDateConverter.convertEpochToDate(Long.valueOf(ethereumTransactionDetailsList.get(i).getTimeStamp()));
            BigDecimal marketPrice = getCryptocurrencyPriceByDate(Constants.ETHEREUM, date);
            String amountAsString = ethereumTransactionDetailsList.get(i).getValue();
            BigDecimal amount = new BigDecimal(amountAsString).divide(new BigDecimal("1000000000000000000"));
            BigDecimal value = amount.multiply(marketPrice);

            cryptocurrencyTransactionDetails.setDate(date);
            cryptocurrencyTransactionDetails.setAmount(amount.toString());
            cryptocurrencyTransactionDetails.setMarketPrice(marketPrice);
            cryptocurrencyTransactionDetails.setValue(value);

            transactionDetailsList.add(cryptocurrencyTransactionDetails);
        }

        cryptocurrencyTransactionsResponse.setTransactions(transactionDetailsList);

        return cryptocurrencyTransactionsResponse;
    }

    public CryptocurrencyTransactionsResponse processChiaTransactions(List<XchscanTransactionDetails> chiaTransactionDetailsList) {

        CryptocurrencyTransactionsResponse cryptocurrencyTransactionsResponse = new CryptocurrencyTransactionsResponse();
        List<CryptocurrencyTransactionDetails> transactionDetailsList = new ArrayList<CryptocurrencyTransactionDetails>();


        for (int i = (chiaTransactionDetailsList.size() - 1); i >= 0 ; i--) {

            CryptocurrencyTransactionDetails cryptocurrencyTransactionDetails = new CryptocurrencyTransactionDetails();

            String date = EpochToDateConverter.convertEpochToDate(Long.valueOf(chiaTransactionDetailsList.get(i).getTimestamp()));
            BigDecimal marketPrice = getCryptocurrencyPriceByDate(Constants.CHIA, date);
            String amountAsString = chiaTransactionDetailsList.get(i).getAmount();
            BigDecimal amount = new BigDecimal(amountAsString).divide(new BigDecimal("1000000000000"));
            BigDecimal value = amount.multiply(marketPrice);

            cryptocurrencyTransactionDetails.setDate(date);
            cryptocurrencyTransactionDetails.setAmount(amount.toString());
            cryptocurrencyTransactionDetails.setMarketPrice(marketPrice);
            cryptocurrencyTransactionDetails.setValue(value);

            transactionDetailsList.add(cryptocurrencyTransactionDetails);
        }

        cryptocurrencyTransactionsResponse.setTransactions(transactionDetailsList);

        return cryptocurrencyTransactionsResponse;
    }

    private BigDecimal getCryptocurrencyPriceByDate(String id, String date) {

        ResponseEntity<CoinGeckoPriceHistoryResponse> response = coinGeckoDao.getCoinPriceHistoryByDate(id, date);
        return response.getBody().getMarket_data().getCurrent_price().getUsd();
    }
    
}
