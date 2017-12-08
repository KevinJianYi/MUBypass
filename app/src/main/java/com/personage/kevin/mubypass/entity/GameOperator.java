package com.personage.kevin.mubypass.entity;

import java.io.Serializable;

/**
 * Created by pc1 on 2016/10/18.
 * 游戏运营商
 */
public class GameOperator implements Serializable{

    /** 运营编号**/
    private String operatorID;
    /** 运营商名称（游戏名称）**/
    private String operatorName;
    /** 运营商简介（内容简介）**/
    private String operatorIntro;
    /** 运营地址（游戏主页链接）**/
    private String operatorAddressLink;
    /**备注**/
    private String remark;

    public GameOperator() {
    }

    public GameOperator(String operatorID, String operatorName, String operatorIntro, String operatorAddressLink, String remark) {
        this.operatorID = operatorID;
        this.operatorName = operatorName;
        this.operatorIntro = operatorIntro;
        this.operatorAddressLink = operatorAddressLink;
        this.remark = remark;
    }

    public String getOperatorID() {
        return operatorID;
    }

    public void setOperatorID(String operatorID) {
        this.operatorID = operatorID;
    }

    public String getOperatorName() {
        return operatorName;
    }

    public void setOperatorName(String operatorName) {
        this.operatorName = operatorName;
    }

    public String getOperatorIntro() {
        return operatorIntro;
    }

    public void setOperatorIntro(String operatorIntro) {
        this.operatorIntro = operatorIntro;
    }

    public String getOperatorAddressLink() {
        return operatorAddressLink;
    }

    public void setOperatorAddressLink(String operatorAddressLink) {
        this.operatorAddressLink = operatorAddressLink;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }




}
