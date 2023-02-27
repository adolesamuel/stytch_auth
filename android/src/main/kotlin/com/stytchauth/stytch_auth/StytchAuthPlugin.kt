package com.stytchauth.stytch_auth

import androidx.annotation.NonNull

import io.flutter.embedding.engine.plugins.FlutterPlugin
import io.flutter.plugin.common.MethodCall
import io.flutter.plugin.common.MethodChannel
import io.flutter.plugin.common.MethodChannel.MethodCallHandler
import io.flutter.plugin.common.MethodChannel.Result

/** StytchAuthPlugin */
class StytchAuthPlugin: FlutterPlugin, MethodCallHandler {
  /// The MethodChannel that will the communication between Flutter and native Android
  ///
  /// This local reference serves to register the plugin with the Flutter Engine and unregister it
  /// when the Flutter Engine is detached from the Activity
  private lateinit var channel : MethodChannel
  private var handler: MethodCallHandlerImpl? = null
  private val channelId = "stytch_auth"
  private val eventChannelId = "stytch_auth/event"


  override fun onAttachedToEngine(@NonNull flutterPluginBinding: FlutterPlugin.FlutterPluginBinding) {
    channel = MethodChannel(flutterPluginBinding.binaryMessenger, channelId)
    eventChannel = EventChannel(binding.binaryMessenger, eventChannelId)
    handler = MethodCallHandlerImpl(flutterPluginBinding.applicationContext, null, eventChannel, channel)

    channel.setMethodCallHandler(this)
  }

  override fun onMethodCall(@NonNull call: MethodCall, @NonNull result: Result) {
    if (call.method == "getPlatformVersion") {
      result.success("Android ${android.os.Build.VERSION.RELEASE}")
    } else {
      result.notImplemented()
    }
  }

  override fun onDetachedFromEngine(@NonNull binding: FlutterPlugin.FlutterPluginBinding) {
    channel.setMethodCallHandler(null)
  }

  override fun onAttachedToActivity(binding: ActivityPluginBinding) {
    handler?.setActivity(binding.activity)
  }

  // public override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
  //   super.onActivityResult(requestCode, resultCode, data)
  //   when (requestCode) {
  //     [YOUR OAUTH_REQUEST_CODE] -> data?.let { viewModel.oauthAuthenticate(resultCode, it) }
  //   }
  // }

  override fun onDetachedFromActivityForConfigChanges() {
    handler?.setActivity(null)
  }

  override fun onReattachedToActivityForConfigChanges(binding: ActivityPluginBinding) {
    onAttachedToActivity(binding)
  }

  // override fun onDetachedFromActivity() {
  //   ///
  // }
}
