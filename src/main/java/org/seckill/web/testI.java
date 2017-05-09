package org.seckill.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by lsx on 2016/8/19.
 */
@Controller
@RequestMapping("/interceptor")
public class testI {
    @RequestMapping("/t1")
    //http://localhost:8080/index2.jsp--》
    //http://localhost:8080/interceptor/t1--》
    public ModelAndView viewAll(String name,String pwd){
        ModelAndView model=new ModelAndView();
        System.out.println("进入viewall方法");
        System.out.println("name="+name+",password="+pwd);
        model.setViewName("/index3");  //使用的转发形式
        model.addObject("msg","被传回的数据");
        return  model;
        
    }
    @RequestMapping("/t2")
    //http://localhost:8080/index2.jsp--》
    //http://localhost:8080/interceptor/t1--》
    public ModelAndView view2(String name,String pwd){
        ModelAndView model=new ModelAndView();
        System.out.println("进入view2方法");
        System.out.println("name="+name+",password="+pwd);
        model.setViewName("/index3");  //使用的转发形式
        return  model;
        
    }
}
