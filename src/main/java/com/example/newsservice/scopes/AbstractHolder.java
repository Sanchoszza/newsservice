package com.example.newsservice.scopes;

import lombok.extern.slf4j.Slf4j;

import java.util.UUID;

@Slf4j
public abstract class AbstractHolder implements IdHolder{

    private final UUID requestId;

    protected AbstractHolder() {
        this.requestId = UUID.randomUUID();
    }

    @Override
    public void logId() {
        log.info("{} is: {}", holderType(), requestId);
    }

    abstract String holderType();
}
