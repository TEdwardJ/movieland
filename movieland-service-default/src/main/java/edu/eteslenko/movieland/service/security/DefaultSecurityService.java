package edu.eteslenko.movieland.service.security;

import edu.eteslenko.movieland.dao.UserDao;
import edu.eteslenko.movieland.entity.User;
import edu.eteslenko.movieland.entity.dto.SessionUserDto;
import edu.eteslenko.movieland.service.SecurityService;
import org.apache.commons.codec.digest.DigestUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class DefaultSecurityService implements SecurityService {

    private Logger logger = LoggerFactory.getLogger(getClass());
    private UserDao userDao;
    private Map<String, Session> sessionPool = new ConcurrentHashMap<>();

    @Override
    public SessionUserDto auth(User incomeUser) {
        User user = userDao.getOne(incomeUser.getEmail());
        String enteredPassword = md5Apache(incomeUser.getPassword() + user.getSole());
        User checkedUser = userDao.checkPassword(enteredPassword);
        if (checkedUser != null) {
            String token = getUserToken(checkedUser).orElse(null);
            if (token == null) {
                token = UUID.randomUUID().toString();
                Session session = new Session(checkedUser, token);
                sessionPool.put(token, session);
                return new SessionUserDto(token, user.getLogin());
            } else if (!isExpired(sessionPool.get(token))) {
                logger.debug("I know you, user. Your session has not been expired yet");
                return new SessionUserDto(token, checkedUser.getLogin());
            }
        }
        return null;
    }

    private boolean isExpired(Session session) {
        return session.getExpiredDate().plus(2, ChronoUnit.HOURS).isBefore(LocalDateTime.now());
    }

    private Optional<String> getUserToken(User checkedUser) {
        return sessionPool.values()
                .stream()
                .filter(t -> t.getUser().equals(checkedUser))
                .map(t -> t.getToken())
                .findFirst();
    }

    @Override
    public void logout(String token) {
        sessionPool.remove(token);
    }

    @Scheduled(fixedDelay = 5000, initialDelay = 5000)
    void clean() {
        logger.debug("Start clean outdated sessions tokens");
        for (Map.Entry<String, Session> sessionEntry : sessionPool.entrySet()) {
            if (isExpired(sessionEntry.getValue())) {
                sessionPool.remove(sessionEntry.getKey());
            }
        }
    }


    public static String md5Apache(String st) {
        String md5Hex = DigestUtils.md5Hex(st);
        return md5Hex;
    }

    @Autowired
    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }
}
