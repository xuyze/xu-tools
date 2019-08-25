/**
 * Copyright (C), 2015-2019, XXX有限公司
 * FileName: Test
 * Author:   xuyz
 * Date:     2019/8/25 15:17
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.xuyz.common.utils.json;

import com.alibaba.fastjson.JSON;

/**
 * 〈一句话功能简述〉<br> 
 * 〈〉
 *
 * @author xuyz
 * @date 2019/8/25
 * @since 1.0.0
 */
public class Test {

    public static void main(String[] args) {
        TestObject testObject = new TestObject();
        testObject.setMsg("11");
        testObject.setMsg2("333");
        testObject.setMsg1("222");
        System.out.println(JSON.toJSON(testObject));
    }
}
