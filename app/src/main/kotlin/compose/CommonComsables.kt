package compose

import androidx.compose.material.Button
import androidx.compose.material.Text
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
