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
import com.badgermolemining.cryptopayouttracker.cryptopayouttracker.model.UnixTimestamp.EpochStartEndDay;
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

            String date = EpochDateConverter.convertEpochToDate(
                            Long.valueOf(ethereumTransactionDetailsList.get(i).getTimeStamp()), Constants.MONTH_DAY_YEAR);
            BigDecimal marketPrice = getCryptocurrencyPriceByFairMarketValue(
                            Constants.ETHEREUM, Long.valueOf(ethereumTransactionDetailsList.get(i).getTimeStamp()));
            String amountAsString = ethereumTransactionDetailsList.get(i).getValue();
            BigDecimal amount = new BigDecimal(amountAsString).divide(Constants.ETH_OFFSET);
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

            String date = EpochDateConverter.convertEpochToDate(
                            Long.valueOf(chiaTransactionDetailsList.get(i).getTimestamp()), Constants.MONTH_DAY_YEAR);
            BigDecimal marketPrice = getCryptocurrencyPriceByFairMarketValue(
                            Constants.CHIA, Long.valueOf(chiaTransactionDetailsList.get(i).getTimestamp()));
            String amountAsString = chiaTransactionDetailsList.get(i).getAmount();
            BigDecimal amount = new BigDecimal(amountAsString).divide(Constants.XCH_OFFSET);
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

    private BigDecimal getCryptocurrencyPriceByFairMarketValue(String id, long timestamp) {

        EpochStartEndDay epochStartEndDay = getEpochStartEndDay(timestamp);
        ResponseEntity<CoinGeckoPriceHistoryResponse> response = 
            coinGeckoDao.getCoinPriceHistory(id, epochStartEndDay.getStartDayEpoch(), 
                                                            epochStartEndDay.getEndDayEpoch());
        String fairMarketValue = getFairMarketValue(response.getBody());
        return new BigDecimal(fairMarketValue);
    }

    private EpochStartEndDay getEpochStartEndDay(long timestamp) {
        return EpochDateConverter.getEpochStartEndDay(timestamp);
    }

    private String getFairMarketValue(CoinGeckoPriceHistoryResponse coinGeckoPriceHistoryTimestampResponse) {
        return FairMarketValueCalculator.getFairMarketValue(coinGeckoPriceHistoryTimestampResponse);
    }
    
}
