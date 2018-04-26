# password-helper

Helper tool for login/password input automation.

## Compilation
    mvn clean package
    
## Usage
    java -jar ./target/password-helper.jar <login> <password>
    
Utility has support for system environment variables:
    
    java -jar ./target/password-helper.jar $login_env_var_name $password_env_var_name