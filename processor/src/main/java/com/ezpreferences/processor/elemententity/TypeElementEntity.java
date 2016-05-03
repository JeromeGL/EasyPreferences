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
