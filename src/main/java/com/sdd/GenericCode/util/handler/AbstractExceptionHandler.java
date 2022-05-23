package com.sdd.GenericCode.util.handler;

import lombok.Getter;
import lombok.Setter;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@Getter
@Setter
public class AbstractExceptionHandler extends ResponseEntityExceptionHandler {
    private ReloadableResourceBundleMessageSource resourceBundle;
}
