package com.ezpreferences.processor;

import com.ezpreferences.processor.utils.Logger;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.Filer;
import javax.annotation.processing.Messager;
import javax.annotation.processing.ProcessingEnvironment;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.Element;
import javax.lang.model.element.ElementKind;
import javax.lang.model.util.Elements;

/**
 * Created by hujinrong on 16/4/28.
 */
public abstract class BaseProcessor extends AbstractProcessor {

    protected Filer mFiler ;
    protected Messager mMessager;
    protected Logger mLogger ;
    protected Elements mElements ;
    @Override
    public synchronized void init(ProcessingEnvironment processingEnv) {
        super.init(processingEnv);
        this.mMessager = processingEnv.getMessager() ;
        this.mFiler = processingEnv.getFiler() ;
        this.mLogger = Logger.getLogger(this.mMessager);
        this.mElements = processingEnv.getElementUtils() ;
    }


    @Override
    public SourceVersion getSupportedSourceVersion() {
        return SourceVersion.latestSupported();
    }

    /**
     * 获取方法元素
     * @param element
     * @return
     */
    public List<Element> getMethodElements(Element element) {
        return getElementsByKind(element,ElementKind.METHOD) ;
    }

    /**
     * 获取域元素
     * @param element
     * @return
     */
    public List<Element> getFieldElements(Element element) {
        return getElementsByKind(element,ElementKind.FIELD);
    }

    private List<Element> getElementsByKind(Element rootElement,ElementKind type) {
        List<Element> elementsAll = (List<Element>) rootElement.getEnclosedElements();
        List<Element> ret = new ArrayList<>();
        if( elementsAll != null ) {
            for(Element elementIterator:elementsAll ) {
                if( elementIterator.getKind() == type ) {
                    ret.add(elementIterator);
                }
            }
        }
        return ret ;
    }

}
