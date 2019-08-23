package com.den.we.config;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.session.Session;
import org.springframework.session.web.http.HttpSessionIdResolver;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Slf4j
public class SmartHttpSessionStrategy implements HttpSessionIdResolver {



    @Override
    public List<String> resolveSessionIds(HttpServletRequest httpServletRequest) {
        return null;
    }

    @Override
    public void setSessionId(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, String s) {

    }

    @Override
    public void expireSession(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {

    }
}