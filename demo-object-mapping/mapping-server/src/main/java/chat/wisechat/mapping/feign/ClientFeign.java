package chat.wisechat.mapping.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;

/**
 * @Author Siberia.Hu
 * @Date 2025/6/26 10:35
 */
@FeignClient(name = "client-app", path = "/client")
public interface ClientFeign {

    @PostMapping("/mapping")
    String mapper();
}
