> 本文由 [简悦 SimpRead](http://ksria.com/simpread/) 转码， 原文地址 [docs.github.com](https://docs.github.com/en/rest/quickstart?apiVersion=2022-11-28)

> Learn how to get started with the GitHub REST API.

This article describes how to quickly get started with the GitHub REST API using GitHub CLI, JavaScript, or `curl`. For a more detailed guide, see "[Getting started with the REST API](chrome-extension://ijllcpnolfcooahcekpamkbidhejabll/en/rest/guides/getting-started-with-the-rest-api)."

[](#getting-started-using-github-cli)Getting started using GitHub CLI
---------------------------------------------------------------------

### [](#using-github-cli-in-the-command-line)Using GitHub CLI in the command line

GitHub CLI is the easiest way to use the GitHub REST API from the command line.

1.  Install GitHub CLI if you haven't installed it yet. For installation instructions, see the [GitHub CLI repository](https://github.com/cli/cli#installation).
    
2.  Use the `auth login` subcommand to authenticate to GitHub CLI. For more information, see the [GitHub CLI `auth login` documentation](https://cli.github.com/manual/gh_auth_login).
    
    ```
    gh auth login
    
    ```
    
3.  Use the `api` subcommand to make your API request. For more information, see the [GitHub CLI `api` documentation](https://cli.github.com/manual/gh_api).
    
    ```
    gh api repos/octocat/Spoon-Knife/issues
    
    ```
    

### [](#using-github-cli-in-github-actions)Using GitHub CLI in GitHub Actions

You can also use GitHub CLI in your GitHub Actions workflows. For more information, see "[Using GitHub CLI in workflows](chrome-extension://ijllcpnolfcooahcekpamkbidhejabll/en/actions/using-workflows/using-github-cli-in-workflows)."

Instead of using the `gh auth login` command, pass an access token as an environment variable called `GH_TOKEN`. GitHub recommends that you use the built-in `GITHUB_TOKEN` instead of creating a token. If this is not possible, store your token as a secret and replace `GITHUB_TOKEN` in the example below with the name of your secret. For more information about `GITHUB_TOKEN`, see "[Automatic token authentication](chrome-extension://ijllcpnolfcooahcekpamkbidhejabll/en/actions/security-guides/automatic-token-authentication)."For more information about secrets, see"[Encrypted secrets](chrome-extension://ijllcpnolfcooahcekpamkbidhejabll/en/actions/security-guides/encrypted-secrets)."

```
on:
  workflow_dispatch:
jobs:
  use_api:
    runs-on: ubuntu-latest
    permissions:
      issues: read
    steps:
      - env:
          GH_TOKEN: ${{ secrets.GITHUB_TOKEN }}
        run: |
          gh api repos/octocat/Spoon-Knife/issues


```

If you are authenticating with a GitHub App, you can create an installation access token within your workflow:

1.  Store your GitHub App's ID as a secret. In the following example, replace `APP_ID` with the name of the secret. You can find your app ID on the settings page for your app or through the API. For more information, see "[GitHub Apps](chrome-extension://ijllcpnolfcooahcekpamkbidhejabll/en/rest/apps/apps#get-an-app)"in the REST API documentation. For more information about secrets, see"[Encrypted secrets](chrome-extension://ijllcpnolfcooahcekpamkbidhejabll/en/actions/security-guides/encrypted-secrets)."
    
2.  Generate a private key for your app. Store the contents of the resulting file as a secret. (Store the entire contents of the file, including `-----BEGIN RSA PRIVATE KEY-----` and `-----END RSA PRIVATE KEY-----`.) In the following example, replace `APP_PEM` with the name of the secret. For more information, see "[Authenticating with GitHub Apps](chrome-extension://ijllcpnolfcooahcekpamkbidhejabll/en/apps/creating-github-apps/authenticating-with-a-github-app/authenticating-with-github-apps#generating-a-private-key)."
    
3.  Add a step to generate a token, and use that token instead of `GITHUB_TOKEN`. Note that this token will expire after 60 minutes. For example:
    
    ```
    
    on:
      workflow_dispatch:
    jobs:
      track_pr:
        runs-on: ubuntu-latest
        steps:
          - name: Generate token
            id: generate_token
            uses: tibdex/github-app-token@36464acb844fc53b9b8b2401da68844f6b05ebb0
            with:
              app_id: ${{ secrets.APP_ID }}
              private_key: ${{ secrets.APP_PEM }}
    
          - name: Use API
            env:
              GH_TOKEN: ${{ steps.generate_token.outputs.token }}
            run: |
              gh api repos/octocat/Spoon-Knife/issues
    
    
    ```
    

[](#getting-started-using-javascript)Getting started using JavaScript
---------------------------------------------------------------------

You can use Octokit.js to interact with the GitHub REST API in your JavaScript scripts. For more information, see "[Scripting with the REST API and JavaScript](chrome-extension://ijllcpnolfcooahcekpamkbidhejabll/en/rest/guides/scripting-with-the-rest-api-and-javascript)."

### [](#using-octokitjs)Using Octokit.js

1.  Create an access token. For example, create a personal access token or a GitHub App user-to-server access token. For more information, see "[Creating a personal access token](chrome-extension://ijllcpnolfcooahcekpamkbidhejabll/en/authentication/keeping-your-account-and-data-secure/creating-a-personal-access-token)"or"[Identifying and authorizing users for GitHub Apps](chrome-extension://ijllcpnolfcooahcekpamkbidhejabll/en/developers/apps/building-github-apps/identifying-and-authorizing-users-for-github-apps)."
    
    **Warning**: Treat your access token like a password.
    
    To keep your token secure, you can store your token as a secret and run your script through GitHub Actions. For more information, see the "[Using Octokit.js in GitHub Actions](#using-octokitjs-in-github-actions)" section.
    
    You can also store your token as a Codespaces secret and run your script in Codespaces. For more information, see "[Managing encrypted secrets for your codespaces](chrome-extension://ijllcpnolfcooahcekpamkbidhejabll/en/codespaces/managing-your-codespaces/managing-encrypted-secrets-for-your-codespaces)."
    
    If these options are not possible, consider using another service such as [the 1Password CLI](https://developer.1password.com/docs/cli/secret-references/) to store your token securely.
    
2.  Install `octokit`. For example, `npm install octokit`. For other ways to install or load `octokit`, see [the Octokit.js README](https://github.com/octokit/octokit.js/#readme).
    
3.  Import `octokit` in your script. For example, `import { Octokit } from "octokit";`. For other ways to import `octokit`, see [the Octokit.js README](https://github.com/octokit/octokit.js/#readme).
    
4.  Create an instance of `Octokit` with your token. Replace `YOUR-TOKEN` with your token.
    
    ```
    const octokit = new Octokit({
      auth: 'YOUR-TOKEN'
    });
    
    
    ```
    
5.  Use `octokit.request` to execute your request. Send the HTTP method and path as the first argument. Specify any path, query, and body parameters in an object as the second argument. For example, in the following request the HTTP method is `GET`, the path is `/repos/{owner}/{repo}/issues`, and the parameters are `owner: "octocat"` and `repo: "Spoon-Knife"`.
    
    ```
    await octokit.request("GET /repos/{owner}/{repo}/issues", {
      owner: "octocat",
      repo: "Spoon-Knife",
    });
    
    
    ```
    

### [](#using-octokitjs-in-github-actions)Using Octokit.js in GitHub Actions

You can also execute your JavaScript scripts in your GitHub Actions workflows. For more information, see "[Workflow syntax for GitHub Actions](chrome-extension://ijllcpnolfcooahcekpamkbidhejabll/en/actions/using-workflows/workflow-syntax-for-github-actions#jobsjob_idstepsrun)."

GitHub recommends that you use the built-in `GITHUB_TOKEN` instead of creating a token. If this is not possible, store your token as a secret and replace `GITHUB_TOKEN` in the example below with the name of your secret. For more information about `GITHUB_TOKEN`, see "[Automatic token authentication](chrome-extension://ijllcpnolfcooahcekpamkbidhejabll/en/actions/security-guides/automatic-token-authentication)."For more information about secrets, see"[Encrypted secrets](chrome-extension://ijllcpnolfcooahcekpamkbidhejabll/en/actions/security-guides/encrypted-secrets)."

The following example workflow:

1.  Checks out the repository content
2.  Sets up Node.js
3.  Installs `octokit`
4.  Stores the value of `GITHUB_TOKEN` as an environment variable called `TOKEN` and runs `.github/actions-scripts/use-the-api.mjs`, which can access that environment variable as `process.env.TOKEN`

Example workflow:

```
on:
  workflow_dispatch:
jobs:
  use_api_via_script:
    runs-on: ubuntu-latest
    permissions:
      issues: read
    steps:
      - name: Check out repo content
        uses: actions/checkout@v3

      - name: Setup Node
        uses: actions/setup-node@v3
        with:
          node-version: '16.17.0'
          cache: npm

      - name: Install dependencies
        run: npm install octokit

      - name: Run script
        run: |
          node .github/actions-scripts/use-the-api.mjs
        env:
          TOKEN: ${{ secrets.GITHUB_TOKEN }}


```

Example JavaScript script, with the file path `.github/actions-scripts/use-the-api.mjs`:

```
import { Octokit } from "octokit"

const octokit = new Octokit({
  auth: process.env.TOKEN
});

try {
  const result = await octokit.request("GET /repos/{owner}/{repo}/issues", {
      owner: "octocat",
      repo: "Spoon-Knife",
    });

  const titleAndAuthor = result.data.map(issue => {title: issue.title, authorID: issue.user.id})

  console.log(titleAndAuthor)

} catch (error) {
  console.log(`Error! Status: ${error.status}. Message: ${error.response.data.message}`)
}


```

If you are authenticating with a GitHub App, you can create an installation access token within your workflow:

1.  Store your GitHub App's ID as a secret. In the following example, replace `APP_ID` with the name of the secret. You can find your app ID on the settings page for your app or through the App API. For more information, see "[GitHub Apps](chrome-extension://ijllcpnolfcooahcekpamkbidhejabll/en/rest/apps/apps#get-an-app)."For more information about secrets, see"[Encrypted secrets](chrome-extension://ijllcpnolfcooahcekpamkbidhejabll/en/actions/security-guides/encrypted-secrets)."
    
2.  Generate a private key for your app. Store the contents of the resulting file as a secret. (Store the entire contents of the file, including `-----BEGIN RSA PRIVATE KEY-----` and `-----END RSA PRIVATE KEY-----`.) In the following example, replace `APP_PEM` with the name of the secret. For more information, see "[Authenticating with GitHub Apps](chrome-extension://ijllcpnolfcooahcekpamkbidhejabll/en/apps/creating-github-apps/authenticating-with-a-github-app/authenticating-with-github-apps#generating-a-private-key)."
    
3.  Add a step to generate a token, and use that token instead of `GITHUB_TOKEN`. Note that this token will expire after 60 minutes. For example:
    
    ```
    
    on:
      workflow_dispatch:
    jobs:
      use_api_via_script:
        runs-on: ubuntu-latest
        steps:
          - name: Check out repo content
            uses: actions/checkout@v3
    
          - name: Setup Node
            uses: actions/setup-node@v3
            with:
              node-version: '16.17.0'
              cache: npm
    
          - name: Install dependencies
            run: npm install octokit
    
          - name: Generate token
            id: generate_token
            uses: tibdex/github-app-token@36464acb844fc53b9b8b2401da68844f6b05ebb0
            with:
              app_id: ${{ secrets.APP_ID }}
              private_key: ${{ secrets.APP_PEM }}
    
          - name: Run script
            run: |
              node .github/actions-scripts/use-the-api.mjs
            env:
              TOKEN: ${{ steps.generate_token.outputs.token }}
    
    
    ```
    

[](#getting-started-using-curl)Getting started using `curl`
-----------------------------------------------------------

### [](#using-curl-in-the-command-line)Using `curl` in the command line

**Notes:**

*   The following example is intended for GitHub.com. If you'd prefer to try the example using GitHub, you must replace `https://api.github.com` with `https://api.github.com`, and replace `HOSTNAME` with the hostname for . You must also replace `octocat/Spoon-Knife` with a repository on GitHub.
*   If you want to make API requests from the command line, GitHub recommends that you use GitHub CLI, which simplifies authentication and requests. For more information about getting started with the REST API using GitHub CLI, see the GitHub CLI version of this article.

1.  Install `curl` if it isn't already installed on your machine. To check if `curl` is installed, execute `curl --version` in the command line. If the output is information about the version of `curl`, it is installed. If you get a message similar to `command not found: curl`, you need to download and install `curl`. For more information, see [the curl project download page](https://curl.se/download.html).
    
2.  Create an access token. For example, create a personal access token or a GitHub App user-to-server access token. For more information, see "[Creating a personal access token](chrome-extension://ijllcpnolfcooahcekpamkbidhejabll/en/authentication/keeping-your-account-and-data-secure/creating-a-personal-access-token)"or"[Identifying and authorizing users for GitHub Apps](chrome-extension://ijllcpnolfcooahcekpamkbidhejabll/en/developers/apps/building-github-apps/identifying-and-authorizing-users-for-github-apps)."
    
    **Warning**: Treat your access token like a password.
    
    To keep your token secure, you can store your token as a Codespaces secret and use the command line through Codespaces. For more information, see "[Managing encrypted secrets for your codespaces](chrome-extension://ijllcpnolfcooahcekpamkbidhejabll/en/codespaces/managing-your-codespaces/managing-encrypted-secrets-for-your-codespaces)."
    
    You can also use GitHub CLI instead of `curl`. GitHub CLI will take care of authentication for you. For more information, see the GitHub CLI version of this page.
    
    If these options are not possible, consider using another service such as [the 1Password CLI](https://developer.1password.com/docs/cli/secret-references/) to store your token securely.
    
3.  Use the `curl` command to make your request. Pass your token in an `Authorization` header. Replace `YOUR-TOKEN` with your token.
    
    ```
    curl --request GET \
    --url "https://api.github.com/repos/octocat/Spoon-Knife/issues" \
    --header "Accept: application/vnd.github+json" \
    --header "Authorization: Bearer YOUR-TOKEN"
    
    ```
    
    **Note:** In most cases, you can use `Authorization: Bearer` or `Authorization: token` to pass a token. However, if you are passing a JSON web token (JWT), you must use `Authorization: Bearer`.
    

### [](#using-curl-commands-in-github-actions)Using `curl` commands in GitHub Actions

You can also use `curl` commands in your GitHub Actions workflows.

GitHub recommends that you use the built-in `GITHUB_TOKEN` instead of creating a token. If this is not possible, store your token as a secret and replace `GITHUB_TOKEN` in the example below with the name of your secret. For more information about `GITHUB_TOKEN`, see "[Automatic token authentication](chrome-extension://ijllcpnolfcooahcekpamkbidhejabll/en/actions/security-guides/automatic-token-authentication)."For more information about secrets, see"[Encrypted secrets](chrome-extension://ijllcpnolfcooahcekpamkbidhejabll/en/actions/security-guides/encrypted-secrets)."

```
on:
  workflow_dispatch:
jobs:
  use_api:
    runs-on: ubuntu-latest
    permissions:
      issues: read
    steps:
      - env:
          GH_TOKEN: ${{ secrets.GITHUB_TOKEN }}
        run: |
          curl --request GET \
          --url "https://api.github.com/repos/octocat/Spoon-Knife/issues" \
          --header "Accept: application/vnd.github+json" \
          --header "Authorization: Bearer $GH_TOKEN"


```

If you are authenticating with a GitHub App, you can create an installation access token within your workflow:

1.  Store your GitHub App's ID as a secret. In the following example, replace `APP_ID` with the name of the secret. You can find your app ID on the settings page for your app or through the App API. For more information, see "[GitHub Apps](chrome-extension://ijllcpnolfcooahcekpamkbidhejabll/en/rest/apps/apps#get-an-app)."For more information about secrets, see"[Encrypted secrets](chrome-extension://ijllcpnolfcooahcekpamkbidhejabll/en/actions/security-guides/encrypted-secrets)."
    
2.  Generate a private key for your app. Store the contents of the resulting file as a secret. (Store the entire contents of the file, including `-----BEGIN RSA PRIVATE KEY-----` and `-----END RSA PRIVATE KEY-----`.) In the following example, replace `APP_PEM` with the name of the secret. For more information, see "[Authenticating with GitHub Apps](chrome-extension://ijllcpnolfcooahcekpamkbidhejabll/en/apps/creating-github-apps/authenticating-with-a-github-app/authenticating-with-github-apps#generating-a-private-key)."
    
3.  Add a step to generate a token, and use that token instead of `GITHUB_TOKEN`. Note that this token will expire after 60 minutes. For example:
    
    ```
    
    on:
      workflow_dispatch:
    jobs:
      use_api:
        runs-on: ubuntu-latest
        steps:
          - name: Generate token
            id: generate_token
            uses: tibdex/github-app-token@36464acb844fc53b9b8b2401da68844f6b05ebb0
            with:
              app_id: ${{ secrets.APP_ID }}
              private_key: ${{ secrets.APP_PEM }}
    
          - name: Use API
            env:
              GH_TOKEN: ${{ steps.generate_token.outputs.token }}
            run: |
              curl --request GET \
              --url "https://api.github.com/repos/octocat/Spoon-Knife/issues" \
              --header "Accept: application/vnd.github+json" \
              --header "Authorization: Bearer $GH_TOKEN"
    
    
    ```
    

[](#next-steps)Next steps
-------------------------

For a more detailed guide, see "[Getting started with the REST API](chrome-extension://ijllcpnolfcooahcekpamkbidhejabll/en/rest/guides/getting-started-with-the-rest-api)."