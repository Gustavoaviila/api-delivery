package com.gustavoaviila.Delivery.resources;


import java.net.URI;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.gustavoaviila.Delivery.domain.entity.Produto;
import com.gustavoaviila.Delivery.domain.entity.Usuario;
import com.gustavoaviila.Delivery.resources.DTO.CredenciaisDTO;
import com.gustavoaviila.Delivery.resources.DTO.TokenDTO;
import com.gustavoaviila.Delivery.security.JwtService;
import com.gustavoaviila.Delivery.service.exceptions.SenhaInvalidaException;
import com.gustavoaviila.Delivery.service.impl.UsuarioServiceImpl;

import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
@RestController
@RequestMapping("/usuarios")
@RequiredArgsConstructor
@Api("Api Usuarios")
public class UsuarioController {

    private final UsuarioServiceImpl usuarioService;
    private final JwtService jwtService;
    
    @GetMapping
	public ResponseEntity<List<Usuario>> findAll(){
		List<Usuario> usuarios = usuarioService.findAll();
		return ResponseEntity.ok().body(usuarios);
	}
    
    @PostMapping
    public ResponseEntity<Usuario> insert ( @RequestBody Usuario usuario ){ 
        usuario = usuarioService.insert(usuario);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(usuario.getId()).toUri();
        return ResponseEntity.created(uri).body(usuario);
    }
    
    @PostMapping("/auth")
    public TokenDTO autenticar(@RequestBody CredenciaisDTO credenciais){
        try{
            Usuario usuario = Usuario.builder()
                    .login(credenciais.getLogin())
                    .senha(credenciais.getSenha()).build();
//            UserDetails usuarioAutenticado = usuarioService.autenticar(usuario);
            String token = jwtService.gerarToken(usuario);
            return new TokenDTO(usuario.getLogin(), token);
        } catch (UsernameNotFoundException | SenhaInvalidaException e ){
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, e.getMessage());
        }
    }
}
