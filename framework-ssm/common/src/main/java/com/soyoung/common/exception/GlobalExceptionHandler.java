package com.soyoung.common.exception;

import com.soyoung.common.base.BaseEnum;
import com.soyoung.common.entity.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.ConversionNotSupportedException;
import org.springframework.beans.TypeMismatchException;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.HttpMessageNotWritableException;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.NoHandlerFoundException;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;

/**
 * 全局异常处理类，拦截controller  RestControllerAdvice此注解为ResponseBody和ControllerAdvice混合注解
 * 全局异常类中定义的异常都可以被拦截，只是触发条件不一样，如IO异常这种必须抛出异常到
 * controller中才可以被拦截，或者在类中用try..catch自己处理
 * 绝大部分不需要向上抛出异常即可被拦截，返回前端json数据，如数组下标越界，404 500 400等错误
 * 如果自己想要写，按着以下格式增加异常即可
 */
@RestControllerAdvice
public class GlobalExceptionHandler {

    private static final Logger log = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    /**
     * 系统错误，未知的错误
     *
     * @param ex 异常信息
     * @return 返回前端异常信息
     */
    @ExceptionHandler({Exception.class})
    public Result exception(Exception ex) {
        log.error("错误详情：" + ex.getMessage(), ex);
        return Result.errorJson(BaseEnum.SYSTEM_ERROR.getCode(), BaseEnum.SYSTEM_ERROR.getMsg());
    }

    /**
     * 文件没有找到错误拦截  要把错误信息抛出到controller层
     *
     * @param ex 异常信息
     * @return 返回前端异常信息
     */
    @ExceptionHandler(FileNotFoundException.class)
    public Result fileNotFound(FileNotFoundException ex) {
        log.error("错误详情：" + ex.getMessage(), ex);
        return Result.errorJson(BaseEnum.FILE_NOT_FOUND.getCode(), BaseEnum.FILE_NOT_FOUND.getMsg());
    }


    /**
     * 字符串转换为数字异常 不需要抛出到ccontroller即可被拦截
     *
     * @param ex 异常信息
     * @return 返回前端异常信息
     */
    @ExceptionHandler(NumberFormatException.class)
    public Result numberFormatEx(NumberFormatException ex) {
        log.error("错误详情：" + ex.getMessage(), ex);
        return Result.errorJson(BaseEnum.NUMBER_FORMAT.getCode(), BaseEnum.NUMBER_FORMAT.getMsg());
    }


    /**
     * sql操作数据库出错了
     *
     * @param ex 异常信息
     * @return 返回前端异常信息
     */
    @ExceptionHandler(SQLException.class)
    public Result sqlException(SQLException ex) {
        log.error("错误详情：" + ex.getMessage(), ex);
        return Result.errorJson(BaseEnum.SQL_EXCEPTION.getCode(), BaseEnum.SQL_EXCEPTION.getMsg());
    }


