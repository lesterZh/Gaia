package com.gaia.member.androidlib.constant;

import com.squareup.okhttp.MediaType;

/**
 * Created by baiyuanwei on 16/3/16.
 */
public class NetConstant {

    //主机域名
    public final static String BASE_URL = "http://192.168.2.2:8080/gaia/member/";

    //在构造请求体时,MediaType参数的值
    public static final MediaType JSON = MediaType.parse("application/json; charset=utf-8");

    public static final String IMAGE_TYPE = "image/jpg";
}
