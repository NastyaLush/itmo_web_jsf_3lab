package com.example.demo.main.data;

import jakarta.transaction.Transactional;


import java.util.List;
@Transactional
public class ResultServiceImpl implements ResultService {
    private final ResultRepository resultRepository;


    public ResultServiceImpl(ResultRepository resultRepository) {
        this.resultRepository = resultRepository;
    }
    @Override
    public List<Result> getResult(){
        System.out.println("get all results");
        System.out.println(resultRepository.findAll());
        return resultRepository.findAll();
    }
    @Override
    public Result saveResult(Result result){
        System.out.println("add new result: "+result);
        return resultRepository.save(result);
    }

}
