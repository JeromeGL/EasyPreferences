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
 *
 */
package com.ezpreferences.processor.maker;

import com.squareup.javapoet.FieldSpec;
import com.squareup.javapoet.TypeName;

import javax.lang.model.type.TypeMirror;

/**
 * Created by hujinrong on 16/5/1.
 */
public class FieldSpecMaker implements IMaker<FieldSpec> {
    TypeMirror typeMirror ;
    public FieldSpecMaker(TypeMirror sharedPreference) {
        this.typeMirror = sharedPreference ;
    }
    @Override
    public FieldSpec make() {
        FieldSpec fieldSpec = FieldSpec.builder(TypeName.get(typeMirror),"mSharedPreference").build();
        return fieldSpec;
    }
}
