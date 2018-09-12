[![Build Status](https://travis-ci.org/Gendalfion/password-helper.svg?branch=master)](https://travis-ci.org/Gendalfion/password-helper)

#### Last snapshot build as [jar](https://gendalfion.github.io/password-helper/release.jar) or exe

# password-helper

Helper tool for login/password input automation.

## Compilation
    mvn clean package
    
## Usage
    java -jar ./target/password-helper.jar <login> <password>
    
Utility has support for system environment variables:
    
    java -jar ./target/password-helper.jar $login_env_var_name $password_env_var_name