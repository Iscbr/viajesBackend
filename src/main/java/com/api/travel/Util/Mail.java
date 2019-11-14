package com.api.travel.Util;

import lombok.Data;

import java.util.HashMap;
import java.util.Map;

@Data
public class Mail {

    private String from;
    private String to;
    private String subject;
    private Map<String, Object> model;
}
