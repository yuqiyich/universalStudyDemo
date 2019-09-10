package com.example.firstdemo;

import com.google.gson.Gson;

/**
 *    
 *  * @Description:  [一句话描述该类的功能]   
 *  * @Author:    [yuqi]  
 *  * @CreateDate:   [2019/9/9 17:56]   
 *  * @email:   [yichitgo@gmail.com]   
 *  
 */
public class TestConfigSign {
    //md5(serverUrl|versionCode|phoneNumList|phoneNumReg|isRuleOn|forceFlag|downUrl|rule)
    public static void main(String[] args) {
        String url="https://raw.githubusercontent.com/yuqiyich/universalStudyDemo/master/gray_config.json";
        String configJson="{\n" +
                "  \"rule\":\"javascript:hitGray(%d,'%s','%s','%s','%s','%s',%d,'%s');function hitGray(versionCode,versionName,phoneNum,phoneNumMd5,deviceId,phoneBrand,androidSkdVersion,channel){\\\\nif(versionCode<= %s || validVersionName(versionName)){\\\\nif(notNull(phoneNumMd5)&&'%s'.indexOf(phoneNumMd5) != -1)\\\\n{ \\\\nconsole.log('phoneNum  has gray list')\\\\nreturn 1;\\\\n}\\\\nif(/'%s'/.test(phoneNum)&&notNull(phoneNum))\\\\n{ \\\\nconsole.log('phoneNum rule has hitGray')\\\\nreturn 1;\\\\n}\\\\n}\\\\nconsole.log('no rule match update')\\\\nreturn null\\\\n}\\\\n\\\\nfunction validVersionName(versionName){\\\\nvar grayVerisonStr='%s'\\\\nif(notNull(grayVerisonStr)&&notNull(versionName)){\\\\nvar grayNumArr=grayVerisonStr.split('.')\\\\nvar curNumArr=versionName.split('.')\\\\ngrayNumArr=grayNumArr.map((value)=>{\\\\nreturn  parseInt(value);\\\\n})\\\\ncurNumArr=curNumArr.map((value)=>{\\\\nreturn  parseInt(value);\\\\n})\\\\nfor (var i = 0; i < grayNumArr.length; i++) {\\\\nconsole.log('grayNumArr :'+grayNumArr[i]+';curNumArr[i]:'+curNumArr[i])\\\\nif(notNull(curNumArr[i])){\\\\nif(grayNumArr[i]<curNumArr[i]){\\\\nconsole.log('gray version small')\\\\nreturn false\\\\n}else if(grayNumArr[i]>curNumArr[i]){\\\\nconsole.log('gray version big')\\\\nreturn true\\\\n}else {\\\\n}\\\\n} else {\\\\nconsole.log('gray big len big')\\\\n}\\\\n}  \\\\n}\\\\nreturn false;\\\\n}\\\\n\\\\nfunction notNull(str){\\\\nif(str != 'undefined' && str != null && str != ''){\\\\nreturn true;\\\\n}\\\\nreturn false;\\\\n}\",\n" +
                "  \"downUrl\":\"https://lzidt.vips100.com/v2/delivery/data/119547292af54e999e335aaa11995653/?LBC=meta&token=&aid=532917&rec_log=true\",\n" +
                "  \"forceFlag\":\"0\",\n" +
                "  \"isRuleOn\":true,\n" +
                "  \"content\":\"灰度测试内容加密版\",\n" +
                "  \"versionCode\":\"300000150\",\n" +
                "  \"versionName\":\"1.5.0.1\",\n" +
                "  \"phoneNumReg\":\"\",\n" +
                "  \"phoneNumList\":\"6AADCEFA2ED1EA6B1CDA99A21FCD159C\",\n" +
                "  \"sign\":\"65E1FB08B0FBA95F5405D773B5A3DCDC\"\n" +
                "}";
        AppGrayInfo appGrayInfo= new Gson().fromJson(configJson,AppGrayInfo.class);
        String segmentation = "|";
        StringBuilder sb = new StringBuilder(url+ segmentation);
        sb.append(appGrayInfo.getVersionCode() + segmentation);
        sb.append(appGrayInfo.getVersionName() + segmentation);
        sb.append(appGrayInfo.getPhoneNumList() + segmentation);
        sb.append(appGrayInfo.getPhoneNumReg() + segmentation);
        sb.append(appGrayInfo.isRuleOn() + segmentation);
        sb.append(appGrayInfo.getForceFlag() + segmentation);
        sb.append(appGrayInfo.getDownUrl() + segmentation);
        sb.append(appGrayInfo.getRule());

        System.out.println(Md5Util.md5Encrypt32Upper(sb.toString()));
    }
}
