import 'package:plugin_platform_interface/plugin_platform_interface.dart';
import 'package:stytch_auth/src/stytch_auth_flutter_method_channel.dart';

abstract class StytchAuthFlutterPlatform extends PlatformInterface {
  ///Constructs a StytchAuthFlutterPlatform
  StytchAuthFlutterPlatform() : super(token: _token);

  static final Object _token = Object();

  static StytchAuthFlutterPlatform _instance = MethodChannelStytchAuthFlutter();

  /// The default instance of [StytchAuthFlutterPlatform] to use.
  ///
  /// Defaults to [MethodChannelStytchAuthFlutter].
  static StytchAuthFlutterPlatform get instance => _instance;

  /// Platform-specific implementations should set this with their own
  /// platform-specific class that extends [StytchAuthFlutter] when
  /// they register themselves.
  static set instance(StytchAuthFlutterPlatform instance) {
    PlatformInterface.verifyToken(instance, _token);
    _instance = instance;
  }

  ///TODO: declare the methods needed to implement a valid StytchAuth

  Future<void> configure({
    required String publicToken,
  }) {
    throw UnimplementedError('configure has not been implemented.');
  }

  Future<void> loginWithGoogle({
    required String loginRedirectUrl,
    required String signUpRedirectUrl,
  }) {
    throw UnimplementedError('google login has not been implemented');
  }
}
