package org.example.decorator;

public class ConcreteDecorator implements Component {
    private Component component;

    public ConcreteDecorator(Component component) {
        this.component = component;
    }

    @Override
    public void operation() {
        component.operation();
        System.out.println("ConcreteDecorator operation");
    }
}
