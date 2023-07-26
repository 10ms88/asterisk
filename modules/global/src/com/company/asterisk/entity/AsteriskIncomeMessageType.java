package com.company.asterisk.entity;

import com.haulmont.chile.core.datatypes.impl.EnumClass;

import javax.annotation.Nullable;


public enum AsteriskIncomeMessageType implements EnumClass<String> {

    STASIS_START("StasisStart"),
    STASIS_END("StasisEnd"),
    CHANNEL_HANGUP_REQUEST("ChannelHangupRequest"),
    CHANNEL_LEFT_BRIDGE("ChannelLeftBridge"),
    CHANNEL_ENTERED_BRIDGE("ChannelEnteredBridge"),
    BRIDGE_DESTROYED("BridgeDestroyed"),
    DIAL("Dial"),
    CHANNEL_STATE_CHANGE("ChannelStateChange"),
    CHANNEL_VARSET("ChannelVarset"),
    CHANNEL_DIALPLAN("ChannelDialplan"),
    CHANNEL_DESTROYED("ChannelDestroyed"),
    CHANNEL_CONNECTED_LINE("ChannelConnectedLine"),
    CHANNEL_UNHOLD("ChannelUnhold"),
    CHANNEL_HOLD("ChannelHold");

    private String id;

    AsteriskIncomeMessageType(String value) {
        this.id = value;
    }

    public String getId() {
        return id;
    }

    @Nullable
    public static AsteriskIncomeMessageType fromId(String id) {
        for (AsteriskIncomeMessageType at : AsteriskIncomeMessageType.values()) {
            if (at.getId().equals(id)) {
                return at;
            }
        }
        return null;
    }
}