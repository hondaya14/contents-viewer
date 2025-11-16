package co.hondaya

import androidx.compose.foundation.layout.Row
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import contents_viewer.composeapp.generated.resources.Res
import contents_viewer.composeapp.generated.resources.microphone
import contents_viewer.composeapp.generated.resources.rss
import org.jetbrains.compose.resources.painterResource

@Composable
fun App() {
    MaterialTheme {
        Row {

            ModalDrawerSheet(
                drawerShape = MaterialTheme.shapes.medium,
            ) {
                NavigationDrawerItem(
                    selected = true,
                    onClick = {},
                    shape = MaterialTheme.shapes.medium,
                    icon = { Icon(painter = painterResource(Res.drawable.rss), contentDescription = "blog") },
                    label = { Text("a") },
                )
                NavigationRailItem(
                    selected = true,
                    onClick = {},
                    icon = { Icon(painter = painterResource(Res.drawable.rss), contentDescription = "blog") },
                    label = { Text("Blog") },
                )
                NavigationRailItem(
                    selected = true,
                    onClick = {},
                    icon = { Icon(painter = painterResource(Res.drawable.microphone), contentDescription = "talks") },
                    label = { Text("Talks") },
                )
            }

//            var showContent by remember { mutableStateOf(false) }
//            Column(
//                modifier = Modifier
//                    .background(MaterialTheme.colorScheme.primaryContainer)
//                    .safeContentPadding()
//                    .fillMaxSize(),
//                horizontalAlignment = Alignment.CenterHorizontally,
//            ) {
//                Button(onClick = { showContent = !showContent }) {
//                    Text("Click me!")
//                }
//                AnimatedVisibility(showContent) {
//                    val greeting = remember { Greeting().greet() }
//                    Column(
//                        modifier = Modifier.fillMaxWidth(),
//                        horizontalAlignment = Alignment.CenterHorizontally,
//                    ) {
//                        Image(painterResource(Res.drawable.compose_multiplatform), null)
//                        Text("Compose: $greeting")
//                    }
//                }
//            }
        }
    }
}