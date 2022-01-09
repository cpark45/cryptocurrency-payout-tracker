package com.badgermolemining.cryptopayouttracker.cryptopayouttracker.controller;

import com.badgermolemining.cryptopayouttracker.cryptopayouttracker.model.CoinGeckoPingResponse;
import com.badgermolemining.cryptopayouttracker.cryptopayouttracker.model.CoinGeckoPriceHistory.CoinGeckoPriceHistoryResponse;
import com.badgermolemining.cryptopayouttracker.cryptopayouttracker.model.EtherscanIoTransactions.EtherscanIoTransactionsResponse;
import com.badgermolemining.cryptopayouttracker.cryptopayouttracker.service.CryptoPayoutTrackerService;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
public class CryptoPayoutTrackerController {

	private final CryptoPayoutTrackerService cryptoPayoutTrackerService;

	@GetMapping("/ping")
	public String ping() {

		ResponseEntity<CoinGeckoPingResponse> response = cryptoPayoutTrackerService.sendPing();

		return response.getBody().toString();
	}

	@GetMapping("/coin/history")
	public String coinHistory() {

		ResponseEntity<CoinGeckoPriceHistoryResponse> response = cryptoPayoutTrackerService.getCoinPriceHistoryByDate();

		return response.getBody().getMarket_data().getCurrent_price().getUsd().toString();
	}

	@GetMapping("/ethereum/transactions")
	public String ethereumTransactions() {

		ResponseEntity<EtherscanIoTransactionsResponse> response = cryptoPayoutTrackerService.getEthereumTransactions();

		return response.getBody().getResult().toString();
	}

}