package org.watch.com;

import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.watch.com.model.LoginModel;

/**
 * 用于判断user
 */
@Configuration
public class MyShiroRealm2 extends AuthorizingRealm {

    private static final Logger log = LoggerFactory.getLogger(MyShiroRealm2.class);

    @Override
    public String getName() {
        return "user";
    }

    //认证
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {

        MyUsernamePasswordToken myToken = (MyUsernamePasswordToken) token;
        if (myToken.getSignature() == null || myToken.getSignature().isEmpty()) {
            //请从新登录;
            log.info("令牌为空");
            throw new UnknownAccountException("账号或密码错误!");
        }
//        账号密码登录
        if (myToken.getUsername() != null && !myToken.getUsername().isEmpty()) {
            LoginModel model = new LoginModel();
            model.setUsername("xuesemofa");
            model.setPassword("xuesemofa123");
            return new SimpleAuthenticationInfo(
                    model,
                    model.getPassword(),
                    getName()
            );
        } else {
            //令牌登录
//            myToken.setUsername(myToken.getSignature());
            return new SimpleAuthenticationInfo(
                    myToken,
                    myToken.getSignature(),
                    getName()
            );
        }
    }

    //授权
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        info.addRole("user");
        return info;
    }

}