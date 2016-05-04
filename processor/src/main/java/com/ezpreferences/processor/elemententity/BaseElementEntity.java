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

import java.util.HashSet;
import java.util.Set;

import javax.lang.model.element.Element;
import javax.lang.model.element.Modifier;

/**
 * Created by hujinrong on 16/4/30.
 */
public abstract class BaseElementEntity<T extends Element> {
    protected T mElement;
    protected Set<Modifier> mModifiers = new HashSet<>();
    public BaseElementEntity(T element) {
        this.mElement = element ;
        Set<Modifier> elementModifiers = this.mElement.getModifiers()  ;
        if( elementModifiers != null ) {
            mModifiers.addAll(elementModifiers);
        }
    }

    public boolean isFinal() {
        return mModifiers.contains(Modifier.FINAL);
    }

    public boolean isStatic() {
        return mModifiers.contains(Modifier.STATIC);
    }

    public boolean isPublic() {
        return mModifiers.contains(Modifier.PUBLIC);
    }

    public boolean isAbstract() {
        return mModifiers.contains(Modifier.ABSTRACT);
    }

    public T getElement() {
        return mElement;
    }

    public String getSimpleName() {
        return mElement.getSimpleName().toString() ;
    }

}
