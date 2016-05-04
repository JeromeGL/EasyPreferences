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
