package com.badgermolemining.cryptopayouttracker.cryptopayouttracker.controller;

import com.badgermolemining.cryptopayouttracker.cryptopayouttracker.model.CoinGeckoPingResponse;
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

}