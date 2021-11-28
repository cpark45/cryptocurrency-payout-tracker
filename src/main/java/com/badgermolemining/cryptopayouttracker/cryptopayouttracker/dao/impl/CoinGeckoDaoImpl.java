package com.badgermolemining.cryptopayouttracker.cryptopayouttracker.dao.impl;

import com.badgermolemining.cryptopayouttracker.cryptopayouttracker.dao.CoinGeckoDao;
import com.badgermolemining.cryptopayouttracker.cryptopayouttracker.model.CoinGeckoPingResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class CoinGeckoDaoImpl implements CoinGeckoDao {

    @Value("${coingecko.ping.url}")
    private String coinGeckoPingUrl;

    private final WebClient coinGeckoAuthClient;
    
    public ResponseEntity<CoinGeckoPingResponse> sendPing() {

        ResponseEntity<CoinGeckoPingResponse> response = coinGeckoAuthClient
            .get()
            .uri(coinGeckoPingUrl)
            .header("accept", "application/json")
            .retrieve()
            .toEntity(CoinGeckoPingResponse.class)
            .block();

        return response;

    }
}
