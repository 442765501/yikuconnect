package com.yiku.listener;

import com.yiku.listener.service.ApService;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * Created by 10853 on 2018/4/10.
 */
@Component
public class ApListener implements ApplicationListener<ContextRefreshedEvent> {

    @Resource
    private ApService apService;
    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        if (event.getApplicationContext().getParent() == null && event == null ) {
            return;
        }
        apService.start();
    }

}
