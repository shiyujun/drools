package cn.org.zhixiang.Controller;

import cn.org.zhixiang.config.DroolsAutoConfiguration;
import cn.org.zhixiang.entity.Dog;
import org.kie.api.runtime.KieSession;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.annotation.AnnotatedBeanDefinition;
import org.springframework.beans.factory.annotation.AnnotatedGenericBeanDefinition;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.BeanDefinitionHolder;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.BeanDefinitionReaderUtils;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

/**
 * d
 *
 * @author syj
 * CreateTime 2018/11/26
 * describe:
 */
@RestController
@RequestMapping("/drools")
public class BaseController {
    @Autowired
    private KieSession kieSession;
    @Autowired
    private Dog dog;
    @Autowired
    private DroolsAutoConfiguration droolsAutoConfiguration;

    @Autowired
    private ConfigurableApplicationContext context;


    /*@GetMapping("/reload")
    public String reload() {

        KieSession k1 = kieSession;
        BeanDefinitionBuilder builder = BeanDefinitionBuilder.genericBeanDefinition(KieSession.class, () -> {
            KieSession k = null;
            try {
                k = droolsAutoConfiguration.kieSession();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return k;
        });
        BeanDefinition beanDefinition = builder.getRawBeanDefinition();
        BeanDefinitionRegistry beanDefinitionRegistry= (BeanDefinitionRegistry) context.getBeanFactory();
        beanDefinitionRegistry.removeBeanDefinition("kieSession");
        beanDefinitionRegistry.registerBeanDefinition("kieSession", beanDefinition);

        System.out.println(kieSession == k1);
        return "success";
    }*/
    @GetMapping("/reload")
    public String reload() {

        Dog k1 = dog;
        BeanDefinitionBuilder builder = BeanDefinitionBuilder.genericBeanDefinition(Dog.class, () -> {
            Dog k = null;
            try {
                k = droolsAutoConfiguration.dog();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return k;
        });
        BeanDefinition beanDefinition = builder.getRawBeanDefinition();
        BeanDefinitionRegistry beanDefinitionRegistry= (BeanDefinitionRegistry) context.getBeanFactory();
        beanDefinitionRegistry.removeBeanDefinition("dog");
        beanDefinitionRegistry.registerBeanDefinition("dog", beanDefinition);
        context.refresh();
        return "success";
    }
}
