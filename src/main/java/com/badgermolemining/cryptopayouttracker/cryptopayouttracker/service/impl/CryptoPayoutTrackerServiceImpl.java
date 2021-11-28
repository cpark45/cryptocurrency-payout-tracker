package com.badgermolemining.cryptopayouttracker.cryptopayouttracker.service.impl;

import com.badgermolemining.cryptopayouttracker.cryptopayouttracker.dao.CoinGeckoDao;
import com.badgermolemining.cryptopayouttracker.cryptopayouttracker.model.CoinGeckoPingResponse;
import com.badgermolemining.cryptopayouttracker.cryptopayouttracker.service.CryptoPayoutTrackerService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class CryptoPayoutTrackerServiceImpl implements CryptoPayoutTrackerService {

    private final CoinGeckoDao coinGeckoDao;
    
    public ResponseEntity<CoinGeckoPingResponse> sendPing() {
        return coinGeckoDao.sendPing();
    }
}
