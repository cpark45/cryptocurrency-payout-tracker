package com.badgermolemining.cryptopayouttracker.cryptopayouttracker.dao.impl;

import com.badgermolemining.cryptopayouttracker.cryptopayouttracker.constants.Constants;
import com.badgermolemining.cryptopayouttracker.cryptopayouttracker.dao.CoinGeckoDao;
import com.badgermolemining.cryptopayouttracker.cryptopayouttracker.model.CoinGeckoPingResponse;
import com.badgermolemining.cryptopayouttracker.cryptopayouttracker.model.CoinGeckoPriceHistory.CoinGeckoPriceHistoryResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class CoinGeckoDaoImpl implements CoinGeckoDao {

    @Value("${coingecko.ping.url}")
    private String coinGeckoPingUrl;

    @Value("${coingecko.coin.url}")
    private String coinGeckoCoinUrl;

    @Value("${coingecko.history.path}")
    private String historyPath;

    private final WebClient webClient;
    
    public ResponseEntity<CoinGeckoPingResponse> sendPing() {

        ResponseEntity<CoinGeckoPingResponse> response = webClient
            .get()
            .uri(coinGeckoPingUrl)
            .header("accept", "application/json")
            .retrieve()
            .toEntity(CoinGeckoPingResponse.class)
            .block();

        return response;
    }

    /**
     * Coingecko API limits 50 calls/minute
     */
    public ResponseEntity<CoinGeckoPriceHistoryResponse> getCoinPriceHistoryByDate(String id, String date) {

        try { // Adding code execution delay to prevent hitting API limit
            Thread.sleep(1200);
        }
        catch (InterruptedException exception) {
            exception.printStackTrace();
        }

        String coinGeckoCoinHistoryUrl = String.format("%s/%s/%s", coinGeckoCoinUrl, id, historyPath);
        String parameters = String.format("?%s=%s&%s=%s", Constants.DATE_PARAMETER, date, 
                                            Constants.LOCALIZATION_PARAMETER, Constants.LOCALIZATION_VALUE);
        String coinGeckoCoinHistoryUri = String.format("%s%s", coinGeckoCoinHistoryUrl, parameters);

        ResponseEntity<CoinGeckoPriceHistoryResponse> response = webClient
            .get()
            .uri(coinGeckoCoinHistoryUri)
            .header("accept", "application/json")
            .retrieve()
            .toEntity(CoinGeckoPriceHistoryResponse.class)
            .block();

        return response;
    }
}
