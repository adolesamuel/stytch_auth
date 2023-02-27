import android.app.Activity
import android.content.Context
import android.content.Intent
import android.util.Log
import com.stytch.sdk.consumer.StytchClient
import com.stytch.sdk.consumer.oauth.OAuth
import io.flutter.plugin.common.EventChannel
import io.flutter.plugin.common.MethodCall
import io.flutter.plugin.common.MethodChannel
import io.flutter.plugin.common.MethodChannel.MethodCallHandler
import org.json.JSONArray
import org.json.JSONObject


internal class MethodCallHandlerImpl(context: Context, activity: Activity?, eventChannel: EventChannel, methodChannel: MethodChannel
): MethodCallHandler, EventChannel.StreamHandler{
    private var context: Context
    private var activity: Activity?
    private var events: EventChannel.EventSink? = null
    private var eventChannel: EventChannel
    private var methodChannel: MethodChannel
    private val logTag: String = "StytchAuthSDKFlutter"


    init {
        this.activity = activity
        this.context = context
        this.eventChannel = eventChannel
        this.methodChannel = methodChannel
        eventChannel.setStreamHandler(this)
    }

    override fun onListen(arguments: Any?, events: EventChannel.EventSink?) {
        this.events = events
    }

    override fun onCancel(arguments: Any?) {
    }

    fun setActivity(act: Activity?) {
        this.activity = act
    }

    override fun onMethodCall(call: MethodCall, result: MethodChannel.Result) {
        try {
            when (call.method) {
                "configure" -> configure(call)
                "startGoogleLogin" -> startGoogleLogin()
            
                // "loginWithEmail" -> loginWithEmail(call, result)
             
                else -> result.notImplemented()
            }

        } catch (e: Exception) {
            result.error(logTag, null, e.toString())
        }
    }

    private fun configure(call: MethodCall){
        val publicToken = call.argument<String>("public_token")
        StytchClient.configure(
              context =  context,
               publicToken = publicToken!!
        )
    }

//    public  fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
////        super.onActivityResult(requestCode, resultCode, data)
//        when (requestCode) {
//            3 -> data?.let { viewModel.oauthAuthenticate(resultCode, it) }
//        }
//    }

//    fun oauthAuthenticate(resultCode: Int, intent: Intent) {
//        viewModelScope.launch {
//            if (resultCode == RESULT_OK) {
//                intent.data?.let {
//                    val result = StytchClient.handle(it, 60U)
//                    // result is either a successfully authenticated session, or an error message
//                }
//            } else {
//                intent.extras?.getSerializable(OAuthError.OAUTH_EXCEPTION)?.let {
//                    when (it as OAuthError) {
//                        is OAuthError.UserCanceled -> {} // do nothing
//                        is OAuthError.NoBrowserFound,
//                        is OAuthError.NoURIFound -> // provide appropriate messaging to the user
//                    }
//                }
//            }
//        }
//    }

    private fun startGoogleLogin(call: MethodCall){
        ///Declare an oAuthRequestIdentifier to use in identifying which request was made.
        ///therefore i'm using just a random one.

       val loginRedirectUrl = call.argument<String>("login_redirect_url")
       val signupRedirectUrl = call.argument<String>("signup_redirect_url")

        val startParameters = OAuth.ThirdParty.StartParameters(
                context = activity!!,
                oAuthRequestIdentifier = 3,
                loginRedirectUrl = loginRedirectUrl,
                signupRedirectUrl =signupRedirectUrl ,
        )
        StytchClient.oauth.google.start(startParameters)
    }


}