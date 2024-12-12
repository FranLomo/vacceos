import android.app.Activity
import android.app.Application
import android.content.Intent
import com.example.juego.MusicService

class AppLifecycleObserver(private val application: Application) : Application.ActivityLifecycleCallbacks {

    private var activityReferences = 0
    private var isAppInBackground = false

    override fun onActivityStarted(activity: Activity) {
        activityReferences++
        if (isAppInBackground) {
            isAppInBackground = false
            startMusicService()
        }
    }

    override fun onActivityStopped(activity: Activity) {
        activityReferences--
        if (activityReferences == 0) {
            // Si no hay actividades visibles, la app está en segundo plano
            isAppInBackground= true
            stopMusicService()
        }
    }

    private fun stopMusicService() {
        val intent = Intent(application, MusicService::class.java)
        application.stopService(intent)
    }

    private fun startMusicService() {
        val intent = Intent(application, MusicService::class.java)
        application.startService(intent)
    }

    override fun onActivityCreated(activity: Activity, savedInstanceState: android.os.Bundle?) {}

    override fun onActivityResumed(activity: Activity) {}

    override fun onActivityPaused(activity: Activity) {}

    override fun onActivitySaveInstanceState(activity: Activity, outState: android.os.Bundle) {}

    override fun onActivityDestroyed(activity: Activity) {}
}
