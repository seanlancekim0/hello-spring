package hello.hellospring.controller;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.Banner;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HelloController {

    @GetMapping("hello")
    public String hello(Model model) {
        model.addAttribute("data", "hello!!");
        return "hello";
    }

    @GetMapping("hello-mvc")
    public String helloMvc(@RequestParam("name") String name, Model model) {
        model.addAttribute("name", name);
        return "hello-template";
    }

    @GetMapping("hello-string")
    @ResponseBody   //http통신 body 부분에 return 데이터를 추가하기위한 어노테이션
    public String helloString(@RequestParam("name") String name) {
        return "hello " + name;
        //페이지 소스보기시 html코드없이 문자열만 전달됨
    }

    @GetMapping("hello-api")
    @ResponseBody
    public Hello helloapi(@RequestParam("name")String name) {
        Hello hello = new Hello();
        hello.setName(name);
        return hello;
        //json구조로 화면에 보여짐
    }

    @Getter
    @Setter
    static class Hello {
        private String name;

    }
}
