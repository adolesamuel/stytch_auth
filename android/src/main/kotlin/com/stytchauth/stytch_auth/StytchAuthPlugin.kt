package com.stytchauth.stytch_auth

import MethodCallHandlerImpl
import androidx.annotation.NonNull

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.util.Log
import io.flutter.plugin.common.EventChannel
import org.json.JSONArray
import org.json.JSONObject


import io.flutter.embedding.engine.plugins.FlutterPlugin
import io.flutter.embedding.engine.plugins.activity.ActivityAware
import io.flutter.embedding.engine.plugins.activity.ActivityPluginBinding
import io.flutter.plugin.common.MethodCall
import io.flutter.plugin.common.MethodChannel
import io.flutter.plugin.common.MethodChannel.MethodCallHandler
import io.flutter.plugin.common.MethodChannel.Result

/** StytchAuthPlugin */
class StytchAuthPlugin: FlutterPlugin, ActivityAware {
  /// The MethodChannel that will the communication between Flutter and native Android
  ///
  /// This local reference serves to register the plugin with the Flutter Engine and unregister it
  /// when the Flutter Engine is detached from the Activity
  private lateinit var channel : MethodChannel
  private lateinit var eventChannel: EventChannel
  private var handler: MethodCallHandlerImpl? = null
  private val channelId = "stytch_auth"
  private val eventChannelId = "stytch_auth/event"


  override fun onAttachedToEngine(@NonNull flutterPluginBinding: FlutterPlugin.FlutterPluginBinding) {
    channel = MethodChannel(flutterPluginBinding.binaryMessenger, channelId)
    eventChannel = EventChannel(flutterPluginBinding.binaryMessenger, eventChannelId)
    handler = MethodCallHandlerImpl(flutterPluginBinding.applicationContext, null, eventChannel, channel)

    channel.setMethodCallHandler(handler)
  }


  // override fun onMethodCall(@NonNull call: MethodCall, @NonNull result: Result) {
  //   if (call.method == "getPlatformVersion") {
  //     result.success("Android ${android.os.Build.VERSION.RELEASE}")
  //   } else {
  //     result.notImplemented()
  //   }
  // }

   override fun onDetachedFromEngine(@NonNull binding: FlutterPlugin.FlutterPluginBinding) {
     channel.setMethodCallHandler(null)
   }


   override fun onAttachedToActivity(binding: ActivityPluginBinding) {
     handler?.setActivity(binding.activity)
   }

//   public override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
//     super.onActivityResult(requestCode, resultCode, data)
//     when (requestCode) {
//       [YOUR OAUTH_REQUEST_CODE] -> data?.let { viewModel.oauthAuthenticate(resultCode, it) }
//     }
//   }

   override fun onDetachedFromActivityForConfigChanges() {
     handler?.setActivity(null)
   }

   override fun onReattachedToActivityForConfigChanges(binding: ActivityPluginBinding) {
     onAttachedToActivity(binding)
   }

   override fun onDetachedFromActivity() {
     ///
   }
}
