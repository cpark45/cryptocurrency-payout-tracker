package com.badgermolemining.cryptopayouttracker.cryptopayouttracker.dao.impl;

import com.badgermolemining.cryptopayouttracker.cryptopayouttracker.constants.Constants;
import com.badgermolemining.cryptopayouttracker.cryptopayouttracker.dao.EtherscanIoDao;
import com.badgermolemining.cryptopayouttracker.cryptopayouttracker.model.EtherscanIoTransactions.EtherscanIoTransactionsResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class EtherscanIoDaoImpl implements EtherscanIoDao {

    @Value("${etherscan.io.transactions.url}")
    private String etherscanIoTransactionsUrl;

    private final WebClient webClient;

    /**
     * Etherscan API limits 5 calls/second, not an issue right now as one transaction call will return up to 10000 transactions
     */
    public ResponseEntity<EtherscanIoTransactionsResponse> getEthereumTransactions(String walletAddress, String apiKey) {

        String parameters = String.format("?%s=%s&%s=%s&%s=%s&%s=%s&%s=%s&%s=%s&%s=%s", 
                                            Constants.MODULE_PARAMETER, Constants.MODULE_VALUE,
                                            Constants.ACTION_PARAMETER, Constants.ACTION_VALUE,
                                            Constants.ADDRESS_PARAMETER, walletAddress,
                                            Constants.START_BLOCK_PARAMETER, Constants.START_BLOCK_VALUE,
                                            Constants.END_BLOCK_PARAMETER, Constants.END_BLOCK_VALUE,
                                            Constants.SORT_PARAMETER, Constants.SORT_VALUE,
                                            Constants.API_KEY_PARAMETER, apiKey);
        String etherscanIoTransactionsUri = String.format("%s%s", etherscanIoTransactionsUrl, parameters);

        ResponseEntity<EtherscanIoTransactionsResponse> response = webClient
            .get()
            .uri(etherscanIoTransactionsUri)
            .retrieve()
            .toEntity(EtherscanIoTransactionsResponse.class)
            .block();

        return response;
    }
}
