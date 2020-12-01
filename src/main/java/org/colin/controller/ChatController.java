package org.colin.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.colin.utils.DataResult;
import org.colin.websocket.WebSocketServer;
import org.springframework.web.bind.annotation.*;
import javax.websocket.Session;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author : wujiangbo(QQ:1134135987)
 * @version : 1.1.0.1
 * @description : TODO
 * @date : 2020-09-17 09:12:31
 */
@RequestMapping("/chat")
@RestController
@Api(tags = "聊天模块")
public class ChatController {

    @ApiOperation(value = "获取在聊天室的所有用户")
    @GetMapping("/onlineusers/{currentUserRealName}")
    public DataResult onlineusers(@PathVariable("currentUserRealName") String currentUserRealName) {
        ConcurrentHashMap<List<String>, Session> map = WebSocketServer.getSessionPools();
        Set<List<String>> set = map.keySet();
        Iterator<List<String>> it = set.iterator();
        Set<Map<String, String>> nameset = new HashSet<>();
        Map<String, String> userInfoMap = null;
        while (it.hasNext()) {
            List<String> entry = it.next();
            userInfoMap = new HashMap<>();
            userInfoMap.put("realName", entry.get(0));
            userInfoMap.put("userPhoto", entry.get(1));
            nameset.add(userInfoMap);
        }
        return DataResult.success(nameset);
    }
}
