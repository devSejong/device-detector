package spring.integration.test;

import com.sandbox9.devicedetector.dto.ReadableUserAgent;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class SampleController {

    @RequestMapping("/ua")
    @ResponseBody
    ReadableUserAgent getUserAgent(ReadableUserAgent ua) {
        return ua;
    }

    @RequestMapping("/")
    String home() {
        return "/index.html";
    }
}