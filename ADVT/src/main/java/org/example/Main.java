package org.example;

import org.example.entity.Student;
import org.example.entity.Teacher;
import org.example.interfaces.NotifyInterface;
import org.example.interfaces.UpdateInfoRequest;
import org.example.service.AccountFactory;
import org.example.service.AdminService;
import org.example.service.FacadeService;
import org.example.service.TeacherService;
import org.example.observer.*;
import org.example.decorator.*;
import org.example.command.*;
import org.example.strategy.CreditCardPayment;
import org.example.strategy.PayPalPayment;
import org.example.strategy.PaymentContext;
import org.example.strategy.PaymentStrategy;



import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        // Insert teacher =========Singleton - Creation=====================
        Teacher teacher = new Teacher(1,"Dinh Van Dong");
        TeacherService.getInstance().insert(teacher);

        // Iterator ============Behavior =============================
        Student student1 = new Student(1,"An");
        Student student2 = new Student(2, "Binh");
        Student student3 = new Student(3,"Nam");

        List<Student> studentList = new ArrayList<>();
        studentList.add(student1);
        studentList.add(student2);
        studentList.add(student3);
        Iterator<Student> iterator = studentList.iterator();
        while (iterator.hasNext()) {
            Student student = iterator.next();
            System.out.println(student.toString());
        }

        // Adapter ========Structural Pattern
        UpdateInfoRequest updateInfoRequest = new AdminService(TeacherService.getInstance());
        updateInfoRequest.updateInfoTeacher(teacher);

        //===================================================================================
        // Factory Method == Creational Pattern
        NotifyInterface notifyStudent = AccountFactory.createNotification("Student");
        notifyStudent.notification();
        NotifyInterface notifyTeacher = AccountFactory.createNotification("Teacher");
        notifyTeacher.notification();
        NotifyInterface notifyParent = AccountFactory.createNotification("Parent");
        notifyParent.notification();

        //Facade == Structural Pattern          FacadeService
        FacadeService facadeService = new FacadeService();
        facadeService.operationDeleteTeacher(1);

        // Observer == Behavioral Pattern
        ConcreteObserver observer = new ConcreteObserver();
        Subject subject = new Subject();
        subject.addObserver(observer);
        subject.notifyObservers();

        // Decorator == Structural Pattern
        Component component = new ConcreteComponent();
        component = new ConcreteDecorator(component);
        component.operation();

        // Command == Behavioral Pattern
        Receiver receiver = new Receiver();
        Command command = new ConcreteCommand(receiver);
        Invoker invoker = new Invoker();
        invoker.setCommand(command);
        invoker.executeCommand();


        // Strategy == Behavioral Pattern
        PaymentContext paymentContext = new PaymentContext();
        // Set Credit Card Payment Strategy
        paymentContext.setPaymentStrategy(new CreditCardPayment());
        paymentContext.performPayment(100);
        // Set PayPal Payment Strategy
        paymentContext.setPaymentStrategy(new PayPalPayment());
        paymentContext.performPayment(50);

    }
}
