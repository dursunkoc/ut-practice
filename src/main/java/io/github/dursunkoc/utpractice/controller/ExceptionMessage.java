package io.github.dursunkoc.utpractice.controller;

import lombok.Builder;

@Builder
public record ExceptionMessage(boolean error, String message, String trxId) {}
