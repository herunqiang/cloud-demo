package cn.itcast.user.web;

import cn.itcast.user.config.PatternProperties;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Slf4j
@RestController
@RequestMapping("/user")
public class UserController2 {
    @Autowired
    private PatternProperties patternProperties;
    @GetMapping("/now2")
    public String now(){
        return LocalDateTime.now().format(DateTimeFormatter.ofPattern(this.patternProperties.getDateFormat()));
    }

    /**
     *
     * @return
     */
    @GetMapping("/prop")
    public Object patternProperties(){
        return this.patternProperties;
    }
}
