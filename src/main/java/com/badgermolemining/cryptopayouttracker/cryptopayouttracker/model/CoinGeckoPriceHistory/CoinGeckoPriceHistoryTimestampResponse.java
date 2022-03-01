package com.badgermolemining.cryptopayouttracker.cryptopayouttracker.model.CoinGeckoPriceHistory;

import java.util.List;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CoinGeckoPriceHistoryTimestampResponse {

    private List<List<String>> prices;
    
}
