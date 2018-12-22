package com.zfm.feisbk.controller;

import com.zfm.feisbk.CustomerException;
import com.zfm.feisbk.pojo.NormalResultDTO;
import com.zfm.feisbk.pojo.UserDO;
import com.zfm.feisbk.service.TestService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")
public class TestController {
    private Logger logger = LoggerFactory.getLogger(TestController.class);
    @Autowired
    private TestService testService;

    @RequestMapping(path = "/add.action", method = RequestMethod.POST)
    public NormalResultDTO add() {
        NormalResultDTO result = new NormalResultDTO("9999","unknow error",null);
        UserDO user = new UserDO();
        user.setUsername("testname");
        try{
            testService.addUser(user);
            result.setCode("0000");
            result.setMessage("successful");
        }catch (CustomerException e){

            result.setMessage(e.getMessage());
        }
        return result;
    }
}
