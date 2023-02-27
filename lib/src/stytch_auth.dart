library stytch_auth;

import 'package:stytch_auth/src/stytch_auth_flutter_platform_interface.dart';

class StytchAuthFlutter {
  /// Private constructor for only one instance of stytch auth to exists.
  ///
  StytchAuthFlutter._();

  static final StytchAuthFlutter _instance = StytchAuthFlutter._();

  /// Get the instance of [StytchAuthFlutter].
  static StytchAuthFlutter get instance => _instance;

  Future<void> configure({
    required String publicToken,
  }) {
    return StytchAuthFlutterPlatform.instance
        .configure(publicToken: publicToken);
  }

  Future<void> loginWithGoogle() {
    return StytchAuthFlutterPlatform.instance.loginWithGoogle();
  }
}
