Google Login Hook
=================

This is a Hook for Liferay that allows users to Sign in using Google account.

The hook is using Google OAuth 2.0 API.

In order to use this Hook you follow this steps:

1. Create a new Web Application using Google Cloud Console (https://cloud.google.com/console)
![Alt text](/add-google-app.jpg "Add Google App")

2. Select OAuth 2.0 Client ID

3. Add the redirect URI that matches your portal settings. It should contain (/c/portal/google_login?cmd=token).
i.e (http://localhost:8080/c/portal/google_login?cmd=token)

4. Click the Download JSON button to obtain the file client_secrets.json.
![Alt text](/configure-google-app.jpg "Configure Google App")

5. Replace the file client_secrets.json in com.liferay.google with the downloaded file.

Then, you're ready to go. Deploy the hook and enjoy :)
