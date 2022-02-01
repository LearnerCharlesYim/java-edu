package com.charles.guli.edu.config;

import cn.dev33.satoken.interceptor.SaRouteInterceptor;
import cn.dev33.satoken.router.SaHttpMethod;
import cn.dev33.satoken.router.SaRouter;
import cn.dev33.satoken.stp.StpUtil;
import com.charles.guli.edu.domain.pojo.Menu;
import com.charles.guli.edu.repository.MenuRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

@Configuration
@RequiredArgsConstructor
public class WebMvcConfig implements WebMvcConfigurer {

    private final MenuRepository menuRepository;

    // 注册拦截器
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // 注册Sa-Token的路由拦截器
        registry.addInterceptor(new SaRouteInterceptor((request, response, handler) -> {
            SaRouter.match("/**", "/user/login", r -> StpUtil.checkLogin());
            List<Menu> menus = menuRepository.findByType(Menu.Type.BUTTON);
            menus.forEach(menu -> {
                String path = menu.getPath();
                if (menu.getMethod() != null) {
                    if ("GET".equals(menu.getMethod())) {
                        SaRouter.match(SaHttpMethod.GET).match(path).check(r -> StpUtil.checkPermission(path));
                    } else if ("POST".equals(menu.getMethod())) {
                        SaRouter.match(SaHttpMethod.POST).match(path).check(r -> StpUtil.checkPermission(path));
                    } else if ("PUT".equals(menu.getMethod())) {
                        SaRouter.match(SaHttpMethod.PUT).match(path).check(r -> StpUtil.checkPermission(path));
                    } else if ("DELETE".equals(menu.getMethod())) {
                        SaRouter.match(SaHttpMethod.DELETE).match(path).check(r -> StpUtil.checkPermission(path));
                    } else {
                        SaRouter.match(path, r -> StpUtil.checkPermission(path));
                    }
                } else {
                    SaRouter.match(path, r -> StpUtil.checkPermission(path));
                }
            });

        })).excludePathPatterns(
                "/captcha",
                "/v2/api-docs/**",
                "/swagger-resources/**",
                "/doc.html",
                "/**/*.css",
                "/**/*.js",
                "/**/*.ico",
                "/webjars/**"
        );
    }
}
