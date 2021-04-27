package utils;

import io.qameta.allure.Step;
import org.apache.commons.codec.binary.Base64;

public class DecodeJWT {
    @Step("Decode JWT Body")
    public static String decodeJWTBody(String jwtToken)
    {
        String[] splitToken = jwtToken.split("\\.");
        String encodedBody= splitToken[1];
        Base64 base64Url = new Base64(true);
        return new String(base64Url.decode(encodedBody));
    }

}
