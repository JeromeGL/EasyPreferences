package com.ezpreferences.processor.utils;

import com.squareup.javapoet.TypeName;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by hujinrong on 16/5/3.
 */
public class Constant {

    public static String PREFIX  = "EZP_";
    public static String PREFIX_GET = "get";
    public static String PREFIX_SET = "set";
    public static String PREFIX_IS = "is";

    public static TypeName TBOOLEAN = TypeName.BOOLEAN ;
    public static TypeName TINT = TypeName.INT ;
    public static TypeName TFLOAT = TypeName.FLOAT ;
    public static TypeName TLONG = TypeName.LONG ;
    public static TypeName TSTRING = TypeName.get(String.class);

    public static String STrue = "true";
    public static String SFalse = "false";

    public static Map<TypeName,String> typeMapping = new HashMap<>();
    static  {
        typeMapping.put(TBOOLEAN,"Boolean");
        typeMapping.put(TINT,"Int");
        typeMapping.put(TFLOAT,"Float");
        typeMapping.put(TLONG,"Long");
        typeMapping.put(TSTRING,"String");
    }

    public static String WARNING_TYPE_TOP_LEVEL = "必须为顶级类";
    public static String WARNING_TYPE_INTERFACE = "必须为接口类型";
    public static String WARNING_METHOD_IS = "必须返回boolean类型,否则用getXXX()代替.";
    public static String WARNING_METHOD_NAME = "方法名必须以get/set/is开头.";
    public static String WARNING_METHOD_MULTI_KEY = "类型不一致.请检查是否有同一个key但采用了不同的类型.";

    public static String WARNING_METHOD_PARAMETER_COUNT = "方法参数列表个数必须为0";
    public static String WARNING_METHOD_RETURNTYPE = "返回值不能为void.";
    public static String WARNING_METHOD_TYPE = "Only have 5 types(boolean/int/float/long/String) to choose";
    public static String WARNING_METHOD_GET_CANNOT_WITH_COMMIT = "不能用Commit Annotation声明.";

    public static String WARNING_METHOD_BOOLEAN_DEFAULTVALUE = "boolean default value is true/false.";
    public static String WARNING_METHOD_CONVERT_ERROR = "Default Annotation 转换成相应类型失败.请检查.";

    public static String WARING_METHOD_SET_PARAMETER_COUNT = "有且仅有一个参数.";
    public static String WARNING_METHOD_SET_RETURN_TYPE_VOID = "返回值必须为void.";
    public static String WARNING_METHOD_SET_RETURN_TYPE_BOOLEAN = "Commit Annotation声明的方法.返回值必须为Boolean.";

    public static String WARNING_METHOD_SET_NO_DEFAULT = "方法不能声明Default Annotation.";
}
