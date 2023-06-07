package com.example.demo.mbean;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import javax.management.MBeanServer;
import javax.management.ObjectName;
import java.io.Serializable;
import java.lang.management.ManagementFactory;

@Named("squareUtil")
@ViewScoped
public class SquareUtil implements Serializable {
    private static final long serialVersionUID = 222L;
    private Square square;

    @PostConstruct
    private void init() {
        try {
            MBeanServer mBeanServer = ManagementFactory.getPlatformMBeanServer();
            ObjectName interval = new ObjectName("com/example/demo/mbean:type=Square");
            square = new Square();
            mBeanServer.registerMBean(square, interval);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void updateRadius(double r) {
        int radius = (int) r;
        square.updateSquare(radius);
    }
}
