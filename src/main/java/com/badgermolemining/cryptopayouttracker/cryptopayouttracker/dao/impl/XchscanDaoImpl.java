package com.badgermolemining.cryptopayouttracker.cryptopayouttracker.dao.impl;

import java.util.ArrayList;
import java.util.List;

import com.badgermolemining.cryptopayouttracker.cryptopayouttracker.constants.Constants;
import com.badgermolemining.cryptopayouttracker.cryptopayouttracker.dao.XchscanDao;
import com.badgermolemining.cryptopayouttracker.cryptopayouttracker.model.XchscanTransactions.XchscanTransactionsResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class XchscanDaoImpl implements XchscanDao {

    @Value("${xchscan.transactions.url}")
    private String xchscanTransactionUrl;

    private final WebClient webClient;

    /**
     * Xchscan API has no explicit limits, need to make a call for every 25 transactions
     */
    public List<ResponseEntity<XchscanTransactionsResponse>> getChiaTransactions(String walletAddress) {

        ResponseEntity<XchscanTransactionsResponse> response;
        List<ResponseEntity<XchscanTransactionsResponse>> responses = new ArrayList<ResponseEntity<XchscanTransactionsResponse>>();
        Integer offset = 0;

        do {
            String parameters = String.format("?%s=%s&%s=%s&%s=%s", Constants.ADDRESS_PARAMETER, walletAddress, 
                                                Constants.LIMIT_PARAMETER, Constants.LIMIT_VALUE, 
                                                Constants.OFFSET_PARAMETER, offset);

            String xchScanTransactionsUri = String.format("%s%s", xchscanTransactionUrl, parameters);

            response = webClient
                .get()
                .uri(xchScanTransactionsUri)
                .retrieve()
                .toEntity(XchscanTransactionsResponse.class)
            .block();

            if (response.getBody().getTxns().size() > 0) {
                responses.add(response);
            }

            offset = offset + Constants.LIMIT_VALUE;

        } while (response.getBody().getTxns().size() > 0);

        return responses;
    }
    
}
