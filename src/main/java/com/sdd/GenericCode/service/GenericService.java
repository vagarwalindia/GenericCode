package com.sdd.GenericCode.service;

import com.sdd.GenericCode.entity.BaseEntity;
import com.sdd.GenericCode.vo.filter.BaseFilter;
import com.sdd.GenericCode.spec.BaseSpec;
import com.sdd.GenericCode.vo.BaseVO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.io.Serializable;

public interface GenericService<V extends BaseVO, F extends BaseFilter, S extends BaseSpec, T extends BaseEntity, ID extends Serializable> {

    V get(ID id);
    V addUpdate(V v);
    boolean delete(ID id);
    Page<V> getList(Pageable page, F f);
    T getTById(ID id) throws Exception;
    Page<T> getEntityPage(Pageable page, F f);

}
