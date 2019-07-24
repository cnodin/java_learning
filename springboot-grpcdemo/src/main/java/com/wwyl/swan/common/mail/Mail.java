package com.wwyl.swan.common.mail;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: pollux
 * Date: 10/02/2018
 * Time: 11:07
 * To change this template use File | Settings | File Templates.
 * Description:
 */
@Getter
@Setter
@ConfigurationProperties("mail")
public class Mail {

    private String subject;

    private String from;

    private String[] to;

    private String[] cc;

    private String[] bcc;

    private Boolean html;

    private List<Object> attachments;

    private Map<String, Object> variables;

    public Mail() {
        this.html = Boolean.FALSE;
    }
}
