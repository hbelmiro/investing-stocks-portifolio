package com.hbelmiro;

import org.eclipse.microprofile.rest.client.inject.RestClient;

import javax.enterprise.context.ApplicationScoped;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

@ApplicationScoped
public class Portfolio {

    private final StocksService stocksService;

    public Portfolio(@RestClient StocksService stocksService) {
        this.stocksService = stocksService;
    }

    private List<Position> getPositions() {
        return List.of(
                new Position("aapl", new BigDecimal("5.88"), new BigDecimal("43.97")),
                new Position("amzn", new BigDecimal("0.145"), new BigDecimal("1664.84")),
                new Position("goog", new BigDecimal("0.2"), new BigDecimal("1133.63")),
                new Position("fb", new BigDecimal("1.39002847"), new BigDecimal("209.34")),
                new Position("voo", new BigDecimal("5.69556585"), new BigDecimal("286.80"))
        );
    }

    public BigDecimal getProfit() {
        BigDecimal totalPaid = getPositions().stream()
                                             .map(position -> position.getAveragePrice().multiply(position.getShares()))
                                             .reduce(BigDecimal::add)
                                             .orElse(BigDecimal.ZERO);

        BigDecimal actualValue = getPositions().stream()
                                               .map(position -> this.stocksService.getStock(position.getStockSymbol()).multiply(position.getShares()))
                                               .reduce(BigDecimal::add)
                                               .orElse(BigDecimal.ZERO);

        return actualValue.subtract(totalPaid).divide(totalPaid, RoundingMode.HALF_UP).multiply(BigDecimal.valueOf(100));
    }

}
