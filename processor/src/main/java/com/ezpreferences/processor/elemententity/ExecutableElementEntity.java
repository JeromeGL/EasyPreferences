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
package com.ezpreferences.processor.elemententity;

import java.lang.annotation.Annotation;
import java.util.ArrayList;
import java.util.List;

import javax.lang.model.element.ExecutableElement;
import javax.lang.model.element.VariableElement;
import javax.lang.model.type.TypeMirror;

/**
 * Created by hujinrong on 16/4/30.
 */
public class ExecutableElementEntity extends BaseElementEntity<ExecutableElement> {
    String mMethodName;

    public ExecutableElementEntity(ExecutableElement element) {
        super(element);
        this.mElement = element ;
        mMethodName = element.getSimpleName().toString();
    }

    public boolean isConstructor() {
        return "<init>".equals(mMethodName);
    }

    public TypeMirror getReturnType() {
        return this.mElement.getReturnType();
    }

    public List<TypeMirror> getParameterType() {
        List<VariableElement> variableElements = (List<VariableElement>) this.mElement.getParameters();
        List<TypeMirror> types = new ArrayList<>();
        if( variableElements != null ) {
            for(VariableElement variableElement:variableElements ) {
                types.add(variableElement.asType());
            }
        }
        return types ;
    }

    public <T extends Annotation> T getAnnotation(Class<T> annotationClass) {
        T annotation = mElement.getAnnotation(annotationClass);
        return annotation ;
    }

    public String getmMethodName() {
        return mMethodName;
    }
}
