package com.example.parte3

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.parte3.ui.theme.Parte3Theme

data class VistaApp(
    val titulo: String,
    val descripcion: String,
    val icono: ImageVector,
    val color: String
)

val vistasApp = listOf(
    VistaApp("Inicio", "Productos destacados, ofertas y categorías", Icons.Filled.Home, "Primary"),
    VistaApp("Catálogo", "Lista completa de productos con filtros", Icons.Filled.List, "Secondary"),
    VistaApp("Detalle Producto", "Información completa, fotos y reseñas", Icons.Filled.Info, "Tertiary"),
    VistaApp("Carrito", "Productos seleccionados y checkout", Icons.Filled.ShoppingCart, "Primary")
)

data class TemaComponente(
    val nombre: String,
    val descripcion: String,
    val ejemplo: String
)

val esquemaColores = listOf(
    TemaComponente("Primary", "Azul corporativo #1976D2", "Botones principales, navegación"),
    TemaComponente("Secondary", "Naranja acento #FF9800", "Ofertas, descuentos, badges"),
    TemaComponente("Surface", "Blanco/Gris claro", "Tarjetas de productos"),
    TemaComponente("Background", "Gris muy claro #FAFAFA", "Fondo general de la app")
)

val tipografia = listOf(
    TemaComponente("Display Large", "Roboto Bold 32sp", "Título principal de la tienda"),
    TemaComponente("Headline Medium", "Roboto Medium 24sp", "Nombres de categorías"),
    TemaComponente("Title Medium", "Roboto Medium 18sp", "Nombres de productos"),
    TemaComponente("Body Large", "Roboto Regular 16sp", "Descripciones de productos")
)

val formas = listOf(
    TemaComponente("Small", "8dp bordes redondeados", "Botones pequeños, chips"),
    TemaComponente("Medium", "12dp bordes redondeados", "Tarjetas de productos"),
    TemaComponente("Large", "16dp bordes redondeados", "Cards principales"),
    TemaComponente("Extra Large", "24dp bordes redondeados", "Modales y dialogs")
)

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MaterialTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    TiendaApp()
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TiendaApp() {
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(
                        "MiTienda App - Tema Design",
                        style = MaterialTheme.typography.headlineSmall,
                        fontWeight = FontWeight.Bold
                    )
                },
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primary,
                    titleContentColor = MaterialTheme.colorScheme.onPrimary
                )
            )
        }
    ) { paddingValues ->
        LazyColumn(
            contentPadding = paddingValues,
            verticalArrangement = Arrangement.spacedBy(16.dp),
            modifier = Modifier.padding(16.dp)
        ) {
            item {
                SeccionTema("Esquema de Colores", esquemaColores)
            }

            item {
                SeccionTema("Tipografía", tipografia)
            }

            item {
                SeccionTema("Formas", formas)
            }

            item {
                Text(
                    "Vistas Principales de la Tienda",
                    style = MaterialTheme.typography.headlineSmall,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(vertical = 8.dp)
                )
            }

            items(vistasApp) { vista ->
                TarjetaVista(vista = vista)
            }

            item {
                TarjetaAdicionales()
            }
        }
    }
}

@Composable
fun SeccionTema(
    titulo: String,
    elementos: List<TemaComponente>,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier.fillMaxWidth(),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surfaceVariant
        )
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Text(
                text = titulo,
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
            Spacer(modifier = Modifier.height(8.dp))

            elementos.forEach { elemento ->
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 4.dp)
                ) {
                    Text(
                        text = elemento.nombre,
                        style = MaterialTheme.typography.bodyMedium,
                        fontWeight = FontWeight.Medium,
                        modifier = Modifier.weight(1f)
                    )
                    Text(
                        text = elemento.ejemplo,
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
            }
        }
    }
}

@Composable
fun TarjetaVista(
    vista: VistaApp,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier.fillMaxWidth(),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                imageVector = vista.icono,
                contentDescription = null,
                modifier = Modifier.size(40.dp),
                tint = MaterialTheme.colorScheme.primary
            )

            Spacer(modifier = Modifier.width(16.dp))

            Column(
                modifier = Modifier.weight(1f)
            ) {
                Text(
                    text = vista.titulo,
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = vista.descripcion,
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }

            Card(
                colors = CardDefaults.cardColors(
                    containerColor = when(vista.color) {
                        "Primary" -> MaterialTheme.colorScheme.primary
                        "Secondary" -> MaterialTheme.colorScheme.secondary
                        else -> MaterialTheme.colorScheme.tertiary
                    }
                )
            ) {
                Text(
                    text = vista.color,
                    modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp),
                    style = MaterialTheme.typography.labelSmall,
                    color = MaterialTheme.colorScheme.onPrimary
                )
            }
        }
    }
}

@Composable
fun TarjetaAdicionales(modifier: Modifier = Modifier) {
    Card(
        modifier = modifier.fillMaxWidth(),
        elevation = CardDefaults.cardElevation(defaultElevation = 6.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.primaryContainer
        )
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Text(
                text = "Elementos Adicionales",
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold
            )
            Spacer(modifier = Modifier.height(8.dp))


        }
    }
}

@Preview(showBackground = true)
@Composable
fun TiendaAppPreview() {
    MaterialTheme {
        TiendaApp()
    }
}

@Preview(showBackground = true)
@Composable
fun TiendaAppDarkPreview() {
    MaterialTheme {
        TiendaApp()
    }
}