package com.ezpreferences.processor.utils;

import com.ezpreferences.processor.maker.methodmaker.BaseGetMethodMaker;
import com.squareup.javapoet.MethodSpec;
import com.squareup.javapoet.TypeName;

/**
 * Created by hujinrong on 16/5/3.
 */
public class Utils {
    public static MethodSpec makeMethod(com.ezpreferences.processor.entity.MethodEntity methodEntity) {
        TypeName typeName = null ;
        MethodSpec methodSpec = null ;
        if( methodEntity.mIsMethodSet) {
            typeName = methodEntity.mSetTypeName;
        } else {
            typeName = methodEntity.mReturnTypeName;
        }
        if( methodEntity.mIsMethodSet) {
            com.ezpreferences.processor.maker.methodmaker.BaseSetMethodMaker methodSpecIMaker = null ;
            if (typeName.equals(Constant.TBOOLEAN)) {
                methodSpecIMaker = new com.ezpreferences.processor.maker.methodmaker.BaseSetMethodMaker.SetBooleanMethodMaker(methodEntity.mMethodName,methodEntity.mKey);
            } else if (typeName.equals(Constant.TFLOAT)) {
                methodSpecIMaker = new com.ezpreferences.processor.maker.methodmaker.BaseSetMethodMaker.SetFloatMethodMaker(methodEntity.mMethodName,methodEntity.mKey);
            } else if (typeName.equals(Constant.TLONG)) {
                methodSpecIMaker = new com.ezpreferences.processor.maker.methodmaker.BaseSetMethodMaker.SetLongMethodMaker(methodEntity.mMethodName,methodEntity.mKey);
            } else if (typeName.equals(Constant.TINT)) {
                methodSpecIMaker = new com.ezpreferences.processor.maker.methodmaker.BaseSetMethodMaker.SetIntMethodMaker(methodEntity.mMethodName,methodEntity.mKey);
            } else if (typeName.equals(Constant.TSTRING)) {
                methodSpecIMaker = new com.ezpreferences.processor.maker.methodmaker.BaseSetMethodMaker.SetStringMethodMaker(methodEntity.mMethodName,methodEntity.mKey);
            }
            methodSpecIMaker.setCommitEnable(methodEntity.mIsCommit);
            methodSpec = methodSpecIMaker.make();
        } else {
            BaseGetMethodMaker methodSpecIMaker = null ;
            if (typeName.equals(Constant.TBOOLEAN)) {
                methodSpecIMaker = new BaseGetMethodMaker.GetBooleanMethodMaker(methodEntity.mMethodName,methodEntity.mDefaultValue,methodEntity.mKey);
            } else if (typeName.equals(Constant.TFLOAT)) {
                methodSpecIMaker = new BaseGetMethodMaker.GetFloatMethodMaker(methodEntity.mMethodName,methodEntity.mDefaultValue,methodEntity.mKey);
            } else if (typeName.equals(Constant.TLONG)) {
                methodSpecIMaker = new BaseGetMethodMaker.GetLongMethodMaker(methodEntity.mMethodName,methodEntity.mDefaultValue,methodEntity.mKey);
            } else if (typeName.equals(Constant.TINT)) {
                methodSpecIMaker = new BaseGetMethodMaker.GetIntMethodMaker(methodEntity.mMethodName,methodEntity.mDefaultValue,methodEntity.mKey);
            } else if (typeName.equals(Constant.TSTRING)) {
                methodSpecIMaker = new BaseGetMethodMaker.GetStringMethodMaker(methodEntity.mMethodName,methodEntity.mDefaultValue,methodEntity.mKey);
            }
            methodSpec = methodSpecIMaker.make();
        }
        return methodSpec ;
    }
}
