/**
 * Copyright (C) hujinrong. hujinrong888@gmail.com
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed To in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */
package com.ezpreferences.processor.checker;

import com.ezpreferences.interfaces.Commit;
import com.ezpreferences.interfaces.Default;
import com.ezpreferences.interfaces.Key;
import com.ezpreferences.processor.utils.Constant;
import com.squareup.javapoet.TypeName;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.lang.model.type.TypeMirror;

/**
 * Created by hujinrong on 16/5/3.
 * Validate method.
 * 1.mMethodName get/set/is
 * 2.methodReturnType
 * 3.methodParameterType
 * 4.default value
 * 5.value type with mKeyAnnotation.set get with save type.
 * 6.multi mKeyAnnotation type.
 */
public class MethodChecker implements IChecker {
    private static Map<String, com.ezpreferences.processor.entity.MethodEntity> keyTypeMapping = new HashMap<>();
    private static List<com.ezpreferences.processor.entity.MethodEntity> methodEntities = new ArrayList<>();

    public static List<com.ezpreferences.processor.entity.MethodEntity> getMethodEntities() {
        return methodEntities ;
    }

    private com.ezpreferences.processor.elemententity.ExecutableElementEntity mEntity ;
    private String mMethodName;
    private List<TypeMirror> mParameters;
    private TypeName mReturnType;
    private Default mDefaultAnnotation;
    private Commit mCommitAnnotation;
    private Key mKeyAnnotation;
    private String mErrorMessage ;
    private String mKey;
    private boolean mIsMethodSet;
    String mDefaultValueString = null ;


    public MethodChecker(com.ezpreferences.processor.elemententity.ExecutableElementEntity entity) {
        this.mEntity = entity ;
    }


    @Override
    public boolean check() {
        mMethodName = this.mEntity.getmMethodName() ;
        mParameters = this.mEntity.getParameterType() ;
        mReturnType = TypeName.get(this.mEntity.getReturnType()) ;
        mDefaultAnnotation = this.mEntity.getAnnotation(Default.class);
        mCommitAnnotation = this.mEntity.getAnnotation(Commit.class);
        mKeyAnnotation = this.mEntity.getAnnotation(Key.class);
        if( mMethodName.startsWith(Constant.PREFIX_GET)) {
            if(!checkGetMethod()) {
                return false ;
            }
            mKey = Constant.PREFIX+ mMethodName.replace(Constant.PREFIX_GET,"");
            mIsMethodSet = false ;
        } else if( mMethodName.startsWith(Constant.PREFIX_SET)) {
            if(!checkSetMethod()) {
                return false ;
            }
            mKey = Constant.PREFIX + mMethodName.replace(Constant.PREFIX_SET,"");
            mIsMethodSet = true ;
        } else if( mMethodName.startsWith(Constant.PREFIX_IS)) {
            if( !TypeName.BOOLEAN.equals(mReturnType)) {
                mErrorMessage = Constant.WARNING_METHOD_IS ;
                return false ;
            }
            if(!checkGetMethod()) {
                return false ;
            }
            mKey = Constant.PREFIX + mMethodName.replace(Constant.PREFIX_IS,"");
            mIsMethodSet = false ;
        } else {
            mErrorMessage = Constant.WARNING_METHOD_NAME;
            return false ;
        }

        if( mKeyAnnotation != null ) {
            mKey = mKeyAnnotation.key() ;
        }

        com.ezpreferences.processor.entity.MethodEntity methodEntityExists = keyTypeMapping.get(mKey) ;
        com.ezpreferences.processor.entity.MethodEntity methodEntity = null ;
        if(mIsMethodSet) {
             methodEntity = new com.ezpreferences.processor.entity.MethodEntity(mMethodName, mKey, mCommitAnnotation != null,TypeName.get(mParameters.get(0)));
        } else {
             methodEntity = new com.ezpreferences.processor.entity.MethodEntity(mMethodName, mKey, mDefaultValueString, mReturnType);
        }
        if( methodEntityExists != null ) {
            TypeName typeNameExists = null ;
            TypeName typeNameCurrent = null ;
            if( methodEntityExists.mIsMethodSet) {
                typeNameExists = methodEntityExists.mSetTypeName;
            } else {
                typeNameExists = methodEntityExists.mReturnTypeName;
            }

            if( methodEntity.mIsMethodSet) {
                typeNameCurrent = methodEntity.mSetTypeName;
            } else {
                typeNameCurrent = methodEntity.mReturnTypeName;
            }

            if( !typeNameExists.equals(typeNameCurrent)) {
                mErrorMessage = Constant.WARNING_METHOD_MULTI_KEY ;
                return false ;
            }
        }
        keyTypeMapping.put(mKey,methodEntity);
        methodEntities.add(methodEntity);

        return true ;
    }

