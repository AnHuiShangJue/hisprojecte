package com.ahsj.hiseurekasecurityauthorizationserverone.controller;

import com.ahsj.hiseurekasecurityauthorizationserverone.service.SystemShutdownService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/api/securityShutdown")
public class SystemShutdownController  {
    @Autowired
    SystemShutdownService systemShutdownService;

    @PostMapping("/shutdown.ahsj")
    @ResponseBody
    public void shutdown() throws Exception {
        systemShutdownService.TimedOff();
    }
}
