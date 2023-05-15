package com.example.demo.main.data;

import jakarta.transaction.Transactional;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;
@Transactional
public class ResultServiceImpl implements ResultService {
    private final ResultRepository resultRepository;


    public ResultServiceImpl(ResultRepository resultRepository) {
        this.resultRepository = resultRepository;
    }
    @Override
    public List<Result> getResult(String token){
        System.out.println("get all results");
        return token!=null?resultRepository.findAll(token):new ArrayList<>();
    }
    @Override
    public Result saveResult(Result result, String token, long scriptStart){
        System.out.println("add new result: "+result);
        result.setScriptTime(new Date().getTime()-scriptStart);
        return (token!=null && result!=null)?resultRepository.save(result, token):null;
    }

}
