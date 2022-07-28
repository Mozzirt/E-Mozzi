package game.mozzi.config.response;

import lombok.Data;

/**
 *  작성자 : beomchul.kim@lotte.com
 *  Message
 */
@Data
public class Message {
    private StatusEnum status;
    private String message;
    private Object data;

    public Message() {
        this.status = StatusEnum.BAD_REQUEST;
        this.data = null;
        this.message = null;
    }

    public void setMessage(StatusEnum status, String message, Object data) {
        this.status = status;
        this.message = message;
        this.data = data;
    }
}
