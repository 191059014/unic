package com.hb.unic.util;

import com.hb.unic.util.helper.IdHelper;

/**
 * ========== Description ==========
 *
 * @author Mr.huang
 * @version com.hb.unic.util.IdWorkTest.java, v1.0
 * @date 2019年07月22日 09时36分
 */
public class IdWorkTest {

    public static void main(String[] args) {
        for (int i = 0; i < 20; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    System.out.println(IdHelper.getInstance().nextId());
                }
            }).start();
        }
    }

}
