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
import com.squareup.javapoet.TypeName;

import javax.lang.model.element.Modifier;
import javax.lang.model.type.TypeMirror;

/**
 * Created by hujinrong on 16/5/3.
 */
public class ConstructWithContextMaker implements IMaker<MethodSpec> {
    protected String mSharedPreferenceName;
    protected TypeMirror mContextTypeMirror ;
    public ConstructWithContextMaker(String name, TypeMirror contextTypeMirror) {
        this.mSharedPreferenceName = name ;
        this.mContextTypeMirror = contextTypeMirror ;
    }
    @Override
    public MethodSpec make() {
        MethodSpec methodSpec = MethodSpec.constructorBuilder()
                .addParameter(TypeName.get(mContextTypeMirror),"context")
                .addStatement("mSharedPreference = context.getApplicationContext().getSharedPreferences(\"$L\",Context.MODE_PRIVATE);", mSharedPreferenceName)
                .addModifiers(Modifier.PUBLIC)
                .build();
        return methodSpec;
    }
}
