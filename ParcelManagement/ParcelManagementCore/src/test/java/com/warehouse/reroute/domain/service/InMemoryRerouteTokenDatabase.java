package com.warehouse.reroute.domain.service;

import com.warehouse.reroute.domain.model.RerouteToken;

import java.util.ArrayList;
import java.util.List;

public class InMemoryRerouteTokenDatabase {

    private final List<RerouteToken> rerouteTokenList = new ArrayList<>();


    public void insertRerouteToken(RerouteToken rerouteToken) {
        rerouteTokenList.add(rerouteToken);
    }

    public RerouteToken findRerouteTokenByTokenValue(Integer token) {
        return rerouteTokenList.stream().filter(rerouteToken -> rerouteToken.getToken().equals(token)).findAny().get();
    }
}
