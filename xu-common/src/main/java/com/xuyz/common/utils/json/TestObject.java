/**
 * Copyright (C), 2015-2019, XXX有限公司
 * FileName: TestObject
 * Author:   xuyz
 * Date:     2019/8/25 15:13
 * Description: message对象
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.xuyz.common.utils.json;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

/**
 * 〈一句话功能简述〉<br> 
 * 〈message对象〉
 *
 * @author xuyz
 * @date 2019/8/25
 * @since 1.0.0
 */
@Data
@Setter
@Getter
public class TestObject {

    private String msg;

    private String msg1;

    private String msg2;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getMsg1() {
        return msg1;
    }

    public void setMsg1(String msg1) {
        this.msg1 = msg1;
    }

    public String getMsg2() {
        return msg2;
    }

    public void setMsg2(String msg2) {
        this.msg2 = msg2;
    }
}
