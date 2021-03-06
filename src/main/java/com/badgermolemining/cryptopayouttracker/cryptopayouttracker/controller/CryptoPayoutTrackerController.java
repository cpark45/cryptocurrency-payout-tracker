package com.badgermolemining.cryptopayouttracker.cryptopayouttracker.controller;

import com.badgermolemining.cryptopayouttracker.cryptopayouttracker.model.CoinGeckoPingResponse;
import com.badgermolemining.cryptopayouttracker.cryptopayouttracker.model.CryptocurrencyTransactionsResponse;
import com.badgermolemining.cryptopayouttracker.cryptopayouttracker.service.CryptoPayoutTrackerService;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
public class CryptoPayoutTrackerController {

	private final CryptoPayoutTrackerService cryptoPayoutTrackerService;

	@GetMapping("/ping")
	public String ping() {

		CoinGeckoPingResponse response = cryptoPayoutTrackerService.sendPing();

		return response.toString();
	}

	@GetMapping("/ethereum/transactions")
	public CryptocurrencyTransactionsResponse ethereumTransactions(@RequestParam String walletAddress, @RequestParam String apiKey) {

		CryptocurrencyTransactionsResponse cryptocurrencyTransactionsResponse = cryptoPayoutTrackerService.getEthereumTransactions(walletAddress, apiKey);

		return cryptocurrencyTransactionsResponse;
	}

	@GetMapping("/chia/transactions")
	public CryptocurrencyTransactionsResponse chiaTransactions(@RequestParam String walletAddress) {

		CryptocurrencyTransactionsResponse cryptocurrencyTransactionsResponse = cryptoPayoutTrackerService.getChiaTransactions(walletAddress);

		return cryptocurrencyTransactionsResponse;
	}
}