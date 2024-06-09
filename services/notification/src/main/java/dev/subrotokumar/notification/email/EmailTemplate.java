package dev.subrotokumar.notification.email;

import static java.lang.String.format;

public class EmailTemplate {
    public static String loginMagiclink(String magiclink){
        return format(
            """
            <!DOCTYPE html>
            <html lang="en">
            <head>
                <meta charset="UTF-8">
                <meta name="viewport" content="width=device-width, initial-scale=1.0">
                <title>Login to DXDeployer</title>
                <style>
                    body {
                        font-family: 'Arial', sans-serif;
                        background-color: #f4f4f9;
                        margin: 0;
                        padding: 0;
                    }
                    .container {
                        max-width: 600px;
                        margin: 0 auto;
                        background-color: #ffffff;
                        padding: 20px;
                        border-radius: 10px;
                        box-shadow: 0 0 15px rgba(0, 0, 0, 0.1);
                        overflow: hidden;
                    }
                    .header {
                        text-align: center;
                        padding: 30px 0;
                        background-color: #fff;
                        color: #ffffff;
                    }
                    .header img {
                        max-width: 120px;
                    }
                    .content {
                        text-align: center;
                        padding: 30px 20px;
                    }
                    .content h1 {
                        color: #333333;
                        margin-bottom: 20px;
                    }
                    .content p {
                        color: #555555;
                        font-size: 16px;
                        line-height: 1.5;
                        margin-bottom: 30px;
                    }
                    .button {
                        display: inline-block;
                        padding: 15px 30px;
                        font-size: 18px;
                        color: #ffffff;
                        background-color: #000;
                        text-decoration: none;
                        border-radius: 5px;
                        transition: background-color 0.3s;
                    }
                    .button:hover {
                        background-color: #0056b3;
                    }
                    .footer {
                        text-align: center;
                        padding: 20px 0;
                        background-color: #f4f4f9;
                        color: #888888;
                        font-size: 14px;
                    }
                    .footer a {
                        color: #007bff;
                        text-decoration: none;
                        font-weight: bold;
                    }
                </style>
            </head>
            <body>
                <div class="container">
                    <div class="header">
                        <img src="https://github.com/subrotokumar/public/blob/main/meteor-dark.png?raw=true" alt="DXDeployer Logo">
                    </div>
                    <div class="content">
                        <h1>Welcome to DXDeployer!</h1>
                        <p>We're excited to have you on board. To access your account, simply click the button below:</p>
                        <a href="%s" class="button">Login to DXDeployer</a>
                        <p>If you did not request this email, please ignore it. This link will expire in 5 minutes for your security.</p>
                    </div>
                    <div class="footer">
                        <p>Need help? <a href="https://dxdeployer.subrotokumar.com/support">Contact Support</a></p>
                        <p>&copy; 2024 DXDeployer. All rights reserved.</p>
                    </div>
                </div>
            </body>
            </html>
            """, 
            magiclink
        );
    }

    public static String createProjectDeployment(String username, String projectName, String dashboardLink){
        return format("""
        <!DOCTYPE html>
        <html lang="en">
        <head>
            <meta charset="UTF-8">
            <meta name="viewport" content="width=device-width, initial-scale=1.0">
            <title>Project Deployment Notification</title>
            <style>
                body {
                    font-family: 'Helvetica Neue', Helvetica, Arial, sans-serif;
                    background-color: #f9f9f9;
                    margin: 0;
                    padding: 0;
                }
                .container {
                    max-width: 600px;
                    margin: 20px auto;
                    background-color: #ffffff;
                    border-radius: 8px;
                    overflow: hidden;
                    box-shadow: 0 0 20px rgba(0, 0, 0, 0.1);
                    border: 1px solid #ddd;
                }
                .header {
                    background-color: #000000;
                    color: #ffffff;
                    padding: 20px;
                    text-align: center;
                }
                .header h1 {
                    margin: 0;
                    font-size: 24px;
                }
                .content {
                    padding: 30px 20px;
                    line-height: 1.6;
                    color: #333333;
                }
                .content h2 {
                    color: #000000;
                    font-size: 20px;
                    margin-top: 0;
                }
                .content p {
                    margin: 10px 0;
                }
                .button {
                    display: inline-block;
                    background-color: #000000;
                    color: #ffffff;
                    padding: 12px 20px;
                    text-decoration: none;
                    border-radius: 5px;
                    margin-top: 20px;
                    transition: background-color 0.3s;
                }
                .button:hover {
                    background-color: #333333;
                }
                .footer {
                    background-color: #000000;
                    color: #ffffff;
                    padding: 15px;
                    text-align: center;
                }
                .footer p {
                    margin: 0;
                    font-size: 14px;
                }
            </style>
        </head>
        <body>
            <div class="container">
                <div class="header">
                    <h1>DxDeployer</h1>
                </div>
                <div class="content">
                    <h2>Project Deployment Notification</h2>
                    <p>Dear %s,</p>
                    <p>We are excited to inform you that your project "<strong>%s</strong>" has started deploying on the DxDeployer platform.</p>
                    <p>Please monitor the deployment process through your dashboard. If you encounter any issues or need further assistance, feel free to contact our support team.</p>
                    <p>Thank you for using DxDeployer!</p>
                    <br>
                    <p>Best regards,</p>
                    <p>The DxDeployer Team</p>
                    <a href="%s" class="button">Go to Dashboard</a>
                </div>
                <div class="footer">
                    <p>&copy; 2024 DXDeployer. All rights reserved.</p>
                </div>
            </div>
        </body>
        </html>
        """, username, projectName, dashboardLink);
    }
}