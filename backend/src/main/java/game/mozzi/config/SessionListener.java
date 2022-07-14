package game.mozzi.config;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSessionAttributeListener;
import javax.servlet.http.HttpSessionBindingEvent;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;
import java.util.ArrayList;

import static game.mozzi.config.Constants.SESSION_NAME;


/**
 *  작성자 : beomchul.kim@lotte.net
 *  SessionListener
 */

// 현재 로그인유저 및 유저수 확인용 세션리스너
@Slf4j
@Getter
@Component
@WebListener
public class SessionListener implements HttpSessionAttributeListener , HttpSessionListener {

    private ArrayList<String> loginList = new ArrayList<>();
    private Long totalUser = 0L;

    @Override
    public void attributeAdded(HttpSessionBindingEvent eventSession) {
        // 이미 로그인유저일시
        if(loginList.contains((String) eventSession.getValue())){
            log.debug("이미 로그인한 유저");
            return;
        }else{
            // 로그인유저 소셜아이디 저장
            if(eventSession.getName().equals(SESSION_NAME)){
                loginList.add((String) eventSession.getValue());
                totalUser++;
                log.info("#### SESSION ADDED : LOGIN USER INFO = {} , CNT =  {}" , loginList, totalUser);
            }
        }
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent se) {
        String destoryedSocialId = (String) se.getSession().getAttribute(SESSION_NAME);
        if(loginList.contains(destoryedSocialId)){
            loginList.remove(destoryedSocialId);
            totalUser--;

            log.info("#### SESSION DESTROYED : LOGIN USER INFO = {} , CNT =  {}" , loginList, totalUser);
        }
    }

    @Override
    public void attributeRemoved(HttpSessionBindingEvent eventSession) {
        // 로그인유저 소셜아이디 삭제
        if(eventSession.getName().equals(SESSION_NAME) && loginList.contains((String) eventSession.getValue())){
            loginList.remove((String) eventSession.getValue());
            totalUser--;
            log.info("#### SESSION REMOVED : LOGIN USER INFO = {} , CNT =  {}" , loginList, totalUser);
        }
    }

}