package com.conference.web.taglib;

import com.conference.service.RegistrationService;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.SimpleTagSupport;
import java.io.IOException;

/**
 * Created by gleb on 19.01.18.
 */
public class RegistrationStatisticTag extends SimpleTagSupport {
    public void doTag() throws JspException, IOException {
        JspWriter out = getJspContext().getOut();
        int count = new RegistrationService().getRegistrationsCount();
        out.println(count);
    }
}
