package com.sdd.GenericCode.service.impl;

import com.sdd.GenericCode.entity.BaseEntity;
import com.sdd.GenericCode.util.CustomRepository;
import com.sdd.GenericCode.util.BusinessException;
import com.sdd.GenericCode.util.MessageConstant;
import com.sdd.GenericCode.vo.filter.BaseFilter;
import com.sdd.GenericCode.service.GenericService;
import com.sdd.GenericCode.spec.BaseSpec;
import com.sdd.GenericCode.vo.BaseVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public abstract class AbstractService<V extends BaseVO, F extends BaseFilter, T extends BaseEntity, S extends BaseSpec, ID extends Serializable> implements GenericService<V,F,S,T,ID> {

    @Autowired
    private CustomRepository<T, ID> repository;

    @Override
    public V get(ID id) {
        T t = this.getTById(id);
        return this.convertToVO(t);
    }

    @Override
    public V addUpdate(V v) {
        this.validate(v);
        T t = this.convertToEntity(v);
        return this.convertToVO((T) repository.save(t));
    }

    @Override
    public boolean delete(ID id) {
        repository.delete(this.getTById(id));
        return true;
    }

    @Override
    public Page<V> getList(Pageable page, F f) {
        Page<T> tPage = this.getEntityPage(page, f);
            Page<V> vPage = this.getPageVO(tPage);
            return vPage;
    }

    private Page<V> getPageVO(Page<T> tPage) {
        List<V> vList = new ArrayList<>();
        for (T t: tPage.getContent()) {
            V v = this.convertToVO(t);
            vList.add(v);
        }
        Page<V> page = new PageImpl<>(vList,tPage.getPageable(),tPage.getTotalElements());
        return page;
    }

    private boolean checkIfFilterAndSortManually(Pageable page, F f) {
        S s = this.filter();
        return s.sort(page.getSort())!=null || s.filter(f)!=null;
    }

    public S filter() {
        return null;
    }

    @Override
    public T getTById(ID id)  {
        Optional<T> t;
        t = repository.findById(id);
        if(t.isPresent())
            return t.get();
        throw new BusinessException(MessageConstant.INVALID_ID);
    }

    @Override
    public Page<T> getEntityPage(Pageable page, F f) {
        Pageable pageableWithoutSort ;
        if(this.checkIfFilterAndSortManually(page, f)){
            pageableWithoutSort = PageRequest.of(0,Integer.MAX_VALUE);
        }
        else{
            pageableWithoutSort = PageRequest.of(page.getPageNumber(),page.getPageSize());
        }
        S s = this.filter();
        Page<T> tPage = repository.findAll(s.filter(f,page.getSort()),pageableWithoutSort);
        if(tPage!=null && tPage.hasContent())
            return tPage;
        throw new BusinessException(MessageConstant.PAGINATION_NO_RECORD_FOUND);

    }

    public abstract V convertToVO(T t);

    public abstract T convertToEntity(V v);

    public abstract T setAdditionalDataToEntity(T t,V v);

    public void validateAdd(V v){};

    public void validateUpdate(V v){};
    public void validate(V v){
        if (v.getId()==null || v.getId().isBlank()){
            validateAdd(v);
        }
        else{
            validateUpdate(v);
        }
    }

}
