package com.charles.sys.chat.io.domain;

import lombok.Data;

@Data
public class MessageInfoStructure {
    //消息类型
    private String msgType;
    //消息内容
    private byte[] msgContent;

}
