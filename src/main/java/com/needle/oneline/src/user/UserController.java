package com.needle.oneline.src.user;

import com.fasterxml.jackson.databind.ser.Serializers;
import com.needle.oneline.src.common.BaseResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RequiredArgsConstructor
@RestController
@RequestMapping("/users")
public class UserController {
    private UserService userService;
    @GetMapping("/me")
    public BaseResponse returnUserId(HttpServletRequest request){
        return new BaseResponse(userService.findUserId(request));
    }
}
