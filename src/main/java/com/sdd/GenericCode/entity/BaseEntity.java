package com.sdd.GenericCode.entity;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;

@Getter
@Setter
@MappedSuperclass
public class BaseEntity implements Serializable {
    @Id
    @GeneratedValue(generator = "custom-uuid")
    @GenericGenerator(
            name = "custom-uuid",
            strategy = "com.sdd.GenericCode.util.CustomUUIDGenerator"
    )
    @Column(name = "id", updatable = false, nullable = false)
    private String id;
}
