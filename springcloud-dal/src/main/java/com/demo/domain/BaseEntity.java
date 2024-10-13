package com.demo.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BaseEntity {

    /**
     * 更新时间
     */
    @JsonFormat(shape = JsonFormat.Shape.STRING,pattern = "yyyy-MM-dd HH:mm:ss",timezone ="GMT+8")
    private Date updateTime;

    /**
     * 新增时间
     */
    @JsonFormat(shape = JsonFormat.Shape.STRING,pattern = "yyyy-MM-dd HH:mm:ss",timezone ="GMT+8")
    private Date createTime;

    /**
     * 更新人
     * */
    private String updateBy;

    /**
     * 创建人
     * */
    private String createBy;

    /**
     * 删除标志
     * */
    private Integer delFlag;


}
