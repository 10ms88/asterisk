package com.company.asterisk.config;

import com.haulmont.cuba.core.config.Config;
import com.haulmont.cuba.core.config.Property;
import com.haulmont.cuba.core.config.Source;
import com.haulmont.cuba.core.config.SourceType;

@Source(type = SourceType.DATABASE)
public interface AsteriskServerConfig extends Config {

    @Property("asterisk.host")
    String getHost();

    @Property("asterisk.appName")
    String getAppName();

    @Property("asterisk.port")
    Integer getPort();

    @Property("asterisk.userName")
    String getUserName();

    @Property("asterisk.password")
    String getPassword();

    @Property("asterisk.url")
    String getUrl();

}
