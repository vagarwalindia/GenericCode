package com.sdd.GenericCode.spec;

import com.sdd.GenericCode.entity.BaseEntity;
import com.sdd.GenericCode.vo.BaseVO;
import com.sdd.GenericCode.vo.filter.BaseFilter;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;

import java.util.Comparator;
import java.util.function.Predicate;

public abstract class BaseSpec<T extends BaseEntity, F extends BaseFilter, V extends BaseVO> {

    public abstract Specification<T> filter(F f, Sort sort);
    public Predicate<V> filter(F f){
        Predicate<V> predicate = null ;
        return predicate;
    }

    public Comparator<V> sort(Sort sort) {
        return null;
    }
}
