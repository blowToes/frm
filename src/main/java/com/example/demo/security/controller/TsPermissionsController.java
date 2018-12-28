package com.example.demo.security.controller;


import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.mvc.condition.PatternsRequestCondition;
import org.springframework.web.servlet.mvc.method.RequestMappingInfo;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.*;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author jobob
 * @since 2018-12-17
 */
@Api(description = "权限管理")
@RestController
@RequestMapping("/security/ts-permissions")
public class TsPermissionsController {
    private final static Logger LOGGER = LoggerFactory.getLogger(TsPermissionsController.class);
    @Autowired
    private WebApplicationContext webApplicationContext;

    // 获取所有的接口
    @ApiOperation("获取所有的接口的URL")
    @GetMapping("/con/getUrl")
    public List<Map<String, Object>> getUrlAll() {
        RequestMappingHandlerMapping requestMapping = webApplicationContext.getBean(RequestMappingHandlerMapping.class);
        Map<RequestMappingInfo, HandlerMethod> handlerMethods = requestMapping.getHandlerMethods();
        List<Map<String, Object>> list = Lists.newArrayList();

        Set<Class<?>> set = Sets.newHashSet();
        // 获取所有不重复的Controller
        getControllerList(handlerMethods, set);
        // 构造数据
        set.forEach(ele -> {
            String name = ele.getName();
            if (!name.endsWith("Swagger2Controller")
                    && !name.endsWith("ApiResourceController")
                    && !name.endsWith("BasicErrorController")) {
                // 获取Controller注解
                Api annotation = ele.getDeclaredAnnotation(Api.class);
                String value = annotation.description();
                // 存放一个Controller中的 所有的URL
                List<Map<String, String>> controllerUrlList = Lists.newArrayList();
                // 获取Controller上的url的公共前缀
                RequestMapping requestMappings = ele.getDeclaredAnnotation(RequestMapping.class);
                // 获取一个Controller中的所有的方法
                Map<String, Object> controllerUrls = getControllerUrls(ele, value, controllerUrlList, requestMappings.value()[0]);
                list.add(controllerUrls);
            }
        });
        return list;
    }

    private void getControllerList(Map<RequestMappingInfo, HandlerMethod> handlerMethods, Set<Class<?>> set) {
        for (Map.Entry<RequestMappingInfo, HandlerMethod> entry : handlerMethods.entrySet()) {
            Class<?> beanType = entry.getValue().getBeanType();
            set.add(beanType);
        }
    }

    /**
     * 构造每个controller元素中包含
     * 的接口映射
     *
     * @param beanType          Controller 类
     * @param value             Api 单个控制器的注解
     * @param controllerUrlList 单个控制器包含的url注解
     * @param requestMappe      控制器的所有接口的url前缀
     */
    private Map<String, Object> getControllerUrls(Class<?> beanType,
                                                  String value,
                                                  List<Map<String, String>> controllerUrlList,
                                                  String requestMappe) {
        Map<String, Object> maps = Maps.newHashMap();
        Method[] declaredMethods = beanType.getDeclaredMethods();
        for (Method method : declaredMethods) {
            // 获取
            Annotation[] declaredAnnotations = method.getDeclaredAnnotations();
            // 获取某个方法的响应的注解和URL
            // 获取URL和注解
            if (0 != declaredAnnotations.length) {
                Map<String, String> map = Maps.newHashMap();
                for (Annotation at : declaredAnnotations) {
                    getUrlMappe(at, map);
                }
                controllerUrlList.add(map);
            }
        }
        maps.put("requestMappe", requestMappe);
        maps.put("cotroller", value);
        maps.put("urlList", controllerUrlList);
        return maps;
    }

    /**
     * 获取注解中需要的值（接口url和接口注解）
     *
     * @param at
     * @param map
     * @return
     */
    private Map<String, String> getUrlMappe(Annotation at, Map<String, String> map) {
        String name = at.annotationType().getName();
        if (name.endsWith("ApiOperation")) {
            ApiOperation apiOperation = (ApiOperation) at;
            map.put("handlerDescription", apiOperation.value());
        }
        if (name.endsWith("GetMapping")) {
            GetMapping getMapping = (GetMapping) at;
            map.put("url", getMapping.value()[0]);
        }
        if (name.endsWith("PostMapping")) {
            PostMapping getMapping = (PostMapping) at;
            map.put("url", getMapping.value()[0]);
        }
        if (name.endsWith("PutMapping")) {
            PutMapping getMapping = (PutMapping) at;
            map.put("url", getMapping.value()[0]);
        }
        if (name.endsWith("DeleteMapping")) {
            DeleteMapping getMapping = (DeleteMapping) at;
            map.put("url", getMapping.value()[0]);
        }
        if (at.toString().endsWith("PatchMapping")) {
            PatchMapping getMapping = (PatchMapping) at;
            map.put("url", getMapping.value()[0]);
        }
        return map;
    }

}
