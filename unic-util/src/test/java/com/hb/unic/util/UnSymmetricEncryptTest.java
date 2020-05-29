package com.hb.unic.util;

import com.hb.unic.util.tool.UnSymmetricEncrypt;
import org.junit.Test;

/**
 * 非对称加密测试类
 *
 * @author Mr.Huang
 * @version v0.1, UnSymmetricEncryptTest.java, 2020/5/29 15:00, create by huangbiao.
 */
public class UnSymmetricEncryptTest {

    private String message = "123456您好==++@&";
    private String privateKey = "MIIEvgIBADANBgkqhkiG9w0BAQEFAASCBKgwggSkAgEAAoIBAQCvDPcguobkplJwS8IPOJ4EE1H/kuGubhVyDKYFJeuiAb4cjDSQmh7nlbrfcwFVD9Y2BvO2ao8hTAhirdpiHMNihGcXZA/uEJYjZJb8Lb/Q0jzqnP8gVttT9rEGMVPzZFdM2vFpZD3NKo42echAhp1wowyWMhVvA94OC5FeTzd+bT+eMPJDtg8pUzCl2Lh+h9jU5zAJRShZCSgSjUw1v0M3h/JUHlZk3m3XSAUgON7aPxQ3/N7zBjhvE4YoWNREkhoacSBVwidkFIz9CJbqqdGrDIhpzgZBmB0BtClV/F+0MTZ3eU75Ufs3yZxuWkrGD3z4sD5Q2ooN95f3Hl0eYuJzAgMBAAECggEAaIUKTc1x27Z/hLOHVIVOvEo47Kvr+rWaMnXg7zP8JMh7lhoVDL4pc8eQQ/ksM3j4Q1wVSrcHbS8z1o/N1EUuWHWMBIhnhpL6wnk+OaarYGkXnFJPdyTFYrXqbzMm2C3yfl5ARNhomkctkNNYQUbfXLDORCP1bkrV3HcFvlT4YpClnx9D46C+pv45nBD21HV2ObhBIjo4Jc+FzyAkVCexqqt9gyJDEgvB45ln7+v42QAbLPPUPAqUUxI5UsjDP8jPS1O6CoZH/yq65oP9blZJlc/Wtkgn8FpWyRgWzi2reHI2fMdbpt80sWVbsNSnW/vSgrb93PeUSv+xn2myeylYIQKBgQDdRXotqBmNrmBy+/Jzvfs9toitEvAmzh4jOXjiTRekwU1zRa1sjwz5KuwzTm5yXlDGEqyqAdYObjrQuaF1J7QcLu2ZgC/x3jUchNBkiK3RamNn0o/eFTQyq2h4ejcVm+eF6iaG2xUfUThnRcXFM1umdDd3Zefi3R2B/o90RTtcMQKBgQDKhl9HsXOM29aB9VOTOo7aGTem/JB8Do+WsaTkxJ0xJ1D6/IRhpvv127wdwHe+8PabJxsXDVBgB83C21xgefsalIQ0ZTA3uYZScIvbnnEobVjfISUqIExypw8eMf/UqAiW4Xt4jsvEoFmoap7ldqEIW8HALFBcXwvDProdTdCT4wKBgD6Lrg6LzrVSziaSeyUmVYdLOnUZvlJpvvEcBMSLIBzNGzRzRKNECFnCPUP/bo7bhB+JFc90d2pa1Ds9zZrNH0c8KKNULblDo2WtUS6px+BDH1gwl8Qh4EBWXVB402tDR31+A2fpM55HmhbvDQlCDC0P+sHAR3zEHdwp9ajbDP1xAoGBAIDdsaDny/O0KrD0zmIfX6htZVkO2kzl4v3rOVYLuU3+i2u44+9TFn3hfLidsFRB9VjSME2hviPss0Oq2wPUei/2kozYWi8LEOpCDOwIzE4CDgukhoZgmORXYqo+/F9vME/5V9xlWElcEtC0TzkJG/w6jnVefR/Zpp1su3ZL/k0rAoGBAKi2Wxsrj4xkMtY8T0mO3LpwAy+y9HlEvRDSwApefs+IgGTK0WopMnrcRsmES/mNxZ6a4+biFYBWN0Mpe6B5K7f6GFq3kXkj4qBVRDGH/PQlmlQWQLWt0TdoNO/boMnWTzggHZWPpXbBOnXf36lLylkQqXmDakhRBY/Cgv4Kn2IR";
    private String publicKey = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEArwz3ILqG5KZScEvCDzieBBNR/5Lhrm4VcgymBSXrogG+HIw0kJoe55W633MBVQ/WNgbztmqPIUwIYq3aYhzDYoRnF2QP7hCWI2SW/C2/0NI86pz/IFbbU/axBjFT82RXTNrxaWQ9zSqONnnIQIadcKMMljIVbwPeDguRXk83fm0/njDyQ7YPKVMwpdi4fofY1OcwCUUoWQkoEo1MNb9DN4fyVB5WZN5t10gFIDje2j8UN/ze8wY4bxOGKFjURJIaGnEgVcInZBSM/QiW6qnRqwyIac4GQZgdAbQpVfxftDE2d3lO+VH7N8mcblpKxg98+LA+UNqKDfeX9x5dHmLicwIDAQAB";

    @Test
    public void testGenerateKey() throws Exception {
        String[] keys = UnSymmetricEncrypt.getKeys(UnSymmetricEncrypt.SHA256WithRSA.getAlgorithm(), 2048);
        System.out.println(String.format("公钥(%s)：%s", keys[0].length(), keys[0]));
        System.out.println(String.format("私钥(%s)：%s", keys[1].length(), keys[1]));
    }

    @Test
    public void testEncode() {
        System.out.println(UnSymmetricEncrypt.SHA256WithRSA.encode(message, privateKey));
    }

    @Test
    public void testVerify() {
        System.out.println(UnSymmetricEncrypt.SHA256WithRSA.verify(message, publicKey, "WSOd43OnUw81/UVokdVujNncJJSjbRauzOlk99CrS6PBaN6R+zUQ/S68wcOw9bQc0OfF06q/IAe4gY+h1LgewmFEnMFHy4MPUSneH98ug6bJk+a9hYdENFIlNr1k15L5rEJdBAmBrS6ZwqLN3qz4maxgFA0/ai6Sb8cbTiMza7cV92u9+uEaGbGoPWBaRNOUdfYinYOqI8orZqBTMA06ZT/bc43c4ekNvmLtNrk7UUc9esWYz+q5fRqY+Y1s6rRnc+nt/dMWPR3HRrWuB5vAwpgnIi0NIAapa2xkAlIr2E558X1E3mUzYjrSlZYwuk51+7q1ZiUYrkC1IjsYoLljTg=="));
    }

}

    