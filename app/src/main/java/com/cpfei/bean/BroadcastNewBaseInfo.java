package com.cpfei.bean;

import java.io.Serializable;

/**
 * Created by cpfei on 2017/2/15.
 */

public class BroadcastNewBaseInfo implements Serializable {

    private String descriptionText;//"", //广播描述
    private String jumpUrl;//"", //跳转地址

    public String getDescriptionText() {
        return descriptionText;
    }

    public void setDescriptionText(String descriptionText) {
        this.descriptionText = descriptionText;
    }

    public String getJumpUrl() {
        return jumpUrl;
    }

    public void setJumpUrl(String jumpUrl) {
        this.jumpUrl = jumpUrl;
    }
}
