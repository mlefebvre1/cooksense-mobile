package org.chef.cooksense

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import com.google.firebase.database.core.Platform
import org.chef.cooksense.firebase.createFirestore
import org.chef.cooksense.firebase.firestoreRepository
import org.chef.cooksense.ui.CooksenseTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        enableEdgeToEdge()
        super.onCreate(savedInstanceState)

        setContent {
            CooksenseTheme {
                Surface(modifier = Modifier.fillMaxSize()) {
                    val repository =
                        firestoreRepository(firestore = createFirestore(useEmulator = Platform.isDebugBinary))
                    App(repository = repository)
                }
            }
        }
    }
}
