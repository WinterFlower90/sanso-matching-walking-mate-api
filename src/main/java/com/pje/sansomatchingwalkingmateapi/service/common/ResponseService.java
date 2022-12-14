package com.pje.sansomatchingwalkingmateapi.service.common;

import com.pje.sansomatchingwalkingmateapi.enums.ResultCode;
import com.pje.sansomatchingwalkingmateapi.model.common.CommonResult;
import com.pje.sansomatchingwalkingmateapi.model.common.ListResult;
import com.pje.sansomatchingwalkingmateapi.model.common.SingleResult;
import org.springframework.stereotype.Service;

@Service

public class ResponseService {
    public static <T> ListResult<T> getListResult(ListResult<T> result, boolean isSuccess) {
        if (isSuccess) setSuccessResult(result);
        else setFailResult(result);
        return result;
    }

    public static <T> SingleResult<T> getSingleResult(T data) {
        SingleResult<T> result = new SingleResult<>();
        result.setData(data);
        setSuccessResult(result);
        return result;
    }

    public static CommonResult getSuccessResult() {
        CommonResult result = new CommonResult();
        setSuccessResult(result);
        return result;
    }

    public static CommonResult getFailResult(ResultCode resultCode) {
        CommonResult result = new CommonResult();
        result.setIsSuccess(false);
        result.setCode(resultCode.getCode());
        result.setMsg(resultCode.getMsg());
        return result;
    }

    private static void setSuccessResult(CommonResult result) {
        result.setIsSuccess(true);
        result.setCode(ResultCode.SUCCESS.getCode());
        result.setMsg(ResultCode.SUCCESS.getMsg());
    }

    private static void setFailResult(CommonResult result) {
        result.setIsSuccess(false);
        result.setCode(ResultCode.FAILED.getCode());
        result.setMsg(ResultCode.FAILED.getMsg());
    }
}

