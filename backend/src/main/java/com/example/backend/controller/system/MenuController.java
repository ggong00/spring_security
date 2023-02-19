package com.example.backend.controller.system;

import com.example.backend.dto.MenuDto;
import com.example.backend.dto.response.ResponseDto;
import com.example.backend.dto.response.ResponseMsg;
import com.example.backend.service.MenuService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping(
        value = "/api/system/menus/",
        produces = "application/json"
)
public class MenuController {

    private final MenuService menuService;

    @GetMapping("all")
    ResponseEntity<?> getList(){
        List<MenuDto> myMenuList = menuService.getMenuList();
        ResponseDto.ResponseRes responseRes = new ResponseDto
                .ResponseRes(ResponseMsg.SUCCESS)
                .setData(myMenuList);

        return new ResponseEntity<>(responseRes, HttpStatus.OK);
    }

    @GetMapping("my")
    ResponseEntity<?> getMyMenuList(){
        List<MenuDto> myMenuList = menuService.getMyMenuList();
        ResponseDto.ResponseRes responseRes = new ResponseDto
                .ResponseRes(ResponseMsg.SUCCESS)
                .setData(myMenuList);

        return new ResponseEntity<>(responseRes, HttpStatus.OK);
    }
}