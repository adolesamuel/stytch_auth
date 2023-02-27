import 'package:flutter/foundation.dart';
import 'package:flutter/services.dart';
import 'package:stytch_auth/src/stytch_auth_flutter_platform_interface.dart';

class MethodChannelStytchAuthFlutter extends StytchAuthFlutterPlatform {
  /// The method channel used to interact with the native platform.
  @visibleForTesting
  final methodChannel = const MethodChannel('stytch_auth');

  @visibleForTesting
  final eventChannel = const EventChannel('stytch_auth/event');

  @override
  Future<void> configure({required String publicToken}) {
    return methodChannel.invokeMethod(
      'configure',
      {
        'public_token': publicToken,
      },
    );
  }

  @override
  Future<void> loginWithGoogle() {
    return methodChannel.invokeMethod('startGoogleLogin');
  }
}
