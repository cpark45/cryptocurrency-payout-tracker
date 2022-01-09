package com.badgermolemining.cryptopayouttracker.cryptopayouttracker.dao;

import com.badgermolemining.cryptopayouttracker.cryptopayouttracker.model.EtherscanIoTransactions.EtherscanIoTransactionsResponse;

import org.springframework.http.ResponseEntity;

public interface EtherscanIoDao {

    ResponseEntity<EtherscanIoTransactionsResponse> getEthereumTransactions(String walletAddress);

}
