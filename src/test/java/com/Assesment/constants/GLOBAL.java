package com.Assesment.constants;


public class GLOBAL {

    public static final String CONFIG_FILE = System.getProperty("user.dir") + "/src/resources/configs/environments.properties";

    public static final String ENVCREDENTIALS = System.getProperty("user.dir") + "/src/resources/configs/credentials.properties";

    public static final String APPLICATION_ENVIRONMENT = System.getProperty("env", "dev");
//
    public static final boolean IS_TOKENIZER = Boolean.getBoolean("isToken");

}
