package com.cn.demo.contant;

/**
 * @program: demo
 * @description: 公共信息静态常量
 * @author: DongLianPo
 * @create: 2018/12/11 14:11
 **/
public class Constants {
    /*公共信息定义*/
    /*** 爱充组织机构编号*/
    public static final String WANMA_OPERATOR_CODE = "321895837";
    /*** 请求Token返回标识*/
    public static final String QUERY_TOKEN_RET = "Ret";
    public static final String QUERY_TOKEN_RESULT = "4002";
    public static final int QUERY_TOKEN_RESULTS = 4002;
    public static final String HTTP_RET_RESULT = "0";
    public static final Integer DATA_LENGTHS = 1000;
    public static final Integer STRING_LENGTHS = 9;


    /*HttpClient线程池常量设置*/
    /**
     * 最大连接数
     */
    public static final int HTTP_MAX_TOTAL = 100;
    /**
     * 并发数
     */
    public static final int HTTP_DEFAULT_MAX_PER_ROUTE = 20;
    /**
     * 创建连接的最长时间
     */
    public static final int HTTP_CONNECT_TIMEOUT = 1000;
    /**
     * 从连接池中获取到连接的最长时间
     */
    public static final int HTTP_CONNECTION_REQUEST_TIMEOUT = 500;
    /**
     * 数据传输的最长时间
     */
    public static final int HTTP_SOCKET_TIMEOUT = 10000;
    /**
     * 提交请求前测试连接是否可用
     */
    public static final Boolean HTTP_STALE_CONNECTION_CHECK_ENABLED = true;

    /**
     * Token缓存时间
     */
    public static final Long TOKEN_TIME_SIZE = 100L;

    /*第三方信息Redis缓存路径*/
    /**
     * redis中运营商信息基础信息
     */
    public static final String THIRD_OPERATOR_ORG_NO = "html:third:orgNo:";
    /**
     * redis中运营商token信息
     */
    public static final String THIRD_TOKEN = "html:third:token:";

}
