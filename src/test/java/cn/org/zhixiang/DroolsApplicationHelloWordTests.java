package cn.org.zhixiang;

import cn.org.zhixiang.entity.User;
import org.drools.core.base.RuleNameEndsWithAgendaFilter;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.kie.api.runtime.KieSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DroolsApplicationHelloWordTests {

    @Autowired
    KieSession kieSession;


    @Test
    public void testHelloWord() {
        kieSession.fireAllRules();
    }
    @Test
    public void testUser() {
        User user=new User("张三",18);
        kieSession.insert(user);
        kieSession.fireAllRules();
        System.err.println("规则执行完毕后张三变为了："+user.getName());
    }
    @Test
    public void testOneRule() {
        User user=new User("张三",18);
        kieSession.insert(user);
        kieSession.fireAllRules(new RuleNameEndsWithAgendaFilter("user"));
    }

    @Test
    public void testContains() {
       String name="张三";
        User user=new User("张三",18);
        kieSession.insert(name);
        kieSession.insert(user);
        kieSession.fireAllRules(new RuleNameEndsWithAgendaFilter("contains"));
    }
    @Test
    public void testMemberOf() {
        List list=new ArrayList();
        list.add("张三");
        list.add("李四");
        User user=new User("李四",18);
        kieSession.insert(list);
        kieSession.insert(user);
        kieSession.fireAllRules(new RuleNameEndsWithAgendaFilter("memberOf"));
    }
    @Test
    public void testMatches() {
        User user=new User("张三",18);
        kieSession.insert(user);
        kieSession.fireAllRules(new RuleNameEndsWithAgendaFilter("matches"));
    }



}
