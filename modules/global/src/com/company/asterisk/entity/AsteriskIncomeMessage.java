package com.company.asterisk.entity;

import com.haulmont.cuba.core.entity.BaseLongIdEntity;
import com.haulmont.cuba.core.entity.Creatable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Lob;
import javax.persistence.Table;
import java.util.Date;

@Table(name = "ASTERISK_INCOME_MESSAGE")
@Entity(name = "AsteriskIncomeMessage")
public class AsteriskIncomeMessage extends BaseLongIdEntity implements Creatable {
    private static final long serialVersionUID = -5960400814374289000L;
    
    @Column(name = "TYPE_")
    private String type;

    @Column(name = "CHANNEL_ID")
    private String channelId;

    @Column(name = "CREATE_TS")
    private Date createTs;

    @Column(name = "CREATED_BY", length = 50)
    private String createdBy;

    @Lob
    @Column(name = "INCOME_JSON")
    private String incomeJson;

    public void setType(AsteriskIncomeMessageType type) {
        this.type = type == null ? null : type.getId();
    }

    public AsteriskIncomeMessageType getType() {
        return type == null ? null : AsteriskIncomeMessageType.fromId(type);
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public Date getCreateTs() {
        return createTs;
    }

    public void setCreateTs(Date createTs) {
        this.createTs = createTs;
    }

    public String getChannelId() {
        return channelId;
    }

    public void setChannelId(String channelId) {
        this.channelId = channelId;
    }

    public String getIncomeJson() {
        return incomeJson;
    }

    public void setIncomeJson(String incomeJson) {
        this.incomeJson = incomeJson;
    }
}