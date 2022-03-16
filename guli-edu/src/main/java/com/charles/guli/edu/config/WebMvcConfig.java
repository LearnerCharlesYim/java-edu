package com.charles.guli.edu.config;

import cn.dev33.satoken.interceptor.SaRouteInterceptor;
import cn.dev33.satoken.router.SaHttpMethod;
import cn.dev33.satoken.router.SaRouter;
import cn.dev33.satoken.stp.StpUtil;
import com.charles.common.interceptor.ResponseResultIntercept;
import com.charles.guli.edu.domain.pojo.Menu;
import com.charles.guli.edu.repository.MenuRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.ArrayList;
import java.util.List;

@Configuration
@RequiredArgsConstructor
@Slf4j
public class WebMvcConfig implements WebMvcConfigurer {

    private final MenuRepository menuRepository;
    private List<Menu> menus = new ArrayList<>();

    // 注册拦截器
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new SaRouteInterceptor((request, response, handler) -> {
                    // 静态资源放行
                    if (!(handler instanceof HandlerMethod)) return;
                    log.info("------------------进入SaToken拦截器------------------");

                    SaRouter.match("/**", r -> StpUtil.checkLogin());
                    if (menus.isEmpty()) {
                        menus = menuRepository.findByType(Menu.Type.BUTTON);
                    }
                    menus.forEach(menu -> {
                        // 请求路径
                        String path = menu.getPath();
                        // 请求方法
                        String method = menu.getMethod();
                        if (method != null) {
                            if ("GET".equalsIgnoreCase(method)) {
                                SaRouter.match(SaHttpMethod.GET).match(path).check(r -> StpUtil.checkPermission(path));
                            } else if ("POST".equalsIgnoreCase(method)) {
                                SaRouter.match(SaHttpMethod.POST).match(path).check(r -> StpUtil.checkPermission(path));
                            } else if ("PUT".equalsIgnoreCase(method)) {
                                SaRouter.match(SaHttpMethod.PUT).match(path).check(r -> StpUtil.checkPermission(path));
                            } else if ("DELETE".equalsIgnoreCase(method)) {
                                SaRouter.match(SaHttpMethod.DELETE).match(path).check(r -> StpUtil.checkPermission(path));
                            } else {
                                SaRouter.match(path, r -> StpUtil.checkPermission(path));
                            }
                        } else {
                            SaRouter.match(path, r -> StpUtil.checkPermission(path));
                        }
                    });
                }))
                .excludePathPatterns(
                        "/user/login",
                        "/captcha",
                        "/v2/api-docs/**",
                        "/swagger-resources/**",
                        "/doc.html",
                        "/**/*.css",
                        "/**/*.js",
                        "/**/*.ico",
                        "/webjars/**",
                        "/error",
                        "/online"
                );

        registry.addInterceptor(new ResponseResultIntercept()).addPathPatterns("/**");
    }
}
