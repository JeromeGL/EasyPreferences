package com.ezpreferences.processor.entity;

import com.squareup.javapoet.TypeName;

/**
 * Created by hujinrong on 16/5/3.
 */
public class MethodEntity {
    public String mMethodName;//方法名
    public String mKey; //preference mKey.
    public boolean mIsMethodSet;
    //get
    public String mDefaultValue;
    public TypeName mReturnTypeName;
    //set
    public boolean mIsCommit;
    public TypeName mSetTypeName;

    // getMethod
    public MethodEntity(String methodName,String key,String defaultValue,TypeName returnTypeName) {
        this.mMethodName = methodName ;
        this.mKey = key ;
        this.mDefaultValue = defaultValue ;
        this.mReturnTypeName = returnTypeName ;
        mIsMethodSet = false ;
    }

    // setMethod
    public MethodEntity(String methodName,String key,boolean isCommit,TypeName setTypeName) {
        this.mMethodName = methodName ;
        this.mKey = key ;
        this.mIsCommit = isCommit ;
        this.mSetTypeName = setTypeName ;
        mIsMethodSet = true ;
    }
}
