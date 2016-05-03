package com.ezpreferences.processor.maker.methodmaker;

import com.ezpreferences.processor.maker.IMaker;
import com.ezpreferences.processor.utils.Constant;
import com.squareup.javapoet.MethodSpec;
import com.squareup.javapoet.ParameterSpec;
import com.squareup.javapoet.TypeName;

import java.util.ArrayList;
import java.util.List;

import javax.lang.model.element.Modifier;

/**
 * Created by hujinrong on 16/5/1.
 */
public abstract class BaseSetMethodMaker<SetType extends TypeName> implements IMaker<MethodSpec> {

    protected SetType setType;
    protected String methodName ;
    protected String key ;

    protected boolean mCommitEnable ;

    public BaseSetMethodMaker(String methodName, String key, SetType getType) {
        this.setType = getType ;
        this.methodName = methodName ;
        this.key = key ;
    }

    /**
     * commit() commitEnable = true
     * apply() commitEnalbe = false
     * @param commitEnable
     */
    public void setCommitEnable(boolean commitEnable) {
        this.mCommitEnable = commitEnable ;
    }

    @Override
    public MethodSpec make() {
        List<ParameterSpec> parameterSpecs = new ArrayList<>();
        ParameterSpec valueParameter = ParameterSpec.builder(setType,"value").build();
        parameterSpecs.add(valueParameter);
        MethodSpec.Builder methodSpecBuilder = MethodSpec.methodBuilder(methodName)
                .addModifiers(Modifier.PUBLIC)
                .addParameters(parameterSpecs);
                if( mCommitEnable ) {
                    methodSpecBuilder.addStatement("return mSharedPreference.edit().put$L(\"$L\",value).commit()", Constant.typeMapping.get(setType), key)
                            .returns(TypeName.BOOLEAN);
                } else {
                    methodSpecBuilder.addStatement("mSharedPreference.edit().put$L(\"$L\",value).apply()", Constant.typeMapping.get(setType), key)
                            .returns(TypeName.VOID);
                }

        return methodSpecBuilder.build() ;
    }


    public static class SetBooleanMethodMaker extends BaseSetMethodMaker {
        public SetBooleanMethodMaker(String methodName,String key) {
            super(methodName,key, TypeName.BOOLEAN);
        }
    }

    public static class SetFloatMethodMaker extends BaseSetMethodMaker {
        public SetFloatMethodMaker(String methodName,String key) {
            super(methodName,key, TypeName.FLOAT);
        }
    }

    public static class SetIntMethodMaker extends BaseSetMethodMaker {
        public SetIntMethodMaker(String methodName,String key) {
            super(methodName,key, TypeName.INT);
        }

    }

    public static class SetLongMethodMaker extends BaseSetMethodMaker {
        public SetLongMethodMaker(String methodName,String key) {
            super(methodName,key, TypeName.LONG);
        }
    }

    public static class SetStringMethodMaker extends BaseSetMethodMaker {
        public SetStringMethodMaker(String methodName,String key) {
            super(methodName, key,TypeName.get(String.class));
        }

    }

}
