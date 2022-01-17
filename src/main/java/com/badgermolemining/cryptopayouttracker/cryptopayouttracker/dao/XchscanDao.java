package com.badgermolemining.cryptopayouttracker.cryptopayouttracker.dao;

import java.util.List;

import com.badgermolemining.cryptopayouttracker.cryptopayouttracker.model.XchscanTransactions.XchscanTransactionsResponse;

import org.springframework.http.ResponseEntity;

public interface XchscanDao {
    
    List<ResponseEntity<XchscanTransactionsResponse>> getChiaTransactions(String walletAddress);
}
