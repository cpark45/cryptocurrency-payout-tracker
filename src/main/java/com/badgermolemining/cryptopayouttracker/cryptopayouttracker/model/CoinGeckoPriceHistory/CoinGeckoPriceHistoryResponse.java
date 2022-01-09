package com.badgermolemining.cryptopayouttracker.cryptopayouttracker.model.CoinGeckoPriceHistory;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CoinGeckoPriceHistoryResponse {

    private String id;
    private MarketData market_data;
    
}
