package api;

import data.LoadProperties;

public abstract class BaseApi {
    public String BASE_URL = LoadProperties.userData.getProperty("VlsBaseUrl");
}
