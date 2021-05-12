package com.design.decorator.mode;

/**
 * 基本装饰类
 */
public abstract class BaseDecorator implements IDecorator {

    private IDecorator decorator;

    public BaseDecorator(IDecorator decorator) {
        this.decorator = decorator;
    }

    /**
     * 调用装饰方法
     */
    public void decorate() {
        if(decorator != null) {
            decorator.decorate();
        }
    }
}
