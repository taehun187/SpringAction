package tacos.security.jwt;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.GenericFilterBean;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import java.io.IOException;

@Component
public class JwtFilter extends GenericFilterBean {

    private static final Logger logger = LoggerFactory.getLogger(JwtFilter.class);
    public static final String AUTHORIZATION_HEADER = "Authorization";
    private final TokenProvider tokenProvider;
    @Autowired
    public JwtFilter(TokenProvider tokenProvider) {
        this.tokenProvider = tokenProvider;
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
        String jwt = resolveToken(httpServletRequest);
        String requestURI = httpServletRequest.getRequestURI();
        
//        if (StringUtils.hasText(jwt)) { // Bearer 속성이 존재하는지...Authorization Header
//            if (tokenProvider.validateToken(jwt)) {
//            	Authentication authentication = tokenProvider.getAuthentication(jwt);
//              SecurityContextHolder.getContext().setAuthentication(authentication);
//              logger.debug("Save '{}' in Security Context, uri: {}", authentication.getName(), requestURI);
//            } else {
//            	logger.debug("Invalid JWT, uri: {}", requestURI);
//            }            
//        } else {
//            logger.debug("No Bearer Header, uri: {}", requestURI);
//        }

        if (StringUtils.hasText(jwt) && tokenProvider.validateToken(jwt)) {
        	//////////////////////////////////////////////////////////////////////
            Authentication authentication = tokenProvider.getAuthentication(jwt);
            SecurityContextHolder.getContext().setAuthentication(authentication);
            /////////////////////////////////////////////////////////////////////
            logger.debug("Save '{}' in Security Context, uri: {}", authentication.getName(), requestURI);
        } else {
            logger.debug("Invalid JWT, uri: {}", requestURI);
        }

        filterChain.doFilter(servletRequest, servletResponse);
    }

    private String resolveToken(HttpServletRequest request) {
        String bearerToken = request.getHeader(AUTHORIZATION_HEADER);

        if (StringUtils.hasText(bearerToken) && bearerToken.startsWith("Bearer ")) {
            return bearerToken.substring(7);
        }

        return null;
    }
}