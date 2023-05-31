package compose

import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController

@Composable
fun BackNavigation(naviController: NavHostController) {
    Button(onClick = {
        naviController.popBackStack()
    }) {
        Text(text = "Back")
    }
}
