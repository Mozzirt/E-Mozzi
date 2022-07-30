package game.mozzi.handler.ex;

import java.util.Map;

// api 내에서의 Exception이 발생시 해당 런타임 에러 사용
public class CustomValidationApiException extends RuntimeException{


    // 객체를 구분할때
    private static final long serialVersionUID =1L;

    private Map<String,String> errorMap;

    public CustomValidationApiException(String message){
        super(message); //부모한테 던짐
    }

    public CustomValidationApiException(String message, Map<String,String> errorMap){
        super(message); //부모한테 던짐
        this.errorMap=errorMap;
    }

    public Map<String,String> getErrorMap(){
        return errorMap;
    }


}
