import smtplib
from email.mime.text import MIMEText
from email.mime.multipart import MIMEMultipart

def send_email(receiver_email, word):
    # Replace these variables with your own values
    sender_email = 'hyungjin2949@gmail.com'
    receiver_email = receiver_email
    subject = 'Test Email'
    body = word
    smtp_server = 'smtp.gmail.com'
    smtp_port = 587
    username = 'hyungjin2949@gmail.com'
    password = 'lpbp zbvz cvvi rjdm'

    # Create the email message
    message = MIMEMultipart()
    message['From'] = sender_email
    message['To'] = receiver_email
    message['Subject'] = subject

    # Attach the body of the email
    message.attach(MIMEText(body, 'plain'))

    # Connect to the SMTP server
    with smtplib.SMTP(smtp_server, smtp_port) as server:
        # Log in to the email account
        server.starttls()
        server.login(username, password)

        # Send the email
        server.sendmail(sender_email, receiver_email, message.as_string())