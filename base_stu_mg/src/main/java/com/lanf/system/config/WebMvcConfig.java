package com.lanf.system.config;

import com.lanf.stu.interceptor.LoginInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        //添加资源映射规则，默认的前缀是：http://localhost:port
        registry.addResourceHandler("/img/**")
                .addResourceLocations("file:" + System.getProperty("user.dir") + "\\src\\main\\resources\\static\\img\\");
        //自定义路径webjars访问，addResourceLocations映射该路径下的资源，resourceChain资源链
        registry.addResourceHandler("/temp/**").addResourceLocations("classpath:/temp/").resourceChain(true);
    }

    @Autowired
    private LoginInterceptor loginInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        //注册⾃定义拦截器对象
               }
}
