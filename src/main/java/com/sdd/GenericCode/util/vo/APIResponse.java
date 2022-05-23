package com.sdd.GenericCode.util.vo;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class APIResponse implements Serializable {

    private Object object;
    private ErrorDetails error;
}
