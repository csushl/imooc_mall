package com.imooc.mall.service.impl;

import com.imooc.mall.common.Constant;
import com.imooc.mall.service.EmailService;
import java.util.concurrent.TimeUnit;
import net.sf.jsqlparser.expression.operators.arithmetic.Concat;
import org.redisson.Redisson;
import org.redisson.api.RBucket;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

/**
 * 描述：     EmailService实现类
 */
@Service
public class EmailServiceImpl implements EmailService {

    @Autowired
    private JavaMailSender mailSender;

    @Override
    public void sendSimpleMessage(String to, String subject, String text) {
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setFrom(Constant.EMAIL_FROM);
        simpleMailMessage.setTo(to);
        simpleMailMessage.setSubject(subject);
        simpleMailMessage.setText(text);
        mailSender.send(simpleMailMessage);
    }

    @Override
    public Boolean saveEmailToRedis(String emailAddress, String verificationCode) {
        RedissonClient client = Redisson.create();
        RBucket<String> bucket = client.getBucket(emailAddress);
        boolean exists = bucket.isExists();
        if (!exists) {
            bucket.set(verificationCode, 60, TimeUnit.SECONDS);
            return true;
        }
        return false;
    }


    @Override
    public Boolean checkEmailAndCode(String emailAddress, String verificationCode) {
        RedissonClient client = Redisson.create();
        RBucket<String> bucket = client.getBucket(emailAddress);
        boolean exists = bucket.isExists();
        if (exists) {
            String code = bucket.get();
            //redis里存储的验证码，和用户传过来的一致，则校验通过
            if (code.equals(verificationCode)) {
                return true;
            }
        }
        return false;
    }
}
