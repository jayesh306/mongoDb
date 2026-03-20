package com.example.Java8Lab.interfaces.interfacerunner;

import com.example.Java8Lab.BaseRunner;
import com.example.Java8Lab.interfaces.defaultmethoddemo.CrediCardPayment;
import com.example.Java8Lab.interfaces.defaultmethoddemo.PaymentService;
import com.example.Java8Lab.interfaces.defaultmethoddemo.TaxCalculator;
import com.example.Java8Lab.interfaces.diamondproblem.C;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
@Order(12)
public class InterfaceRunner extends BaseRunner {
    @Override
    protected void execute() {
        PaymentService payment = new CrediCardPayment();
        payment.processPayment(1000);
        payment.printReceipt(100);
        double gst = TaxCalculator.calculateGST(100);
        System.out.println("GST : "+gst);

        C obj = new C();
        obj.display();
    }
}
/* default and static methods were introduced in Java 8 to enable backward compatibility and
allows API evolution without breaking implementations.
Default methods were introduced to allow interfaces to evolve without breaking existing
implementations. They help maintain backward compatibility when adding new methods to widely
used interfaces like Collection.
Q. Can default methods override Object methods?
No, if interface defines -> default String toString();
It does not override Object's toString(). Class hierarchy wins over interface. Rule priority:
1. Class methods    2. Most specific interface  3. Explicit override required
 */