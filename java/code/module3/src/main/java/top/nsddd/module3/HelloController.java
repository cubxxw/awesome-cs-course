package top.nsddd.module3;

import ch.qos.logback.classic.spi.EventArgUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Scanner;

@Controller
public class HelloController {
    @RequestMapping("/hello")  //路径 Localhost:8080/hello
    @ResponseBody

    String name() {
        return "hello world <h1>Hello World</h1>";
    }

    @RequestMapping("/my")
    @ResponseBody
    String my() {
        return "<b>我叫xiongxinwei</b>很高兴认识你!!!!!!!!<del>谢谢</del>";
    }
}
