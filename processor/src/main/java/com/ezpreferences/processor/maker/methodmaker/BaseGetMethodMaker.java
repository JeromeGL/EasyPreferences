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
package com.ezpreferences.processor.maker.methodmaker;

import com.ezpreferences.processor.maker.IMaker;
import com.squareup.javapoet.MethodSpec;
import com.squareup.javapoet.ParameterSpec;
import com.squareup.javapoet.TypeName;

import java.util.ArrayList;
import java.util.List;

import javax.lang.model.element.Modifier;

/**
 * Created by hujinrong on 16/5/1.
 */
public abstract class BaseGetMethodMaker<GetType extends TypeName> implements IMaker<MethodSpec> {
    protected String methodName ;
    protected String defaultValue ;
    protected GetType getType ;
    protected String key ;

    public BaseGetMethodMaker(String methodName, String defaultValue, String key, GetType getType) {
        this.methodName = methodName ;
        this.defaultValue = defaultValue ;
        this.getType = getType ;
        this.key = key ;
    }


    @Override
    public MethodSpec make() {
        List<ParameterSpec> parameterSpecs = new ArrayList<>();
        MethodSpec methodSpec = MethodSpec.methodBuilder(methodName)
                .addModifiers(Modifier.PUBLIC)
                .addParameters(parameterSpecs)
                .addStatement("return mSharedPreference.get$L(\"$L\",$L)", com.ezpreferences.processor.utils.Constant.typeMapping.get(getType),key,defaultValue)
                .returns(getType)
                .build();
        return methodSpec ;
    }


    public static class GetBooleanMethodMaker extends BaseGetMethodMaker {
        public GetBooleanMethodMaker(String methodName,String defaultValue,String key) {
            super(methodName, defaultValue,key, TypeName.BOOLEAN);
        }
    }

    public static class GetFloatMethodMaker extends BaseGetMethodMaker {
        public GetFloatMethodMaker(String methodName,String defaultValue,String key) {
            super(methodName, defaultValue,key, TypeName.FLOAT);
        }
    }

    public static class GetIntMethodMaker extends BaseGetMethodMaker {
        public GetIntMethodMaker(String methodName,String defaultValue,String key) {
            super(methodName,defaultValue,key,TypeName.INT);
        }
    }

    public static class GetLongMethodMaker extends BaseGetMethodMaker {
        public GetLongMethodMaker(String methodName,String defaultValue,String key) {
            super(methodName, defaultValue,key, TypeName.LONG);
        }
    }

    public static class GetStringMethodMaker extends BaseGetMethodMaker {
        public GetStringMethodMaker(String methodName,String defaultValue,String key) {
            super(methodName, defaultValue, key,TypeName.get(String.class));
        }
    }

}
