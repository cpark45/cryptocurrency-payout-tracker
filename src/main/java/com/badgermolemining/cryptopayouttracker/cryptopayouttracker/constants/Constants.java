package com.badgermolemining.cryptopayouttracker.cryptopayouttracker.constants;

import java.math.BigDecimal;

public class Constants {

    // Cryptocurrencies
    public static final String ETHEREUM = "ethereum";
    public static final String CHIA = "chia";

    // CoinGecko Fields
    public static final String DATE_PARAMETER = "date";
    public static final String LOCALIZATION_PARAMETER = "localization";
    public static final String LOCALIZATION_VALUE = "false";
    public static final String VS_CURRENCY_PARAMETER = "vs_currency";
    public static final String VS_CURRENCY_VALUE = "usd";
    public static final String FROM_PARAMETER = "from";
    public static final String TO_PARAMETER = "to";

    // Etherscan.io & Xchscan Fields
    public static final String MODULE_PARAMETER = "module";
    public static final String MODULE_VALUE = "account";
    public static final String ACTION_PARAMETER = "action";
    public static final String ACTION_VALUE = "txlist";
    public static final String ADDRESS_PARAMETER = "address";
    public static final String START_BLOCK_PARAMETER = "startblock";
    public static final String START_BLOCK_VALUE = "0";
    public static final String END_BLOCK_PARAMETER = "endblock";
    public static final String END_BLOCK_VALUE = "999999999";
    public static final String SORT_PARAMETER = "sort";
    public static final String SORT_VALUE = "asc";
    public static final String API_KEY_PARAMETER = "apikey";
    public static final String LIMIT_PARAMETER = "limit";
    public static final Integer LIMIT_VALUE = 25;
    public static final String OFFSET_PARAMETER = "offset";
    public static final BigDecimal ETH_OFFSET = new BigDecimal("1000000000000000000");
    public static final BigDecimal XCH_OFFSET = new BigDecimal("1000000000000");

    // Date Constants
    public static final String MONTH_DAY_YEAR = "MM-dd-yyyy";
    public static final String DAY_MONTH_YEAR = "dd-MM-yyyy";
    public static final String UTC = "UTC";
    public static final int ZERO = 0;
    public static final int TWENTY_THREE = 23;
    public static final int FIFTY_NINE = 59;
    public static final int ONE_THOUSAND = 1000;
}
