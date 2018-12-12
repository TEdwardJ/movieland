package edu.eteslenko.movieland.service.security;

import edu.eteslenko.movieland.dao.UserDao;
import edu.eteslenko.movieland.entity.User;
import edu.eteslenko.movieland.entity.dto.SessionUserDto;
import edu.eteslenko.movieland.service.SecurityService;
import org.apache.commons.codec.digest.DigestUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
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
    private long sessionExpirationTimeoutSecs;

    @Override
    public SessionUserDto auth(User incomeUser) {
        User user = userDao.getOne(incomeUser.getEmail());
        String enteredPassword = getMd5(incomeUser.getPassword() + user.getSole());
        if (enteredPassword.equals(user.getPassword())) {
            String token = getUserToken(user).orElse(null);
            if (token == null) {
                token = UUID.randomUUID().toString();
                sessionPool.put(token, new Session(user, token));
            } else if (!isExpired(sessionPool.get(token))) {
                logger.debug("I know you, user. Your session has not been expired yet");
            }
            return new SessionUserDto(token, user.getLogin());
        }
        return null;
    }

    private boolean isExpired(Session session) {
        return session
                .getStartTime()
                .plus(sessionExpirationTimeoutSecs, ChronoUnit.SECONDS)
                .isBefore(LocalDateTime.now());
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

    @Scheduled(initialDelayString = "${session.ExpirationTimeoutSecs:7200000}", fixedDelayString = "${session.cleanUpPeriod:600000}")
    void clean() {
        logger.debug("Start clean outdated sessions tokens");
        for (Map.Entry<String, Session> sessionEntry : sessionPool.entrySet()) {
            if (isExpired(sessionEntry.getValue())) {
                sessionPool.remove(sessionEntry.getKey());
            }
        }
    }

    public static String getMd5(String st) {
        String md5Hex = DigestUtils.md5Hex(st);
        return md5Hex;
    }

    @Autowired
    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    @Value("${session.ExpirationTimeoutSecs:7200}")
    public void setSessionExpirationTimeoutSecs(long sessionExpirationTimeoutSecs) {
        this.sessionExpirationTimeoutSecs = sessionExpirationTimeoutSecs;
    }
}