    @Override
    public String errorMessage() {
        return mEntity.getmMethodName()+":"+mErrorMessage ;
    }

    private boolean checkGetMethod() {
        if( mParameters.size() > 0 ) {
            mErrorMessage = Constant.WARNING_METHOD_PARAMETER_COUNT ;
            return false ;
        }

        if( TypeName.VOID.equals(TypeName.get(mEntity.getElement().getReturnType()))) {
            mErrorMessage = Constant.WARNING_METHOD_RETURNTYPE ;
            return false ;
        }

        String retTypeCheck = checkType(mReturnType);
        if( retTypeCheck != null ) {
            mErrorMessage = retTypeCheck ;
            return false ;
        }

        if( mCommitAnnotation != null ) {
            mErrorMessage = Constant.WARNING_METHOD_GET_CANNOT_WITH_COMMIT ;
            return false ;
        }

        if( mDefaultAnnotation != null ) {
            try {
                if (TypeName.BOOLEAN.equals(mReturnType)) {
                    boolean defaultValue = false ;
                    if(Constant.STrue.equals(mDefaultAnnotation.value())) {
                        defaultValue = true ;
                    } else if(Constant.SFalse.equals(mDefaultAnnotation.value())) {
                        defaultValue = false ;
                    } else {
                        throw new RuntimeException(Constant.WARNING_METHOD_BOOLEAN_DEFAULTVALUE);
                    }
                    mDefaultValueString = defaultValue +"";
                } else if (TypeName.FLOAT.equals(mReturnType)) {
                    float defaultValue = Float.parseFloat(mDefaultAnnotation.value());
                    mDefaultValueString = defaultValue +"";
                } else if (TypeName.LONG.equals(mReturnType)) {
                    long defaultValue = Long.parseLong(mDefaultAnnotation.value());
                    mDefaultValueString = defaultValue +"";
                } else if (TypeName.INT.equals(mReturnType)) {
                    int defaultValue = Integer.parseInt(mDefaultAnnotation.value());
                    mDefaultValueString = defaultValue +"";
                }
            } catch( Exception e) {
                mErrorMessage = Constant.WARNING_METHOD_CONVERT_ERROR +e.getMessage();
                return false ;
            }
        } else {
            if (TypeName.BOOLEAN.equals(mReturnType)) {
                mDefaultValueString = false +"";
            } else if (TypeName.FLOAT.equals(mReturnType)) {
                mDefaultValueString = 0 +"";
            } else if (TypeName.LONG.equals(mReturnType)) {
                mDefaultValueString = 0 +"";
            } else if (TypeName.INT.equals(mReturnType)) {
                mDefaultValueString = 0 +"";
            }
        }

        return true ;
    }

    private boolean checkSetMethod() {
        if( mParameters.size() != 1 ) {
            mErrorMessage = Constant.WARING_METHOD_SET_PARAMETER_COUNT ;
            return false ;
        }

        if(!TypeName.VOID.equals(TypeName.get(mEntity.getElement().getReturnType())) && mCommitAnnotation == null ) {
            mErrorMessage = Constant.WARNING_METHOD_SET_RETURN_TYPE_VOID;
            return false ;
        }

        if( !TypeName.BOOLEAN.equals(TypeName.get(mEntity.getElement().getReturnType())) && mCommitAnnotation != null ) {
            mErrorMessage = Constant.WARNING_METHOD_SET_RETURN_TYPE_BOOLEAN;
            return false ;
        }

        String retTypeCheck = checkType(TypeName.get(mParameters.get(0)));
        if( retTypeCheck != null ) {
            mErrorMessage = retTypeCheck ;
            return false ;
        }

        if( mDefaultAnnotation != null ) {
            mErrorMessage = Constant.WARNING_METHOD_SET_NO_DEFAULT ;
            return false ;
        }

        return true ;
    }

    private String checkType(TypeName typeName) {
        if( TypeName.BOOLEAN.equals(typeName)||TypeName.INT.equals(typeName)||
                TypeName.FLOAT.equals(typeName)||TypeName.LONG.equals(typeName)||
                TypeName.get(String.class).equals(typeName)) {
            return null ;
        }
        return Constant.WARNING_METHOD_TYPE ;
    }
}
