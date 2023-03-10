package tn.arabsoft.spring.configurations;

import org.springframework.stereotype.Component;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.core.AuthenticationException;
@Component
public class JwtAuthentication implements AuthenticationEntryPoint {

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response,
                         AuthenticationException authException) throws IOException, ServletException {
        response .sendError(HttpServletResponse.SC_UNAUTHORIZED,"Unauthorized");
    }
}
