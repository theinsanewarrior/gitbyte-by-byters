# GitByte

GitByte is an AI-powered web IDE (under development) that can be accessed via a Chrome extension. It optimizes code and generates new code from Jira stories using ChatGPT APIs, enhancing productivity and streamlining development processes directly within your browser.

## Getting Started

### Step 1: Enable the GitByte Chrome Extension
1. Install the "GitByte Chrome Extension" from the Chrome Web Store.
2. Pin the extension to your Chrome toolbar for easy access.

### Step 2: Set Up the Backend
1. Clone the `byters` folder from this repository.
2. Create a local schema named `gitbyte`.
3. Change the username and password in the `application.properties` file to match your local database configuration.
4. Build the project using Java 17.
    ```sh
    ./gradlew build
    ```
5. Run the backend server.
    ```sh
    ./gradlew bootRun
    ```

### Step 3: Set Up the VSCode Plugin
1. Clone the VSCode plugin from this repository.
2. Navigate to the plugin directory and install the necessary dependencies.
    ```sh
    npm install
    ```
3. Press `F5` to start the plugin in a new VSCode window.

### Step 4: Start Developing
1. Go to the GitHub repository you want to develop.
2. Open the GitByte Chrome extension.
3. Select your desired tech stack from the dropdown list.
4. Enter your GitHub access token and username, then click `Submit`.
    - The extension will automatically clone your repository into the IDE.
5. Enter your ChatGPT API key to enable AI-assisted development.

### Enjoy a Smooth Development Process
Utilize GitByte's powerful features to optimize and generate code effortlessly. Enhance your development workflow with AI support, making coding faster and more efficient.

## Support
If you have any questions or need help, feel free to open an issue or contact us directly.

## Contributors
- Utkarsh Sharma (@sharmautkarsh505)
- Nikunj Miglani (@NM1210)

Happy coding with GitByte!