    /**
     * 参数传递出错了
     *
     * @param ex 异常信息
     * @return 返回前端异常信息
     */
    @ExceptionHandler(IllegalArgumentException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Result sqlException(IllegalArgumentException ex) {
        log.error("错误详情：" + ex.getMessage(), ex);
        return Result.errorJson(BaseEnum.ILLEGAL_ARGUMENT.getCode(), BaseEnum.ILLEGAL_ARGUMENT.getMsg());
    }


    /**
     * 栈溢出错误
     *
     * @param ex 异常信息
     * @return 返回前端异常信息
     */
    @ExceptionHandler(StackOverflowError.class)
    public Result stackOverflow(StackOverflowError ex) {
        log.error("错误详情：" + ex.getMessage(), ex);
        return Result.errorJson(BaseEnum.STACK_OVERFLOW.getCode(), BaseEnum.STACK_OVERFLOW.getMsg());
    }


    /**
     * 404错误拦截
     *
     * @param ex 异常信息
     * @return 返回前端异常信息
     */
    @ExceptionHandler(NoHandlerFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public Result noHandlerNotFound(NoHandlerFoundException ex) {
        log.error("错误详情：" + ex.getMessage(), ex);
        return Result.errorJson(BaseEnum.NO_HANDLER.getCode(), BaseEnum.NO_HANDLER.getMsg());
    }


    /**
     * 400错误拦截
     *
     * @param ex 异常信息
     * @return 返回前端异常信息
     */
    @ExceptionHandler(TypeMismatchException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Result request400(TypeMismatchException ex) {
        log.error("错误详情：" + ex.getMessage(), ex);
        return Result.errorJson(BaseEnum.BAD_REQUEST.getCode(), BaseEnum.BAD_REQUEST.getMsg());
    }


    /**
     * 400错误拦截
     *
     * @param ex 异常信息
     * @return 返回前端异常信息
     */
    @ExceptionHandler(MissingServletRequestParameterException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Result request400(MissingServletRequestParameterException ex) {
        log.error("错误详情：" + ex.getMessage(), ex);
        return Result.errorJson(BaseEnum.BAD_REQUEST.getCode(), BaseEnum.BAD_REQUEST.getMsg() + "   找不到传入的参数");
    }


    /**
     * 400错误拦截
     *
     * @param ex 异常信息
     * @return 返回前端异常信息
     */
    @ExceptionHandler(HttpMessageNotReadableException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Result request400(HttpMessageNotReadableException ex) {
        log.error("错误详情：" + ex.getMessage(), ex);
        return Result.errorJson(BaseEnum.BAD_REQUEST.getCode(), BaseEnum.BAD_REQUEST.getMsg() + "    可能缺少参数");
    }


    /**
     * 405错误拦截
     *
     * @param ex 异常信息
     * @return 返回前端异常信息
     */
    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    @ResponseStatus(HttpStatus.METHOD_NOT_ALLOWED)
    public Result request405(HttpRequestMethodNotSupportedException ex) {
        log.error("错误详情：" + ex.getMessage(), ex);
        return Result.errorJson(BaseEnum.METHOD_NOT_ALLOWED.getCode(), BaseEnum.METHOD_NOT_ALLOWED.getMsg());
    }


    /**
     * 500错误拦截
     *
     * @param ex 异常信息
     * @return 返回前端异常信息
     */
    @ExceptionHandler({ConversionNotSupportedException.class, HttpMessageNotWritableException.class})
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public Result request500(RuntimeException ex) {
        log.error("错误详情：" + ex.getMessage(), ex);
        return Result.errorJson(BaseEnum.INTERNAL_SERVER_ERROR.getCode(), BaseEnum.INTERNAL_SERVER_ERROR.getMsg());
    }


    /**
     * 类型转换异常 可以拦截
     *
     * @param ex 异常信息
     * @return 返回前端异常信息
     */
    @ExceptionHandler(ClassCastException.class)
    public Result classCastExceptionHandler(ClassCastException ex) {
        log.error("错误详情：" + ex.getMessage(), ex);
        return Result.errorJson(BaseEnum.CLASS_CAST.getCode(), BaseEnum.CLASS_CAST.getMsg());
    }


    /**
     * 未知方法异常
     *
     * @param ex 异常信息
     * @return 返回前端异常信息
     */
    @ExceptionHandler(NoSuchMethodException.class)
    public Result noSuchMethodExceptionHandler(NoSuchMethodException ex) {
        log.error("错误详情：" + ex.getMessage(), ex);
        return Result.errorJson(BaseEnum.NO_SUCH_METHOD.getCode(), BaseEnum.NO_SUCH_METHOD.getMsg());
    }


    /**
     * IO异常 需要抛出到Controller层可捕获到
     *
     * @param ex 异常信息
     * @return 返回前端异常信息
     */
    @ExceptionHandler(IOException.class)
    public Result iOExceptionHandler(IOException ex) {
        log.error("错误详情：" + ex.getMessage(), ex);
        return Result.errorJson(BaseEnum.IO_EXCEPTION.getCode(), BaseEnum.IO_EXCEPTION.getMsg());
    }


    /**
     * 空指针异常
     *
     * @param ex 异常信息
     * @return 返回前端异常信息
     */
    @ExceptionHandler(NullPointerException.class)
    public Result nullPointerExceptionHandler(NullPointerException ex) {
        log.error("错误详情：" + ex.getMessage(), ex);
        return Result.errorJson(BaseEnum.NULL_POINTER.getCode(), BaseEnum.NULL_POINTER.getMsg());
    }


    /**
     * 数组越界异常拦截
     *
     * @param ex 异常信息
     * @return 返回前端异常信息
     */
    @ExceptionHandler(IndexOutOfBoundsException.class)
    public Result indexOutOfBoundsExceptionHandler(IndexOutOfBoundsException ex) {
        log.warn("错误详情：" + ex.getMessage(), ex);
        return Result.errorJson(BaseEnum.INDEX_OUT_BOUNDS.getCode(), BaseEnum.INDEX_OUT_BOUNDS.getMsg());
    }


    /**
     * 用户不存在异常拦截
     *
     * @param ex 异常信息
     * @return 返回前端异常信息
     */
    @ExceptionHandler(InternalAuthenticationServiceException.class)
    public Result internalAuthenticationServiceExceptionHandler(InternalAuthenticationServiceException ex) {
        log.warn("错误详情：" + ex.getMessage(), ex);
        return Result.errorJson(BaseEnum.PASSWORD_ERROR.getCode(), BaseEnum.PASSWORD_ERROR.getMsg());
    }


    /**
     * 自定义异常信息拦截
     *
     * @param ex 异常信息
     * @return 返回前端异常信息
     */
    @ExceptionHandler(MyException.class)
    public Result myCustomizeException(MyException ex) {
        log.warn("错误详情：" + ex);
        return Result.errorJson(BaseEnum.CUSTOMIZE_EXCEPTION.getCode(), ex.getMessage());
    }

}
