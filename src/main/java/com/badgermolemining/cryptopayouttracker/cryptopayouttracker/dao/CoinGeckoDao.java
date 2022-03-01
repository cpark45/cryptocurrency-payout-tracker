package com.badgermolemining.cryptopayouttracker.cryptopayouttracker.dao;

import com.badgermolemining.cryptopayouttracker.cryptopayouttracker.model.CoinGeckoPingResponse;
import com.badgermolemining.cryptopayouttracker.cryptopayouttracker.model.CoinGeckoPriceHistory.CoinGeckoPriceHistoryTimestampResponse;

import org.springframework.http.ResponseEntity;

public interface CoinGeckoDao {
    ResponseEntity<CoinGeckoPingResponse> sendPing();
    ResponseEntity<CoinGeckoPriceHistoryTimestampResponse> getCoinPriceHistory(String id, String startTimestamp, String endTimestamp);
}
