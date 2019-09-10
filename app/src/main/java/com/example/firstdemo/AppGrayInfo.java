package com.example.firstdemo;

public class AppGrayInfo {
    String rule;//下发规则
    boolean isRuleOn = false;//下发的规则是否开启
    String downUrl;//apk 下载地址
    String forceFlag;//是否强制
    String content;//更新内容
    String versionCode;//版本号
    String phoneNumReg;//号码正则表达式
    String phoneNumList;//灰度号码的列表
    String sign;//签名信息

    public String getPhoneNumReg() {
        return phoneNumReg;
    }

    public void setPhoneNumReg(String phoneNumReg) {
        this.phoneNumReg = phoneNumReg;
    }

    public String getPhoneNumList() {
        return phoneNumList;
    }

    public void setPhoneNumList(String phoneNumList) {
        this.phoneNumList = phoneNumList;
    }



    public boolean isRuleOn() {
        return isRuleOn;
    }

    public void setRuleOn(boolean ruleOn) {
        isRuleOn = ruleOn;
    }

    public String getRule() {
        return rule;
    }

    public void setRule(String rule) {
        this.rule = rule;
    }

    public String getDownUrl() {
        return downUrl;
    }

    public void setDownUrl(String downUrl) {
        this.downUrl = downUrl;
    }

    public String getForceFlag() {
        return forceFlag;
    }

    public void setForceFlag(String forceFlag) {
        this.forceFlag = forceFlag;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getVersionCode() {
        return versionCode;
    }

    public void setVersionCode(String versionCode) {
        this.versionCode = versionCode;
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }
}
