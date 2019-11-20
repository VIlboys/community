package com.bjq.community.entity;

import com.bjq.community.model.User;
import lombok.Data;

import javax.lang.model.element.NestingKind;

@Data
public class NotificationDTO {
    private Long id;

    private Long gmtCreate;

    private Integer status;

    private Long notifier;

    private String notifierName;

    private String outerTitle;

    private Long outerid;

    private String typeName;

    private Integer type;

}
