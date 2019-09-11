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
                "  \"rule\":\"javascript:hitGray(%d,'%s','%s','%s','%s','%s',%d,'%s');function hitGray(versionCode,versionName,phoneNum,phoneNumMd5,deviceId,phoneBrand,androidSkdVersion,channel){\\nif(versionCode<= %s || validVersionName(versionName)){\\n  if(notNull(phoneNumMd5)&&'%s'.indexOf(phoneNumMd5) != -1)\\n  { \\n    console.log('phoneNum  has gray list')\\n    return 1;\\n  }\\n  if(/'%s'/.test(phoneNum)&&notNull(phoneNum))\\n  { \\n    console.log('phoneNum rule has hitGray')\\n    return 1;\\n  }\\n}\\nconsole.log('no rule match update')\\nreturn null\\n}\\nfunction validVersionName(versionName){\\n   var grayVerisonStr='%s'\\n   if(notNull(grayVerisonStr)&&notNull(versionName)){\\nvar grayNumArr=grayVerisonStr.split('.')\\nvar curNumArr=versionName.split('.')\\ngrayNumArr=grayNumArr.map((value)=>{\\n    return  parseInt(value);\\n})\\n   curNumArr=curNumArr.map((value)=>{\\n    return  parseInt(value);\\n})\\n     for (var i = 0; i < grayNumArr.length; i++) {\\n         console.log('grayNumArr :'+grayNumArr[i]+';curNumArr[i]:'+curNumArr[i])\\n\\t\\t if(notNull(curNumArr[i])){\\n\\t\\t if(grayNumArr[i]<curNumArr[i]){\\n\\t\\t     console.log('gray version small')\\n             return false\\n           }else if(grayNumArr[i]>curNumArr[i]){\\n            console.log('gray version big')\\n           return true\\n      }else {\\n       }\\n\\t\\t } else {\\n\\t\\t  console.log('gray big len big')\\n\\t\\t }\\n    }  \\n   }\\n   return false;\\n}\\nfunction notNull(str){\\nif(str != 'undefined' && str != null && str != ''){\\n return true;\\n}\\nreturn false;\\n}\",\n" +
                "  \"downUrl\":\"https://lzidt.vips100.com/v2/delivery/data/119547292af54e999e335aaa11995653/?LBC=meta&token=&aid=532917&rec_log=true\",\n" +
                "  \"forceFlag\":\"0\",\n" +
                "  \"isRuleOn\":true,\n" +
                "  \"content\":\"灰度测试内容加密版111\",\n" +
                "  \"versionCode\":\"300000150\",\n" +
                "  \"versionName\":\"1.5.0.81\",\n" +
                "  \"phoneNumReg\":\"\",\n" +
                "  \"phoneNumList\":\"6AADCEFA2ED1EA6B1CDA99A21FCD159C\",\n" +
                "  \"sign\":\"C0F4D7892AB699D7526E1EA7EA64D44C\"\n" +
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
