package com.xiatianlong.signatrue.exception;

import com.xiatianlong.signatrue.model.AsynchronousResult;
import com.xiatianlong.signatrue.utils.AsynchronousRequestUtil;
import com.xiatianlong.signatrue.utils.SerializeUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Locale;

/**
 * 全局异常处理
 * Created by xiatianlong on 2017/12/28.
 */
@Component
public class ApplicationExceptionResolver implements HandlerExceptionResolver {

    @Autowired
    protected MessageSource messageSource;

    /**
     * single message
     *
     * @param messageKey message key
     * @return message
     */
    protected String getMessage(String messageKey) {
        return messageSource.getMessage(messageKey, null, Locale.CHINA);
    }

    @Override
    public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {

        // 打印异常信息
        System.out.println("进入异常处理--------------");
        ex.printStackTrace();
        try {
            ApplicationException applicationException;

            // 如果是系统手动抛出的异常直接转换
            if (ex instanceof ApplicationException) {
                applicationException = (ApplicationException) ex;
            } else {
                // 其他未知异常
                applicationException = new ApplicationException("未知异常");
            }

            // 向前端返回异常信息
//            if (AsynchronousRequestUtil.isAsynchronousRequest(request)) {
                // 异步
                AsynchronousResult result = new AsynchronousResult();
                result.setMessage(applicationException.getMessage());
                response.setContentType("application/json; charset=UTF-8");
                response.getWriter().print(SerializeUtil.serialize(result));
                return new ModelAndView();

//            } else {
//                // 同步
//                ModelAndView modelAndView = new ModelAndView("error/exception");
//                modelAndView.addObject("errorMessage", applicationException.getMessage());
//                return modelAndView;
//            }
        } catch (Exception e) {
            // do nothing
            e.printStackTrace();
        }

        return new ModelAndView();
    }

}