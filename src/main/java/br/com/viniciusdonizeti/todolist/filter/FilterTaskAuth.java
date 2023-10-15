package br.com.viniciusdonizeti.todolist.filter;

import java.io.IOException;
import java.util.Base64;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import at.favre.lib.crypto.bcrypt.BCrypt;
import br.com.viniciusdonizeti.todolist.user.IUserRepository;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


@Component
public class FilterTaskAuth extends OncePerRequestFilter{

    @Autowired
    private IUserRepository userRepository;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws IOException, ServletException{    
        var servletPath = request.getServletPath();

        if(servletPath.startsWith("/tasks/")){
            //Pegar informações do usuário
            var authorization = request.getHeader("Authorization");
            var authEncoded = authorization.substring("Basic".length()).trim();

            String[] authDecoded =  new String(Base64.getDecoder().decode(authEncoded)).split(":");

            //Validar se o usuário existe
            String username = authDecoded[0];
            String password = authDecoded[1];


            var findUser = this.userRepository.findByUsername(username);
 

            if(findUser == null){
                response.sendError(401);
            }

            //Validar senha
            var passwordVerify = BCrypt.verifyer().verify(password.toCharArray(), findUser.getPassword());

            if(passwordVerify.verified){
                request.setAttribute("idUser", findUser.getId());
                filterChain.doFilter(request, response);
            } else {
                response.sendError(401);
            }
        }

        filterChain.doFilter(request, response);
    }
}
