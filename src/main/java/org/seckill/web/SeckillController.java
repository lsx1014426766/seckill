package org.seckill.web;

import dto.Exposer;
import dto.SeckillExecution;
import dto.SeckillResult;
import org.seckill.entity.Seckill;
import org.seckill.enums.SeckillStateEnum;
import org.seckill.exceptions.ReaptKillExcption;
import org.seckill.exceptions.SeckillCloseException;
import org.seckill.service.SeckillService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

/**
 * Created by lsx on 2016/8/4.
 */
@Controller
@RequestMapping("/seckill")//url: /模块/资源/{id}/细分
public class SeckillController {
    private  final Logger logger= LoggerFactory.getLogger(this.getClass());
    @Autowired
    private SeckillService seckillService;
    //http://localhost:9090/seckill/list
    @RequestMapping(value="/list" ,method= RequestMethod.GET)
    public String list(Model model){
        //list.jsp+model=ModelAndView
        List<Seckill>list=seckillService.getSeckillList();
        model.addAttribute("list",list);
        return "list";// /WEB-INF/jsp/list.jsp

    }
    @RequestMapping(value = "/{seckillId}/detail", method =RequestMethod.GET)
    public String detail(@PathVariable("seckillId") Long seckillId, Model model){
        if(seckillId==null){
            return "redirect:/seckill/list";

        }
        Seckill seckill=seckillService.getById(seckillId);
        if (seckill==null){
            return "forward:/seckill/list";
        }
        model.addAttribute("seckill",seckill);
        return  "detail";
    }
    //return ajax json
    @RequestMapping(value = "/{seckillId}/exposer",method = RequestMethod.POST,
    produces ={"application/json;charset=UTF-8"})
    @ResponseBody//示意返回为json   响应内容
    public SeckillResult<Exposer> exposer(@PathVariable("seckillId")Long seckillId){
        SeckillResult<Exposer>result;
        try {
            Exposer exposer=seckillService.exportSeckillUrl(seckillId);
            result=new SeckillResult<Exposer>(true,exposer);
        }catch (Exception e){
            logger.error(e.getMessage(),e);
            result=new SeckillResult<Exposer>(false,e.getMessage());
        }
        return  result;

    }
    @RequestMapping(value = "/{seckillId}/{md5}/execution",method = RequestMethod.GET,
    produces = {"application/json;charset=UTF-8"})
    @ResponseBody
    public SeckillResult<SeckillExecution>execute(@PathVariable("seckillId") Long seckillId,
                                                  @PathVariable("md5") String md5,
                               @CookieValue(value ="killPhone",required = false) Long killPhone){
        //springmvc valid
        if (killPhone==null){
            return new SeckillResult<SeckillExecution>(false,"未注册");
        }
        SeckillResult<SeckillExecution>result;
        try {
            //改用存储过程的方法调用
            SeckillExecution execution=seckillService.executeSeckillProcedure(seckillId,killPhone,md5);
           // SeckillExecution execution=seckillService.executeSeckill(seckillId,killPhone,md5);
            result= new SeckillResult<SeckillExecution>(true,execution);
        }catch (ReaptKillExcption e1){
            SeckillExecution execution1=new SeckillExecution(seckillId, SeckillStateEnum.REPEAT_KILL.getState(),SeckillStateEnum.REPEAT_KILL.getStateInfo());
            result=new SeckillResult<SeckillExecution>(true,execution1);

        }catch (SeckillCloseException e2){
            SeckillExecution execution2=new SeckillExecution(seckillId, SeckillStateEnum.END.getState(),SeckillStateEnum.END.getStateInfo());
            result=new SeckillResult<SeckillExecution>(true,execution2);
        }catch (Exception e){
            logger.error(e.getMessage(),e);
            SeckillExecution execution3=new SeckillExecution(seckillId, SeckillStateEnum.END);
            result= new SeckillResult<SeckillExecution>(true,execution3);
        }
        return result;

    }
    //http://localhost:9090/seckill/time/now
    @RequestMapping(value = "/time/now",method = RequestMethod.GET)
    @ResponseBody
    public SeckillResult<Long> time(){
        Date now=new Date();
        return new SeckillResult<Long>(true,now.getTime());

    }
}
