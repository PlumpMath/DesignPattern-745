package com.rayleeya.designpattern.observer.simple;

public interface Observer<T> {

	public void onChange(Observable<T> ob, T data);
}
