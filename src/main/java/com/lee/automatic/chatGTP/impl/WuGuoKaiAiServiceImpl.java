package com.lee.automatic.chatGTP.impl;

import com.lee.automatic.chatGTP.OpenAiConfig;
import com.lee.automatic.chatGTP.OpenAiService;
import com.lee.automatic.chatGTP.model.WuGuoKaiReq;
import com.lee.automatic.common.utils.HttpUtils;
import lombok.extern.slf4j.Slf4j;
import okhttp3.Response;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

/**
 * megumi页面 ai问答
 *
 * @author Lee
 */
@Slf4j
@Service
public class WuGuoKaiAiServiceImpl implements OpenAiService, Ordered {

    /**
     * 使用 <a href="http://doc.wuguokai.cn/s/xPq1iNw_v">web页面</a> url
     */
    public static final String URL = "https://chat2.wuguokai.cn/api/chat-process";

    @Resource
    private OpenAiConfig openAiConfig;

    @Override
    public String ask(String prompt) {
        try {
            Map<String, String> headers = new HashMap<>(1);
            headers.put("referer", "https://chat.wuguokai.cn/");

            Response post = HttpUtils.post(URL, headers,
                    new WuGuoKaiReq(prompt, null, openAiConfig.getWuguokai(), true));

            return post.body().string();
        } catch (Exception e) {
            log.error("megumi页面 AI问答异常", e);
            return e.getMessage();
        }
    }

    @Override
    public int getOrder() {
        return 2;
    }
}
