# Security: Simple Spring Security Example - SWEN90007 

This project demonstrates the use of Spring Security in a JEE (non-spring based) Web Application.

## User Details and Password Encoding

This project uses a custom user detail service to load user details for auth* purposes. The users are created in memory, see UserDetailsServiceImpl for details.

Passwords are encoded using BCrypt.

## Useful Resources

Spring security documentation:
https://docs.spring.io/spring-security/reference/

Security expressions:
https://www.baeldung.com/spring-security-expressions


