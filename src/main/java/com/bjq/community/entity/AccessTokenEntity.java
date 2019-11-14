package com.bjq.community.entity;

import lombok.Data;

/**
 * 实体类
 */
@Data
public class AccessTokenEntity {
   private String client_id;
   private String client_secret;
   private String code;
   private String redirect_uri;
   private String state;
}
