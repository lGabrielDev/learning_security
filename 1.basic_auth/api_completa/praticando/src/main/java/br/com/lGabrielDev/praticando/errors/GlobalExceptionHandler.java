package br.com.lGabrielDev.praticando.errors;
import java.time.LocalDateTime;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import br.com.lGabrielDev.praticando.errors.exceptions.FieldIsNullException;
import br.com.lGabrielDev.praticando.errors.exceptions.UsuarioAlreadyExists;
import br.com.lGabrielDev.praticando.errors.exceptions.UsuarioAlreadyHasThisRole;
import br.com.lGabrielDev.praticando.errors.exceptions.UsuarioListIsEmpty;
import br.com.lGabrielDev.praticando.errors.exceptions.UsuarioNotFound;

@ControllerAdvice
public class GlobalExceptionHandler  {
    
    //exceptions para personalizar




    //UsuarioAlreadyExists
    @ExceptionHandler(UsuarioAlreadyExists.class)
    public ResponseEntity<ErroPadrao> usuarioAlreadyExists(UsuarioAlreadyExists e){
        ErroPadrao e1 = new ErroPadrao(
            LocalDateTime.now(),
            HttpStatus.CONFLICT.value(),
            e.getMessage()
        );

        return ResponseEntity.status(HttpStatus.CONFLICT).body(e1);
    }


    //UsuarioListIsEmpty
    @ExceptionHandler(UsuarioListIsEmpty.class)
    public ResponseEntity<ErroPadrao> usuarioListIsEmpty(UsuarioListIsEmpty e){
        ErroPadrao e1 = new ErroPadrao(
            LocalDateTime.now(),
            HttpStatus.NOT_FOUND.value(),
            e.getMessage()
        );

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e1);
    }


    //FieldIsNullException
    @ExceptionHandler(FieldIsNullException.class)
    public ResponseEntity<ErroPadrao> fieldIsNullException(FieldIsNullException e){
        ErroPadrao e1 = new ErroPadrao(
            LocalDateTime.now(),
            HttpStatus.EXPECTATION_FAILED.value(),
            e.getMessage()
        );

        return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(e1);
    }


    //BodyIsNullException
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<ErroPadrao> bodyIsNullException(){
        ErroPadrao e1 = new ErroPadrao(
            LocalDateTime.now(),
            HttpStatus.EXPECTATION_FAILED.value(),
            "O body da requisicao nao foi informado"
        );

        return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(e1);
    }


    //UsuarioNotFound
    @ExceptionHandler(UsuarioNotFound.class)
    public ResponseEntity<ErroPadrao> usuarioNotFound(UsuarioNotFound e){
        ErroPadrao e1 = new ErroPadrao(
            LocalDateTime.now(),
            HttpStatus.NOT_FOUND.value(),
            e.getMessage()
        );

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e1);
    }


    //UsuarioAlreadyHasThisRole
    @ExceptionHandler(UsuarioAlreadyHasThisRole.class)
    public ResponseEntity<ErroPadrao> usuarioAlreadyHasThisRole(UsuarioAlreadyHasThisRole e){
        ErroPadrao e1 = new ErroPadrao(
            LocalDateTime.now(),
            HttpStatus.CONFLICT.value(),
            e.getMessage()
        );

        return ResponseEntity.status(HttpStatus.CONFLICT).body(e1);
    }



    //TESTEEEEEEEEE

    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<ErroPadrao> handleAccessDeniedException(AccessDeniedException ex) {
        ErroPadrao erroPadrao = new ErroPadrao(
            LocalDateTime.now(),
            HttpStatus.FORBIDDEN.value(),
            "Acesso negado: você não tem permissão para acessar este recurso"
        );

        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(erroPadrao);
    }

    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity<ErroPadrao> handleBadCredentialsException(BadCredentialsException ex) {
        ErroPadrao erroPadrao = new ErroPadrao(
            LocalDateTime.now(),
            HttpStatus.UNAUTHORIZED.value(),
            "Credenciais inválidas"
        );

        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(erroPadrao);
    }
}
