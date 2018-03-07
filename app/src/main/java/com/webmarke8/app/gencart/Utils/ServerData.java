package com.webmarke8.app.gencart.Utils;

/**
 * Created by GeeksEra on 2/15/2018.
 */

public class ServerData {

    public static String Server = "http://192.168.10.6/api/";

    public static String CustomerLogin = Server + "login";
    public static String OwnerLogin = Server + "login";

    public static String CustomerSignup = Server + "register";
    public static String OwnerSignup = Server + "register";

    public static String GetStores = Server + "stores";
    public static String GetStoresByID = Server + "get_store/";

    public static String SearchStore = Server + "search_store/";
    public static String UrlImage = "http://192.168.10.6/storage/large/";
    public static String UrlImage1 = "http://192.168.10.6/storage/medium/";

}
