package cn.org.zhixiang;

import org.drools.core.base.RuleNameEndsWithAgendaFilter;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.kie.api.runtime.KieSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;


@RunWith(SpringRunner.class)
@SpringBootTest
public class DroolsApplicationPropertyTests {

    @Autowired
    KieSession kieSession;


    @Test
    public void testSalience() {
        kieSession.fireAllRules();
    }

    @Test
    public void testDateEffective() {
        kieSession.fireAllRules(new RuleNameEndsWithAgendaFilter("dateEffective"));
    }

    @Test
    public void testDateEffectiveTomorrow() {
        kieSession.fireAllRules(new RuleNameEndsWithAgendaFilter("dateEffectiveTomorrow"));
    }
    @Test
    public void testEnabled() {
        kieSession.fireAllRules(new RuleNameEndsWithAgendaFilter("enabled"));
    }

}
