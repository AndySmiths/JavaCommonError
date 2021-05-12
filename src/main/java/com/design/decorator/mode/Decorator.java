package com.design.decorator.mode;

public class Decorator implements IDecorator{

    @Override
    public void decorate() {
        System.out.println("水电装修、天花板以及粉刷墙。。。");
    }
}
