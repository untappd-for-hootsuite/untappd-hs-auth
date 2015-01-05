Untappd for Hootsuite - Auth
===

This module handles the callback from Untappd's Oauth signin. It exchanges the auth code for a token and redirects to the main app.

How it works
---
Your instance of the Untappd for Hootsuite app should have a "Sign into Untappd" link which sends the user directly to Untappd's Oauth endpoint. That endpoint will complete and forward the user back to this service's `/callback` endpoint. The endpoint exchanges the temporary token for full credentials. For now, the user is then redirected to app.untappdforhootsuite.com with the full token as a query parameter.

The long-term plan is to have the tokens stored in a DB shared between this service and the main app so that the credentials are not exposed to the user.
