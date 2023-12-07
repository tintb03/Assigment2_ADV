package org.example.observer;

import java.util.ArrayList;
import java.util.List;

public class Subject {
    private List<ConcreteObserver> observers = new ArrayList<>();

    public void addObserver(ConcreteObserver observer) {
        observers.add(observer);
    }

    public void notifyObservers() {
        for (ConcreteObserver observer : observers) {
            observer.update();
        }
    }
}
