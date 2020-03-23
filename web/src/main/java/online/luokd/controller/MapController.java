package online.luokd.controller;

import com.alibaba.fastjson.JSONObject;
import online.luokd.entity.CustomMsg;
import online.luokd.mapper.MapMapper;
import online.luokd.model.Map;
import online.luokd.vo.WxRetMsgVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Controller;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.HashMap;

@RestController
public class MapController {
    private static Logger logger = LoggerFactory.getLogger(MapController.class);
    private static final String TOKEN = "1234";
    private static final String ACCESS_TOKEN = "31_LBQfLPtJYZOGjKzahy39QsKi6axDp8wdOB1JJPMygh5YuoGWDxjbRRj8ekl_fIh7HLbEnTUtfq4ryQDSoaKXOfgU-qOvbfKZkTgxNCNYdQU4M9e2vMOc9pKt3PYhA4Cn-WERXdqnQRvO6oXNIQThAJASAD";
    private static final String SEND_URL = "https://api.weixin.qq.com/cgi-bin/message/custom/send?access_token=";
    private static final String TOUSER = "oE7475E3Ae6hVsEKF43tH_Gy8YVg";

    @Autowired
    private MapMapper mapMapper;

    @Value("${global.color}")
    private String color;

    @RequestMapping(value = "hi")
    public String hello() {
        return "hello,earth";
    }

    @RequestMapping(value = "showConfig")
    public String showConfig() {
        return this.color;
    }

    @RequestMapping(value = "/kkk", method = RequestMethod.POST)
    @ResponseBody
    public String receiveMessage(HttpServletRequest request, HttpServletResponse response) {
        try {
//            logger.info("收到用户在客服窗口发送的消息:{}" + JSONObject.toJSONString(request));

            CustomMsg msg = new CustomMsg();
            msg.setTouser(TOUSER);
            msg.setMsgtype("text");
            HashMap<String, String> text = new HashMap<>();
            text.put("content", "hahahahah");
            msg.setText(text);
            Thread.sleep(5000);
            logger.info("req to wx:{}",JSONObject.toJSONString(msg));
            WxRetMsgVo wxRetMsgVo = sendPostRequest(SEND_URL + ACCESS_TOKEN, msg);
            logger.info("response from wx:{}-{}-{}",wxRetMsgVo.getErrcode(),wxRetMsgVo.getErrmsg(),wxRetMsgVo.getMsgid());

        } catch (Exception e) {
            e.printStackTrace();
        }
        return "success";
    }

    public static WxRetMsgVo sendPostRequest(String url, CustomMsg params) {
        RestTemplate client = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        HttpMethod method = HttpMethod.POST;
        // 以表单的方式提交
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        //将请求头部和参数合成一个请求
        HttpEntity<CustomMsg> requestEntity = new HttpEntity<>(params, headers);
        //执行HTTP请求，将返回的结构使用ResultVO类格式化
        ResponseEntity<WxRetMsgVo> response = client.postForEntity(url, params, WxRetMsgVo.class);
//        ResponseEntity<WxRetMsgVo> response = client.exchange(url, method, requestEntity, WxRetMsgVo.class);

        return response.getBody();
    }

    @GetMapping(value = "/kkk", produces = "text/html;charset=utf-8")
    public String checkToken(@RequestParam("signature") String signature, @RequestParam("timestamp") String timestamp, @RequestParam("nonce") String nonce, @RequestParam("echostr") String echostr) {
        // 排序
        String[] arr = {TOKEN, timestamp, nonce};
        Arrays.sort(arr);

        StringBuilder content = new StringBuilder();
        for (int i = 0; i < arr.length; i++) {
            content.append(arr[i]);
        }

        // sha1Hex 加密
        MessageDigest md = null;
        String temp = null;
        try {
            md = MessageDigest.getInstance("SHA-1");
            byte[] digest = md.digest(content.toString().getBytes());
            temp = byteToStr(digest);
            logger.info("加密后的token:" + temp);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        if ((temp.toLowerCase()).equals(signature)) {
            return echostr;
        }
        return null;
    }

    private static String byteToStr(byte[] byteArray) {
        String strDigest = "";
        for (int i = 0; i < byteArray.length; i++) {
            strDigest += byteToHexStr(byteArray[i]);
        }
        return strDigest;
    }

    private static String byteToHexStr(byte mByte) {
        char[] Digit = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};
        char[] tempArr = new char[2];
        tempArr[0] = Digit[(mByte >>> 4) & 0X0F];
        tempArr[1] = Digit[mByte & 0X0F];
        String s = new String(tempArr);
        return s;
    }

    @RequestMapping(value = "all")
    public String getAll() {
        return JSONObject.toJSONString(mapMapper.getAll());
    }

    @RequestMapping(value = "add")
    public String insert(@RequestBody Map reqMap) {
        Map map = new Map();
        map.setKey(reqMap.getKey());
        map.setValue(reqMap.getValue());
        mapMapper.insert(map);
        return "ok";
    }

    @RequestMapping(value = "delete")
    public String delete(@RequestParam Long id) {
        mapMapper.delete(id);
        return "ok";
    }

    @RequestMapping(value = "update", method = RequestMethod.POST)
    public String update(@RequestBody Map reqMap) {
        Map map = new Map();
        map.setId(reqMap.getId());
        map.setKey(reqMap.getKey());
        map.setValue(reqMap.getValue());
        mapMapper.update(map);
        return "ok";
    }

    @RequestMapping(value = "delete-by-key", method = RequestMethod.POST)
    public String deleteByKey(@RequestBody Map reqMap) {
        Map map = new Map();
        map.setKey(reqMap.getKey());
        mapMapper.deleteByKey(map);
        return "ok";
    }
}
