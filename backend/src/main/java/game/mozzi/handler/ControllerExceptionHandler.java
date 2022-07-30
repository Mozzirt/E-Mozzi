package game.mozzi.handler;

import game.mozzi.dto.CMRespDto;
import game.mozzi.handler.ex.CustomValidationApiException;
import game.mozzi.handler.ex.CustomValidationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

@RestController
@ControllerAdvice // 모든 exception 다 낚아챔
public class ControllerExceptionHandler {

    @ExceptionHandler(CustomValidationException.class)
    public ResponseEntity<?> apiException(CustomValidationException e){
        return new ResponseEntity<>(new CMRespDto<>(-1,e.getMessage(),null), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(CustomValidationApiException.class) //RuntimeException이 CustomValidationApiException일어나는 모든 일에 캐칭
    public ResponseEntity<?> validationApiException(CustomValidationApiException e){
        System.out.println("==================================나 실행됨==============================");
        // CMRespDto, Script 비교
        // 1. 클라이언트에게 응답할때는 Script  좋음.
        // 2. Ajax 통신 - CMRespDto
        // 3. Android 통신 - CMRespDto

        return new ResponseEntity<CMRespDto<?>>(new CMRespDto<>(-1,e.getMessage(),e.getErrorMap()),HttpStatus.BAD_REQUEST);
    }

}
