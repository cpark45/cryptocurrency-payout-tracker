package com.badgermolemining.cryptopayouttracker.cryptopayouttracker.model.XchscanTransactions;

import java.util.List;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class XchscanTransactionsResponse {
    
    private List<XchscanTransactionDetails> txns;
    
}
