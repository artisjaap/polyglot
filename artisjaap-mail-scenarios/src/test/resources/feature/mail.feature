Feature: sending emails
  Scenario: sending emails
    Given A mail template with code CODE, subject: "a new mail" and body
    """
      Dear Master,

      This is my first test mail

      Best regards,
      Bot
    """