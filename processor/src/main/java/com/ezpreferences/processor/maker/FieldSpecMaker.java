package com.ezpreferences.processor.maker;

//import android.content.SharedPreferences;

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
