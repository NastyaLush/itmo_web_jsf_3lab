package com.example.demo.main;

import com.example.demo.DBUtil.DataBase;
import com.example.demo.mbean.PointManager;
import com.example.demo.validation.Validation;
import com.example.demo.data.Result;

import javax.annotation.PostConstruct;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import javax.management.MBeanServer;
import javax.management.ObjectName;
import java.io.Serializable;
import java.lang.management.ManagementFactory;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;


@Named("userData")
@ViewScoped
public class UserData implements Serializable {
    private static final long serialVersionUID = 2L;
    private final SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd  HH:mm:ss ");
    private final DataBase dataBase;
    private final Validation validation;
    private PointManager pointManager;


    public UserData() {
        dataBase = new DataBase();
        validation = new Validation();
    }

    public List<Result> getResults() {
        return dataBase.listResult().stream().sorted((e1, e2) -> {
            try {
                int result = formatter.parse(e1.getDate()).compareTo(formatter.parse(e2.getDate()));
                if (result == 1) {
                    return -1;
                }
                return result == 0 ? 0 : 1;
            } catch (ParseException e) {
                throw new RuntimeException(e);
            }
        }).collect(Collectors.toUnmodifiableList());
    }


    @PostConstruct
    private void init() {
        try {
            MBeanServer mBeanServer = ManagementFactory.getPlatformMBeanServer();
            ObjectName interval = new ObjectName("com/example/demo/mbean:type=PointManager");
            pointManager = new PointManager();
            mBeanServer.registerMBean(pointManager, interval);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void setResult(Result result) {
        dataBase.setResult(validation.checkResult(result, System.nanoTime(), pointManager));
    }

    public void setResultFromGraph() {
        final Map<String, String> params = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
        System.out.println(params);
        double x = Double.parseDouble(params.get("x"));
        double y = Double.parseDouble(params.get("y"));
        double r = Double.parseDouble(params.get("r"));
        System.out.println(x+" "+y+" "+r);
        Result result = new Result();
        result.setX(x);
        result.setY(y);
        result.setR(r);
        System.out.println(result);
        setResult(result);
    }

    public void clearBase() {
        dataBase.clearBase();
    }

}
