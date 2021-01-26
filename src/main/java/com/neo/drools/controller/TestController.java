package com.neo.drools.controller;

import com.neo.drools.model.Address;
import com.neo.drools.model.fact.AddressCheckResult;
import com.neo.drools.service.ReloadDroolsRulesService;

import org.drools.core.base.RuleNameEndsWithAgendaFilter;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.io.IOException;


@RequestMapping("/test")
@Controller
public class TestController {

    @Resource
    private ReloadDroolsRulesService rules;

    @ResponseBody
    @RequestMapping("/address")
    public void test(@RequestParam("num")int num){
        Address address = new Address();
        address.setPostcode(generateRandom(num));
        KieSession kieSession = ReloadDroolsRulesService.kieContainer.newKieSession();

        AddressCheckResult result = new AddressCheckResult();
        kieSession.insert(address);
        kieSession.insert(result);
        //获取所有规则
        int ruleFiredCount = kieSession.fireAllRules();
        //指定规则
//        int ruleFiredCount = kieSession.fireAllRules(new RuleNameEndsWithAgendaFilter("Postcode should be filled with exactly 5 numbers"));
        kieSession.destroy();
        System.out.println("触发了" + ruleFiredCount + "条规则");
        if(result.isPostCodeResult()){
        	System.out.println("number:"+address.getPostcode());
            System.out.println("规则校验通过"+result.isPostCodeResult());
        }

    }
    
    @ResponseBody
    @RequestMapping("/address_two")
    public void address_two(@RequestParam("postcode")int postcode,@RequestParam("controlCable")int controlCable,
    		@RequestParam("uncontrolCable")int uncontrolCable,@RequestParam("unAndControlCable")int unAndControlCable){
        Address address = new Address();
        address.setPostcode(postcode);
        address.setControlCable(controlCable);
        address.setUncontrolCable(uncontrolCable);
        address.setUnAndControlCable(unAndControlCable);
        
        KieSession kieSession = ReloadDroolsRulesService.kieContainer.newKieSession();

        AddressCheckResult result = new AddressCheckResult();
        kieSession.insert(address);
        kieSession.insert(result);
        //获取所有规则
        int ruleFiredCount = kieSession.fireAllRules();
        //指定规则
//        int ruleFiredCount = kieSession.fireAllRules(new RuleNameEndsWithAgendaFilter("Postcode should be filled with exactly 5 numbers"));
        kieSession.destroy();
        System.out.println("触发了" + ruleFiredCount + "条规则");
        if(result.isPostCodeResult()){
            System.out.println("规则校验通过"+result.isPostCodeResult());
            System.out.println(address.getContext());
        }

    }

    /**
     * 从数据加载最新规则
     * @return
     * @throws IOException
     */
    @ResponseBody
    @RequestMapping("/reload")
    public String reload() throws IOException {
        rules.reload();
        return "ok";
    }


    /**
     * 生成随机数
     * @param num
     * @return
     */
    public int generateRandom(int num) {
        String chars = "0123456789";
        StringBuffer number=new StringBuffer();
        for (int i = 0; i < num; i++) {
            int rand = (int) (Math.random() * 10);
            number=number.append(chars.charAt(rand));
        }
        return Integer.valueOf(number.toString());
    }
}
