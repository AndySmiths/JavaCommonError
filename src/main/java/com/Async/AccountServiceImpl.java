package com.Async;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.CompletableFuture;

@Slf4j
public class AccountServiceImpl implements AccountService {
    @Override
    public CompletableFuture<Void> add(int account, int amount) {
        log.info("account:{}, amount:{}", account, amount);
        return null;
    }
}
