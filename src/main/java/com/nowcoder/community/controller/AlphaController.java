package com.nowcoder.community.controller;

import com.nowcoder.community.service.AlphaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.Banner;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;

@Controller
@RequestMapping("/alpha")
public class AlphaController {

    @Autowired
    private AlphaService alphaService;

    @RequestMapping("/hello")
    @ResponseBody
    public String sayHello(){
        return "Hello Spring Boot";
    }

    @RequestMapping("/data")
    @ResponseBody
    public String getData() {
        return alphaService.find();
    }

    @RequestMapping("/http")
    @ResponseBody
    public void http(HttpServletRequest request, HttpServletResponse response) {
        //获取请求数据
        System.out.println(request.getMethod());
        System.out.println(request.getServletPath());
        Enumeration<String> enumeration = request.getHeaderNames();
        while(enumeration.hasMoreElements()){
            String name = enumeration.nextElement();
            String value = request.getHeader(name);
            System.out.println(name + ": " + value);
        }
        System.out.println(request.getParameter("code"));

        //返回响应数据
        response.setContentType("text/html;charset=utf-8");
        try {
            PrintWriter writer = response.getWriter();
            writer.write("<hl>牛客网</hl>");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //GET请求
    @RequestMapping(path = "/student", method = RequestMethod.GET)
    @ResponseBody
    public String getStudent(@RequestParam(name = "current", required = false, defaultValue = "1") int current,
                             @RequestParam(name = "limit", required = false, defaultValue = "20") int limit) {
        System.out.println(current);
        System.out.println(limit);
        return  "some students";
    }

    @RequestMapping(path = "/student/{id}", method = RequestMethod.GET)
    @ResponseBody
    public String getStudent(@PathVariable("id") int id) {
        System.out.println(id);
        return  "a students";
    }

    //POST请求
    @RequestMapping(path = "/student", method = RequestMethod.POST)
    @ResponseBody
    public String saveStudent(String name, int age) {
        System.out.println(name);
        System.out.println(age);
        return  "success";
    }

    //响应html数据
    @RequestMapping(path = "/teacher", method = RequestMethod.GET)
//    @ResponseBody//不加这个注解默认返回html
    public ModelAndView getTeacher() {
        ModelAndView modelAndView  = new ModelAndView();
        modelAndView.addObject("name", "张三");
        modelAndView.addObject("age", "30");
        modelAndView.setViewName("/demo/view");
        return modelAndView;
    }

    @RequestMapping(path = "/school", method = RequestMethod.GET)
//    @ResponseBody//不加这个注解默认返回html
    public String getSchool(Model model) {
        model.addAttribute("name", "peking");
        model.addAttribute("age", "100");

        return "/demo/view";
    }

    //在异步请求中响应json数据
    @RequestMapping(path = "/emp", method = RequestMethod.GET)
    @ResponseBody
    public Map<String, Object> getEmp() {
        Map<String, Object> emp = new HashMap<>();
        emp.put("name","张三");
        emp.put("age","23");
        emp.put("salary","8000.00");
        return emp;
    }

    @RequestMapping(path = "/emps", method = RequestMethod.GET)
    @ResponseBody
    public List<Map<String, Object>> getAllEmp() {
        List<Map<String, Object>> list = new ArrayList<>();
        Map<String, Object> emp = new HashMap<>();
        emp.put("name","张三");
        emp.put("age","23");
        emp.put("salary","8000.00");
        list.add(emp);
        emp = new HashMap<>();
        emp.put("name","李四");
        emp.put("age","20");
        emp.put("salary","9000.00");
        list.add(emp);
        return list;
    }
}
