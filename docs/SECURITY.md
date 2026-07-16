# Security Model

Relay prioritizes security, as it holds credentials to remote development servers.

## Core Tenets
1. **No Public Exposure**: Connects primarily through secure tunnels (like Tailscale).
2. **Key Storage**: All SSH keys and sensitive connection configurations must be stored securely using the Android Keystore system.
3. **Biometrics**: Support for biometric authentication before establishing a connection.
4. **No Passwords**: Authentication relies on SSH keys rather than passwords whenever possible.
