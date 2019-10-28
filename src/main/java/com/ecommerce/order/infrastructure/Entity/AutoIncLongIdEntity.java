package com.ecommerce.order.infrastructure.Entity;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

/**
 * ID use auto increase mechanism
 */
@MappedSuperclass
public abstract class AutoIncLongIdEntity {
    private static final long serialVersionUID = 2541587132584905304L;
    private Long id;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public int hashCode() {
        Long id = getId();
        return id == null ? 0 : id.hashCode();
    }
}
