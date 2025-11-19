package co.hondaya

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowLeft
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowRight
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import contents_viewer.composeapp.generated.resources.*
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.painterResource


private enum class DestinationTarget(
    val resource: DrawableResource,
) {
    CV(Res.drawable.file),
    TALKS(Res.drawable.microphone),
    BLOGS(Res.drawable.rss),
    LINK(Res.drawable.link),
    ;
}

@Composable
fun App() {
    MaterialTheme {
        Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {
            Row(modifier = Modifier.fillMaxSize()) {
                Drawer()
//                Divider(modifier = Modifier.fillMaxHeight().width(1.dp))
                Box(
                    modifier = Modifier
                        .weight(1f)
                        .fillMaxHeight()
                        .background(MaterialTheme.colorScheme.surface),
                    contentAlignment = Alignment.Center,
                ) {
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        Text(
                            text = "Please select",
                            style = MaterialTheme.typography.headlineSmall,
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                        Text(
                            text = "ドロワーの各項目は CV / Blog / Talks / Links を表します。",
                            style = MaterialTheme.typography.bodyLarge,
                            color = MaterialTheme.colorScheme.onSurfaceVariant,
                        )
                    }
                }
            }
        }
    }
}

@Composable
private fun Drawer() {
    var expanded by remember { mutableStateOf(true) }
    var currentDestination by remember { mutableStateOf(DestinationTarget.CV) }
    val destinations = remember { DestinationTarget.entries }

    NavigationRail(
        modifier = Modifier
            .fillMaxHeight()
            .widthIn(min = 72.dp)
            .width(if (expanded) 220.dp else 88.dp)
            .size(if (expanded) 48.dp else 48.dp)
            .animateContentSize(),
    ) {
        destinations.forEach { destination ->
            NavigationRailItem(
                selected = currentDestination == destination,
                onClick = { currentDestination = destination },
                icon = {
                    Icon(
                        painter = painterResource(destination.resource),
                        contentDescription = destination.name
                    )
                },
                label = { Text(text = destination.name) },
                alwaysShowLabel = false,
            )
        }
        IconButton(onClick = { expanded = !expanded }) {
            Icon(
                imageVector = if (expanded) Icons.AutoMirrored.Filled.KeyboardArrowLeft else Icons.AutoMirrored.Filled.KeyboardArrowRight,
                contentDescription = if (expanded) "Collapse drawer" else "Expand drawer",
            )
        }
    }
}
