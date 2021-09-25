package com.hb.unic.rbac.model.vo.request;

import lombok.Data;

/**
 * 更新密码参数
 *
 * @version v0.1, 2021/9/14 21:55, create by huangbiao.
 */
@Data
public class UpdatePasswordRequest {

    /**
     * 旧的密码
     */
    private String oldPassword;

    /**
     * 新的密码
     */
    private String newPassword;

}
