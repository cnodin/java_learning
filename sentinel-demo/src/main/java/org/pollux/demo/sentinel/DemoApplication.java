package org.pollux.demo.sentinel;

import com.alibaba.csp.sentinel.Entry;
import com.alibaba.csp.sentinel.SphU;
import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.alibaba.csp.sentinel.slots.block.RuleConstant;
import com.alibaba.csp.sentinel.slots.block.flow.FlowRule;
import com.alibaba.csp.sentinel.slots.block.flow.FlowRuleManager;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.ArrayList;
import java.util.List;

//@SpringBootApplication
public class DemoApplication {

	public static void main(String[] args) {
		//SpringApplication.run(DemoApplication.class, args);
    initFlowRules();

    while (true) {
      try (Entry entry = SphU.entry("HelloWorld")) {
        System.out.println("hello, world");
      }catch (BlockException be) {
        //处理被流控的逻辑
        System.out.println("blocked!");
      }
    }
	}

  @SentinelResource("HelloWorld")
  public void helloWorld() {
    System.out.println("hello world");
  }

	private static void initFlowRules() {
    List<FlowRule> rules = new ArrayList();
    FlowRule rule = new FlowRule()
            .setRefResource("HelloWorld")
            .setGrade(RuleConstant.FLOW_GRADE_QPS)
            .setCount(10)
            .as(FlowRule.class);
    rules.add(rule);
    FlowRuleManager.loadRules(rules);
  }

}
