package com.sdd.GenericCode.util;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.function.Supplier;

@AllArgsConstructor
@Getter
public class BusinessException extends RuntimeException {

    private final int errorCode;
}
