package ws.springcloud.consumer.controller.blockhandler;

import com.alibaba.csp.sentinel.slots.block.BlockException;
import lombok.extern.slf4j.Slf4j;

/**
 * 自定义限流处理类
 * @author WindShadow
 * @version 2021-08-24.
 */
@Slf4j
public class MyBlockHandler {

    public static String strDBlockHadnler(BlockException be) {

        log.warn("MyBlockHandler Sentinel --- D",be);
        return "MyBlockHandler Sentinel --- D";
    }

}
