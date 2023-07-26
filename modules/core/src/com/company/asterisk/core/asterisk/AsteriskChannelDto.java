package com.company.asterisk.core.asterisk;


import com.company.asterisk.utils.ExceptionUtils;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.io.Serializable;

public class AsteriskChannelDto implements Serializable {

    private String id;
    private String name;
    private String state;
    private Caller caller;
    private Connected connected;
    private String accountcode;
    private Dialplan dialplan;
    private String creationtime;
    private String language;


    public static AsteriskChannelDto fromJson(String json) {
        try {
            return new ObjectMapper().readValue(json, AsteriskChannelDto.class);
        } catch (IOException e) {
           ExceptionUtils.logErrors(e);
           return null;
        }
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public Caller getCaller() {
        return caller;
    }

    public void setCaller(Caller caller) {
        this.caller = caller;
    }

    public Connected getConnected() {
        return connected;
    }

    public void setConnected(Connected connected) {
        this.connected = connected;
    }

    public String getAccountcode() {
        return accountcode;
    }

    public void setAccountcode(String accountcode) {
        this.accountcode = accountcode;
    }

    public Dialplan getDialplan() {
        return dialplan;
    }

    public void setDialplan(Dialplan dialplan) {
        this.dialplan = dialplan;
    }

    public String getCreationtime() {
        return creationtime;
    }

    public void setCreationtime(String creationtime) {
        this.creationtime = creationtime;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public static class Caller implements Serializable {
        private String name;
        private String number;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getNumber() {
            return number;
        }

        public void setNumber(String number) {
            this.number = number;
        }


    }

    public static class Connected implements Serializable {
        private String name;
        private String number;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getNumber() {
            return number;
        }

        public void setNumber(String number) {
            this.number = number;
        }

    }

    public static class Dialplan implements Serializable {
        private String context;
        private String exten;
        private int priority;
        private String app_name;
        private String app_data;

        public String getContext() {
            return context;
        }

        public void setContext(String context) {
            this.context = context;
        }

        public String getExten() {
            return exten;
        }

        public void setExten(String exten) {
            this.exten = exten;
        }

        public int getPriority() {
            return priority;
        }

        public void setPriority(int priority) {
            this.priority = priority;
        }

        public String getApp_name() {
            return app_name;
        }

        public void setApp_name(String app_name) {
            this.app_name = app_name;
        }

        public String getApp_data() {
            return app_data;
        }

        public void setApp_data(String app_data) {
            this.app_data = app_data;
        }
    }

}
