package com.badgermolemining.cryptopayouttracker.cryptopayouttracker.util;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

import com.badgermolemining.cryptopayouttracker.cryptopayouttracker.model.CoinGeckoPriceHistory.CoinGeckoPriceHistoryResponse;

public class FairMarketValueCalculator {

    public static String getFairMarketValue(CoinGeckoPriceHistoryResponse coinGeckoPriceHistoryTimestampResponse) {

        List<List<String>> prices = coinGeckoPriceHistoryTimestampResponse.getPrices();

        if (prices.isEmpty()) {
            return BigDecimal.ZERO.toString();
        }

        BigDecimal totalValue = BigDecimal.ZERO;

        for (int i = 0; i < prices.size(); i++) {
            totalValue = totalValue.add(new BigDecimal(prices.get(i).get(1)));
        }

        return totalValue.divide(new BigDecimal(prices.size()), 2, RoundingMode.HALF_EVEN).toString();
    }
    
}
