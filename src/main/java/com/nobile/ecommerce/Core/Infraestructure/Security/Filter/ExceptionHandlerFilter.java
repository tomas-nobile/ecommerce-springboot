package com.nobile.ecommerce.Core.Infraestructure.Security.Filter;

import java.io.IOException;

import org.springframework.web.filter.OncePerRequestFilter;

import com.auth0.jwt.exceptions.JWTVerificationException;

import jakarta.persistence.EntityNotFoundException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class ExceptionHandlerFilter extends OncePerRequestFilter{
        private void errorStatusMessage(HttpServletResponse response, String message,int status ) throws IOException{
        response.setStatus(status);
        response.getWriter().write(message);
        response.getWriter().flush();
    }
    
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws IOException, ServletException{
        try {
            filterChain.doFilter(request, response);
        }
        catch (EntityNotFoundException e) { 
            this.errorStatusMessage(response,"Username doesn't exist" , HttpServletResponse.SC_NOT_FOUND);
        } 
        catch (JWTVerificationException e) {
            this.errorStatusMessage(response,"JWT not valid" , HttpServletResponse.SC_FORBIDDEN);
        }
        catch (RuntimeException e) {
            String message= e.getMessage();
            if(message == null) message= "Bad Request";
            this.errorStatusMessage(response,message , HttpServletResponse.SC_BAD_REQUEST);
        }  
    }
}
