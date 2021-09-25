package com.hb.unic.rbac.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

/**
 * 树形结构
 *
 * @version v0.1, 2020/9/18 18:41, create by huangbiao.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ElementuiTree implements Serializable {

    // serialVersionUID
    private static final long serialVersionUID = -8867811043430366793L;

    // id
    private Long id;

    // 名称
    private String label;

    // 子树
    private List<ElementuiTree> children;

}

    