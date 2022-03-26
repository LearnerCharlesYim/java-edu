package com.charles.guli.edu.config;

import cn.dev33.satoken.interceptor.SaRouteInterceptor;
import cn.dev33.satoken.router.SaHttpMethod;
import cn.dev33.satoken.router.SaRouter;
import cn.dev33.satoken.stp.StpUtil;
import cn.hutool.core.date.DateUtil;
import com.charles.common.annotation.LoginUser;
import com.charles.common.interceptor.AccessLimitInterceptor;
import com.charles.guli.edu.domain.pojo.Menu;
import com.charles.guli.edu.domain.pojo.User;
import com.charles.guli.edu.domain.vo.Pet;
import com.charles.guli.edu.repository.MenuRepository;
import com.charles.guli.edu.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.MethodParameter;
import org.springframework.core.convert.converter.Converter;
import org.springframework.format.Formatter;
import org.springframework.format.FormatterRegistry;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

@Configuration
@RequiredArgsConstructor
@Slf4j
public class WebMvcConfig implements WebMvcConfigurer {

    private final MenuRepository menuRepository;
    private final UserRepository userRepository;
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

//        registry.addInterceptor(new ResponseResultIntercept()).addPathPatterns("/**");

        registry.addInterceptor(new AccessLimitInterceptor()).addPathPatterns("/**");
    }

    @Override
    public void addFormatters(FormatterRegistry registry) {
        registry.addFormatter(new Formatter<Date>() {
            private final String FORMAT = "yyyy|MM|dd";

            @Override
            public Date parse(String s, Locale locale) throws ParseException {
                return DateUtil.parse(s, FORMAT);
            }

            @Override
            public String print(Date date, Locale locale) {
                return DateUtil.format(date, FORMAT);
            }
        });
        registry.addConverter(new Converter<String, Pet>() {
            @Override
            public Pet convert(String s) {
                if (!StringUtils.hasText(s)) return null;
                Pet pet = new Pet();
                String[] strings = s.split(",");
                pet.setName(strings[0]);
                pet.setAge(Integer.parseInt(strings[1]));
                return pet;
            }
        });
    }

    // 注册参数解析器
    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> resolvers) {
        resolvers.add(new HandlerMethodArgumentResolver() {
            @Override
            public boolean supportsParameter(MethodParameter methodParameter) {
                return methodParameter.hasParameterAnnotation(LoginUser.class) &&
                        methodParameter.getParameterType().equals(User.class);
            }

            @Override
            public Object resolveArgument(MethodParameter methodParameter,
                                          ModelAndViewContainer modelAndViewContainer,
                                          NativeWebRequest nativeWebRequest,
                                          WebDataBinderFactory webDataBinderFactory) throws Exception {
                String username = (String) StpUtil.getSession().get("loginUser");
                return userRepository.findByUsername(username);
            }
        });
    }
}
