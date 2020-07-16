package com.Internshala.FoodShala.Configurations;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import javax.sql.DataSource;

@EnableGlobalMethodSecurity(prePostEnabled = true)
@EnableWebSecurity
@Configuration
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Autowired
    private DataSource dataSource;

    @Autowired
    public void globalSecurityConfig(AuthenticationManagerBuilder auth) throws  Exception {
        auth.jdbcAuthentication().dataSource(dataSource)
                .usersByUsernameQuery("select email, password, active from user where email = ?;")
                .authoritiesByUsernameQuery("select email, role from user where email=?;");
        auth.jdbcAuthentication().dataSource(dataSource)
                .usersByUsernameQuery("select email, password, active from restaurant where email = ?;")
                .authoritiesByUsernameQuery("select email, role from restaurant where email=?;");

    }

    @Override
    protected void configure(HttpSecurity http) throws  Exception{
            http.csrf().disable()
                    .authorizeRequests().antMatchers(HttpMethod.OPTIONS, "/**").permitAll()
                    .antMatchers("/addUser").permitAll()
                    .antMatchers("/addRestaurant").permitAll()
                    .antMatchers("/getAllRestaurants").permitAll()
                    .antMatchers("/getAllFoodItems").permitAll()
                    .antMatchers("/getAllFoodItemsOfRestaurant/{restaurantId}").permitAll()
                    .anyRequest().authenticated().and().httpBasic();
            http.cors();

    }
}
