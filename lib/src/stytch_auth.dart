library stytch_auth;

class StytchAuthFlutter {
  /// Private constructor for only one instance of stytch auth to exists.
  ///
  StytchAuthFlutter._();

  static final StytchAuthFlutter _instance = StytchAuthFlutter._();

  /// Get the instance of [StytchAuthFlutter].
  static StytchAuthFlutter get instance => _instance;
}
