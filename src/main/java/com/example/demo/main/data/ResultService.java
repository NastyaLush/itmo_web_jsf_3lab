package com.example.demo.main.data;

import java.util.List;

public interface ResultService {
    public List<Result> getResult(String token);
    public Result saveResult(Result result, String token, long scriptStart);

}
