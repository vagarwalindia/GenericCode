package com.sdd.GenericCode.util.vo;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ErrorDetails implements Serializable {

    @JsonProperty("responseStatus")
    private String responseStatus;
    @JsonProperty("responseCode")
    private int errorCode;
    @JsonProperty("responseMsg")
    private String responseMessage;
}
