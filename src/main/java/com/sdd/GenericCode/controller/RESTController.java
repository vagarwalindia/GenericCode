package com.sdd.GenericCode.controller;


import com.sdd.GenericCode.entity.BaseEntity;
import com.sdd.GenericCode.service.GenericService;
import com.sdd.GenericCode.spec.BaseSpec;
import com.sdd.GenericCode.vo.BaseVO;
import com.sdd.GenericCode.vo.filter.BaseFilter;
import com.sdd.GenericCode.util.error.codes.MessageCode;
import com.sdd.GenericCode.util.keywords.Keywords;
import com.sdd.GenericCode.util.service.MessageByLocaleService;
import com.sdd.GenericCode.util.vo.APIResponse;
import com.sdd.GenericCode.util.vo.ErrorDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import springfox.documentation.annotations.ApiIgnore;

import javax.servlet.http.HttpServletRequest;
import java.io.Serializable;

public class RESTController<V extends BaseVO, F extends BaseFilter, S extends BaseSpec, T extends BaseEntity, ID extends Serializable> {

    public static final String CONTROLLERURL = "";
    public static final String ADDUPDATE_URL = "/addUpdate";
    public static final String DELETEBYID_URL = "/delete";
    public static final String GETLIST_URL = "/getList";
    public static final String GETBYID_URL = "/get";


    @Autowired
    public GenericService<V, F, S, T ,ID > service;

    @Autowired
    public MessageByLocaleService messageByLocaleService;

    @PostMapping(ADDUPDATE_URL)
    public ResponseEntity<APIResponse> addUpdate(@RequestBody V v, HttpServletRequest request){
        APIResponse response = new APIResponse();
        ErrorDetails error = new ErrorDetails();
        response.setObject(service.addUpdate(v));
        error.setErrorCode(MessageCode.SUCCESS);
        error.setResponseStatus(Keywords.RESPONSE_SUCCESS);
        error.setResponseMessage(messageByLocaleService.getMessage(String.valueOf(MessageCode.SUCCESS)));
        response.setError(error);
        return new ResponseEntity<APIResponse>(response, HttpStatus.OK);
    }

    @GetMapping(GETBYID_URL + "/{id}")
    public ResponseEntity<APIResponse> get(@PathVariable ID id, HttpServletRequest request){
        APIResponse response = new APIResponse();
        ErrorDetails error = new ErrorDetails();
        response.setObject(service.get(id));
        error.setErrorCode(MessageCode.SUCCESS);
        error.setResponseStatus(Keywords.RESPONSE_SUCCESS);
        error.setResponseMessage(messageByLocaleService.getMessage(String.valueOf(MessageCode.SUCCESS)));
        response.setError(error);
        return new ResponseEntity<APIResponse>(response, HttpStatus.OK);
    }

    @GetMapping(DELETEBYID_URL + "/{id}")
    public ResponseEntity<APIResponse> delete(@PathVariable ID id, HttpServletRequest request){
        APIResponse response = new APIResponse();
        ErrorDetails error = new ErrorDetails();
        response.setObject(service.delete(id));
        error.setErrorCode(MessageCode.SUCCESS);
        error.setResponseStatus(Keywords.RESPONSE_SUCCESS);
        error.setResponseMessage(messageByLocaleService.getMessage(String.valueOf(MessageCode.SUCCESS)));
        response.setError(error);
        return new ResponseEntity<APIResponse>(response, HttpStatus.OK);
    }

    @PostMapping(GETLIST_URL)
    public ResponseEntity<APIResponse> getList(@ApiIgnore Pageable pageRequest, @RequestBody (required = false) F f , HttpServletRequest request){
        APIResponse response = new APIResponse();
        ErrorDetails error = new ErrorDetails();
        response.setObject(service.getList(pageRequest,f));
        error.setErrorCode(MessageCode.SUCCESS);
        error.setResponseStatus(Keywords.RESPONSE_SUCCESS);
        error.setResponseMessage(messageByLocaleService.getMessage(String.valueOf(MessageCode.SUCCESS)));
        response.setError(error);
        return new ResponseEntity<APIResponse>(response, HttpStatus.OK);
    }



}
