/*
 * Copyright (C) 2019 Baidu, Inc. All Rights Reserved.
 */
package com.ecommerce.order.infrastructure.Entity;

import java.util.Date;

import javax.persistence.MappedSuperclass;

/**
 * @author Xu Zhijian, <xuzhijian@baidu.com>
 * @since 2019-10-25
 */
@MappedSuperclass
public abstract class AutoIncLongIdTraceableEntity extends AutoIncLongIdEntity {
    private static final long serialVersionUID = 7561429181727176028L;
    private Date createTime;
    private Date updateTime;
    //    private Long creator;
    //    private Long updater;

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    //    public Long getCreator() {
    //        return creator;
    //    }
    //
    //    public void setCreator(Long creator) {
    //        this.creator = creator;
    //    }
    //
    //    public Long getUpdater() {
    //        return updater;
    //    }
    //
    //    public void setUpdater(Long updater) {
    //        this.updater = updater;
    //    }
}
