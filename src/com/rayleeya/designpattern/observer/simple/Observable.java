package com.rayleeya.designpattern.observer.simple;

import java.util.ArrayList;

/**
 * A simple Observable
 * 
 * The Observable is no need to be abstract, 
 * because it can be extended, and also can be combined.
 * 
 * @author Rayleeya
 *
 * @param <T>
 */
public class Observable<T> {

	private ArrayList<Observer<T>> mObservers;
	private boolean mChanged;
	
	public Observable() {
		mObservers = new ArrayList<Observer<T>>();
	}

	public void setChanged() {
		mChanged = true;
	}
	
	public boolean hasChanged() {
		return mChanged;
	}
	
	public void clearChanged() {
		mChanged = false;
	}
	
	public void registerObserver(Observer<T> ob) {
		if (ob == null) //Must consider the NullPointer problem
			throw new IllegalArgumentException("Observer cannot be null.");
		synchronized (mObservers) { //Must consider the concurrent problem
			if (!mObservers.contains(ob)) //Must consider the readd problem
				mObservers.add(ob);
		}
	}
	
	public void unregisterObserver(Observer<T> ob) {
		if (ob == null) 
			throw new IllegalArgumentException("Observer cannot be null.");
		synchronized (mObservers) {
			mObservers.remove(ob);
		}
	}
	
	public void unregisterAll() {
		synchronized (mObservers) {
			mObservers.clear();
		}
	}
	
	public int countObservers() {
		return mObservers.size();
	}
	
	public void notifyChange(T data) { //Must consider the data type
		if (!hasChanged()) return; //Must consider effective update
		synchronized (mObservers) {
			for (Observer<T> ob : mObservers) {
				ob.onChange(this, data);
			}
		}
	}
	
	public void notifyChange() {
		notifyChange(null);
	}
}
