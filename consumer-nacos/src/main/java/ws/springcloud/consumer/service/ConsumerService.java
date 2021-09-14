package ws.springcloud.consumer.service;


/**
 * @author WindShadow
 * @version 2021-08-08.
 */

public interface ConsumerService {


    String getHello();

    int getHashCodeValue(String str);

    String callError();

    String timeout(Integer time);
}
