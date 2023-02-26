import 'package:flutter/foundation.dart';
import 'package:flutter/services.dart';
import 'package:stytch_auth/src/stytch_auth_flutter_platform_interface.dart';

class MethodChannelStytchAuthFlutter extends StytchAuthFlutterPlatform {
  /// The method channel used to interact with the native platform.
  @visibleForTesting
  final methodChannel = const MethodChannel('stytch_auth');
}
