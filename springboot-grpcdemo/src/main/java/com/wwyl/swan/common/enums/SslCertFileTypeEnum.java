package com.wwyl.swan.common.enums;

import lombok.Getter;

/**
 * Author: wen.huang_wb
 * Description:证书文件类型枚举,包括ca证书,公钥,私钥
 * Date: 2018/5/19
 **/

@Getter
public enum SslCertFileTypeEnum {

    SSLCERT_CRT("crt"),
    SSLCERT_KEY("key"),
    SSLCERT_CA("ca");
    private String certFileType;

    private SslCertFileTypeEnum(String CertFileType) {
        this.certFileType = CertFileType;
    }

    public static Boolean checkExtention (String sslFileType) {
        SslCertFileTypeEnum[] sslCertEnums = SslCertFileTypeEnum.values();

        for (SslCertFileTypeEnum sslCertEnum : sslCertEnums) {
            if (sslCertEnum.certFileType.equals(sslFileType)) {
                return  true;
            }
        }
        return false;
    }
}

