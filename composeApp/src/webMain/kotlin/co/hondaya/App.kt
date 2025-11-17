package co.hondaya

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Article
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material.icons.filled.Link
import androidx.compose.material.icons.filled.Mic
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationRail
import androidx.compose.material3.NavigationRailItem
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp

private data class DrawerDestination(
    val id: String,
    val label: String,
    val icon: ImageVector,
)

@Composable
fun App() {
    MaterialTheme {
        Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {
            Row(modifier = Modifier.fillMaxSize()) {
                Drawer()
                Divider(modifier = Modifier.fillMaxHeight().width(1.dp))
                Box(
                    modifier = Modifier
                        .weight(1f)
                        .fillMaxHeight()
                        .background(MaterialTheme.colorScheme.surface)
                        .padding(48.dp),
                    contentAlignment = Alignment.Center,
                ) {
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        Text(
                            text = "コンテンツを選択してください",
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
    val destinations = remember {
        listOf(
            DrawerDestination(id = "cv", label = "CV", icon = Icons.Filled.AccountCircle),
            DrawerDestination(id = "blog", label = "Blog", icon = Icons.Filled.Article),
            DrawerDestination(id = "talks", label = "Talks", icon = Icons.Filled.Mic),
            DrawerDestination(id = "links", label = "Links", icon = Icons.Filled.Link),
        )
    }

    NavigationRail(
        modifier = Modifier
            .fillMaxHeight()
            .widthIn(min = 72.dp)
            .width(if (expanded) 220.dp else 88.dp)
            .animateContentSize()
            .padding(vertical = 24.dp),
        header = {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(8.dp),
                modifier = Modifier.padding(bottom = 24.dp),
            ) {
                IconButton(onClick = { expanded = !expanded }) {
                    Icon(
                        imageVector = if (expanded) Icons.Filled.KeyboardArrowLeft else Icons.Filled.KeyboardArrowRight,
                        contentDescription = if (expanded) "Collapse drawer" else "Expand drawer",
                    )
                }
                if (expanded) {
                    Text(
                        text = "Contents",
                        style = MaterialTheme.typography.titleMedium,
                    )
                }
            }
        },
    ) {
        destinations.forEach { destination ->
            NavigationRailItem(
                selected = destination.id == "cv",
                onClick = {},
                icon = { Icon(destination.icon, contentDescription = destination.label) },
                label = if (expanded) {
                    {
                        Text(
                            text = destination.label,
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis,
                        )
                    }
                } else {
                    null
                },
                alwaysShowLabel = false,
            )
        }
    }
}
