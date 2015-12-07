package com.mihailsergeevichs.todo.manager.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.*;
import org.springframework.core.env.Environment;
import org.springframework.security.crypto.encrypt.Encryptors;
import org.springframework.social.UserIdSource;
import org.springframework.social.config.annotation.ConnectionFactoryConfigurer;
import org.springframework.social.config.annotation.EnableSocial;
import org.springframework.social.config.annotation.SocialConfigurer;
import org.springframework.social.connect.ConnectionFactoryLocator;
import org.springframework.social.connect.ConnectionRepository;
import org.springframework.social.connect.UsersConnectionRepository;
import org.springframework.social.connect.jdbc.JdbcUsersConnectionRepository;
import org.springframework.social.connect.web.ConnectController;
import org.springframework.social.facebook.connect.FacebookConnectionFactory;
import org.springframework.social.security.AuthenticationNameUserIdSource;
import org.springframework.social.twitter.connect.TwitterConnectionFactory;

import javax.sql.DataSource;

@Configuration
@EnableSocial
public class SocialContext implements SocialConfigurer {

    protected static final String PROPERTY_TWITTER_CONSUMER_KEY = "twitter.consumer.key";
    protected static final String PROPERTY_TWITTER_CONSUMER_PASSWORD = "twitter.consumer.secret";
    protected static final String PROPERTY_FACEBOOK_APP_ID = "facebook.app.id";
    protected static final String PROPERTY_FACEBOOK_APP_SECRET = "facebook.app.secret";

    @Autowired
    private DataSource dataSource;

    /**
     * Configure Twitter and Facebook connection factories
     */
    @Override
    public void addConnectionFactories(ConnectionFactoryConfigurer cfConfig, Environment env) {
        cfConfig.addConnectionFactory(new TwitterConnectionFactory(
                env.getProperty(PROPERTY_TWITTER_CONSUMER_KEY),
                env.getProperty(PROPERTY_TWITTER_CONSUMER_PASSWORD)
        ));
        cfConfig.addConnectionFactory(new FacebookConnectionFactory(
                env.getProperty(PROPERTY_FACEBOOK_APP_ID),
                env.getProperty(PROPERTY_FACEBOOK_APP_SECRET)
        ));
    }

    /**
     *  UserIdSource determine the account ID of the user.
     */
    @Override
    public UserIdSource getUserIdSource() {
        return new AuthenticationNameUserIdSource();
    }


    @Override
    public UsersConnectionRepository getUsersConnectionRepository(ConnectionFactoryLocator connectionFactoryLocator) {
        return new JdbcUsersConnectionRepository(
                dataSource,
                connectionFactoryLocator,
                Encryptors.noOpText()
        );
    }

    /**
     * Set connection with the account provider and application
     */
    @Bean
    public ConnectController connectController(ConnectionFactoryLocator connectionFactoryLocator, ConnectionRepository connectionRepository) {
        return new ConnectController(connectionFactoryLocator, connectionRepository);
    }
}
