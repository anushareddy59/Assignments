Feature: Sales Force Login App

Background: 
Given Open SalesForce Application


@passwordEmptyFailure
Scenario: Login with blank password
When user on "LoginPage"
When user enters username as "anu@tekarch.com"
When user enters password as ""
When user clicks on login button
Then empty password error message displayed


@loginSuccess
Scenario: Login with valid Credentials
When user on "LoginPage"
When user enters username as "anu@tekarch.com"
When user enters password as "DhruvDheem@123"
When user clicks on login button
When user on "HomePage"
Then homepage logo should be displayed


@rememberUserNameChack
Scenario: Ensure Username is retained after logout
When user on "LoginPage"
When user enters username as "anu@tekarch.com"
When user enters password as "DhruvDheem@123"
When user clicks remember me checkbox
When user clicks on login button
When user on "HomePage"
When user clicks on profile icon
When user clicks on logout
When user on "LoginPage"
Then username field contains "anu@tekarch.com"

@forgotPasswordCheck
Scenario: Ensure Forgot password link sends an email
When user on "LoginPage"
When user enters username as "anu@tekarch.com"
When user clicks forgot your password
When user on "ForgotPasswordPage"
When user enters forgot username as "anu@tekarch.com"
When user clicks continue button
Then check your email message is displayed


@errorLoginWrongInputs
Scenario: Login with invalid Credentials
When user on "LoginPage"
When user enters username as "123"
When user enters password as "22132"
When user clicks on login button
Then invalid credentials message is displayed