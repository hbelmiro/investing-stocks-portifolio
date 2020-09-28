package com.hbelmiro;

import java.math.BigDecimal;

public class Position {

    private final String stockSymbol;

    private final BigDecimal shares;

    private final BigDecimal averagePrice;

    public Position(String stockSymbol, BigDecimal shares, BigDecimal averagePrice) {
        this.stockSymbol = stockSymbol;
        this.shares = shares;
        this.averagePrice = averagePrice;
    }

    public BigDecimal getShares() {
        return shares;
    }

    public BigDecimal getAveragePrice() {
        return averagePrice;
    }

    public String getStockSymbol() {
        return stockSymbol;
    }

}
