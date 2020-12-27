package com.second.hand.trading.server.controller;

import com.second.hand.trading.server.enums.ErrorMsg;
import com.second.hand.trading.server.model.MessageModel;
import com.second.hand.trading.server.service.MessageService;
import com.second.hand.trading.server.vo.ResultVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * @author myl
 * @create 2020-12-27  11:19
 */
@RestController
@RequestMapping("/message")
public class MessageController {

    @Autowired
    private MessageService messageService;

    @PostMapping("/send")
    public ResultVo sendMessage(@CookieValue("shUserId")
                                @NotNull(message = "登录异常 请重新登录")
                                @NotEmpty(message = "登录异常 请重新登录") String shUserId,
                                @RequestBody MessageModel messageModel){
        messageModel.setUserId(Long.valueOf(shUserId));
        messageModel.setCreateTime(new Date());
        if(messageService.addMessage(messageModel)){
            return ResultVo.success(messageModel);
        }
        return ResultVo.fail(ErrorMsg.SYSTEM_ERROR);
    }

    @GetMapping("/info")
    public ResultVo getMessage(@RequestParam Long id){
        return ResultVo.success(messageService.getMessage(id));
    }

    @GetMapping("/idle")
    public ResultVo getAllIdleMessage(@RequestParam Long idleId){
        return ResultVo.success(messageService.getAllIdleMessage(idleId));
    }

    @GetMapping("/my")
    public ResultVo getAllMyMessage(@CookieValue("shUserId")
                                        @NotNull(message = "登录异常 请重新登录")
                                        @NotEmpty(message = "登录异常 请重新登录") String shUserId){
        return ResultVo.success(messageService.getAllMyMessage(Long.valueOf(shUserId)));
    }

    @GetMapping("/delete")
    public ResultVo deleteMessage(@CookieValue("shUserId")
                                  @NotNull(message = "登录异常 请重新登录")
                                  @NotEmpty(message = "登录异常 请重新登录") String shUserId,
                                  @RequestParam Long id){
        if(messageService.deleteMessage(id)){
            return ResultVo.success();
        }
        return ResultVo.fail(ErrorMsg.SYSTEM_ERROR);
    }
}
