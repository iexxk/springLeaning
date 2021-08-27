package com.exxk.testdemo;

import ch.qos.logback.classic.encoder.PatternLayoutEncoder;

public class CustomizePatternLayoutEncoder extends PatternLayoutEncoder {

    protected boolean useBase64Regex = true;
    protected int maxLength = 2048;//单条消息的最大长度，主要是message

    @Override
    public void start() {
        super.start();
    }

    @Override
    public String getPattern() {
        return super.getPattern();
    }


    public int getMaxLength() {
        return maxLength;
    }

    public void setMaxLength(int maxLength) {
        this.maxLength = maxLength;
    }
}
