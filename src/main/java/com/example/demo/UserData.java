package com.example.demo;




import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import java.beans.Transient;
import java.io.Serializable;
import java.sql.Time;
import java.util.*;

@Named("userData")
@ViewScoped
public class UserData implements Serializable {
    private static final long serialVersionUID = 2L;
    private final DataBase dataBase;
    private final Validation validation;


    public UserData() {
        dataBase = new DataBase();
        validation = new Validation();
    }

    public List<Result> getResults() {
        return dataBase.listResult();
 }


    public void setResult(Result result) {
        dataBase.setResult(validation.checkResult(result, System.nanoTime()));
    }
    public void setResultFromGraph() {
        final Map<String, String> params = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
        System.out.println(params);;
        double x = Double.parseDouble(params.get("x"));
        double y = Double.parseDouble(params.get("y"));
        double r = Double.parseDouble(params.get("r"));
        Result result = new Result();
        result.setX(x);
        result.setY(y);
        result.setR(r);
        System.out.println(result);
        setResult(result);
    }
    public void clearBase(){
        dataBase.clearBase();
    }

}