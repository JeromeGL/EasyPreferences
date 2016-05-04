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

import javax.lang.model.element.NestingKind;
import javax.lang.model.element.PackageElement;
import javax.lang.model.element.TypeElement;
import javax.lang.model.type.TypeMirror;

/**
 * Created by hujinrong on 16/4/30.
 */
public class TypeElementEntity extends BaseElementEntity<TypeElement> {
    public TypeElementEntity(TypeElement element) {
        super(element);
    }

    public String getTypeString() {
        return mElement.getQualifiedName().toString();
    }

    public TypeMirror getTypeMirror() {
        return mElement.asType() ;
    }

    public boolean isTopLevel() {
        return mElement.getNestingKind() == NestingKind.TOP_LEVEL ;
    }

    public PackageElement getPackageElement() {
        if( isTopLevel() ) {
            return (PackageElement) mElement.getEnclosingElement();
        } else {
            return null ;
        }
    }

    public boolean isInterface() {
        return mElement.getKind().isInterface() ;
    }

    public<T extends Annotation> T getAnnotation(Class<T> annotaion) {
        return mElement.getAnnotation(annotaion);
    }


}
