package Data;

import org.apache.catalina.authenticator.*;
import org.apache.catalina.connector.Request;

import javax.security.enterprise.AuthenticationException;
import javax.security.enterprise.AuthenticationStatus;
import javax.security.enterprise.authentication.mechanism.http.HttpAuthenticationMechanism;
import javax.security.enterprise.authentication.mechanism.http.HttpMessageContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.security.Principal;

public class Login implements HttpAuthenticationMechanism {
    FormAuthenticator formAuth = new FormAuthenticator();
    SSLAuthenticator sslAuthenticator = new SSLAuthenticator();
    BasicAuthenticator basicAuth = new BasicAuthenticator();
    DigestAuthenticator digestAuth = new DigestAuthenticator();

    @Override
    public AuthenticationStatus validateRequest(HttpServletRequest request, HttpServletResponse response, HttpMessageContext httpMessageContext) throws AuthenticationException {
        return null;
    }

    @Override
    public AuthenticationStatus secureResponse(HttpServletRequest request, HttpServletResponse response, HttpMessageContext httpMessageContext) throws AuthenticationException {
        return null;
    }

    @Override
    public void cleanSubject(HttpServletRequest request, HttpServletResponse response, HttpMessageContext httpMessageContext) {

    }
}
