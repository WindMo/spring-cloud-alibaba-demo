package ws.springcloud.consumer.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ws.springcloud.consumer.controller.blockhandler.MyBlockHandler;

/**
 * Sentinel 服务降级限流测试接口
 * @author WindShadow
 * @version 2021-08-23.
 */
@Slf4j
@RestController
@RequestMapping("/flow")
public class FlowLimitController {

    @GetMapping("/testA")
    @SentinelResource("testA")
    public String strA() {

        return "Sentinel --- A";
    }

    @GetMapping("/testB")
    public String strB() {

        return "Sentinel --- B";
    }

    @GetMapping("/testC")
    public String strC() {


        return "Sentinel --- C";
    }

    @GetMapping("/testD")
    @SentinelResource(value = "testD",
            blockHandlerClass = MyBlockHandler.class,
            blockHandler = "strDBlockHadnler") // 自定义限流处理方法，指定类和方法名，方法必须是静态的
    public String strD() {

        return "Sentinel --- D";
    }

    /**
     * {@link SentinelResource#blockHandler()}属性负责限流处理
     * {@link SentinelResource#fallback()}属性负责异常回调处理（熔断）
     *
     * 限流处理先于异常回调处理之前
     *
     * 通过{@link SentinelResource#fallbackClass()}和{@link SentinelResource#fallback()}指定类和方法名确定异常的回调处理时，方法必须是静态的
     */
    @GetMapping("/testHotKey")
    @SentinelResource(value = "HotKey", blockHandler = "strHotKeyHadnler")/** 使用{@link #strHotKeyHadnler()}进行窗口期的限流处理 */
    public String strHotKey(@RequestParam(name = "p1", required = false) String p1,
                            @RequestParam(name = "p2", required = false) String p2) {


        return "Sentinel --- HotKey";
    }

    public String strHotKeyHadnler(String p1, String p2, BlockException be) {

        log.warn("HotKey - 限流处理",be);
        return "Sentinel --- HotKey 限流处理";
    }
}
