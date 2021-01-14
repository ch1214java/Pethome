package cn.xinhang.org.service;

import cn.xinhang.basic.util.FastDfsUtils;
import org.junit.Test;

public class FastdfsTest {
    @Test
    public void testFastdfs(){
        String filePath = FastDfsUtils.upload("E:/123.jpg", "jpg");
        System.out.println(filePath);
    }
}

