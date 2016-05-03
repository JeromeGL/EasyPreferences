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
