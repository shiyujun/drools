package cn.org.zhixiang.utils;

import org.kie.api.KieServices;
import org.kie.api.builder.KieBuilder;
import org.kie.api.builder.KieFileSystem;
import org.kie.api.builder.Message;
import org.kie.api.builder.Results;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.UnsupportedEncodingException;

/**
 * d
 *
 * @author syj
 * CreateTime 2018/11/23
 * describe:
 */
@Component
public class RulesUtil {
    @Autowired
    private KieContainer kieContainer;
    @Autowired
    private KieSession kieSession;

    public void reload(String rules, String fileName) throws UnsupportedEncodingException {
        KieServices kieServices = KieServices.Factory.get();
        KieFileSystem kfs = kieServices.newKieFileSystem();
        kfs.write("src/main/resources/rules/" + fileName, rules);
        KieBuilder kieBuilder = kieServices.newKieBuilder(kfs).buildAll();
        Results results = kieBuilder.getResults();
        if (results.hasMessages(Message.Level.ERROR)) {
            System.out.println(results.getMessages());
            throw new IllegalStateException("### errors ###");
        }
        kieContainer = kieServices.newKieContainer(getKieServices().getRepository().getDefaultReleaseId());
        kieSession = kieContainer.newKieSession();
    }

    public static KieServices getKieServices() {
        return KieServices.Factory.get();
    }
}
