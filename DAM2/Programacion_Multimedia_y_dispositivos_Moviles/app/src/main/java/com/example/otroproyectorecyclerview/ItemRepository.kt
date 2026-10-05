package com.example.otroproyectorecyclerview

class ItemRepository {

    fun getItems(): List<ItemData> {
        return listOf(
            ItemData(
                id = 1,
                title = "Montañas al Atardecer",
                description = "Paisaje alpino con vista a los picos nevados.",
                imageUrl = "https://picsum.photos/id/10/600/400"
            ),
            ItemData(
                id = 2,
                title = "Bosque Humedo",
                description = "Camino frondoso rodeado de vegetación y niebla.",
                imageUrl = "https://picsum.photos/id/28/600/400"
            ),
            ItemData(
                id = 3,
                title = "Arquitectura Moderna",
                description = "Estructura urbana de cristal y acero.",
                imageUrl = "https://picsum.photos/id/49/600/400"
            ),
            ItemData(
                id = 4,
                title = "Costa Marina",
                description = "Vista al océano con olas rompiendo en las rocas.",
                imageUrl = "https://picsum.photos/id/54/600/400"
            ),
            ItemData(
                id = 5,
                title = "Espacio de Trabajo",
                description = "Escritorio con café, portátil y libreta de notas.",
                imageUrl = "https://picsum.photos/id/1060/600/400"
            ),
            ItemData(
                id = 6,
                title = "Coche Clásico",
                description = "Vehículo antiguo restaurado en una calle histórica.",
                imageUrl = "https://picsum.photos/id/111/600/400"
            ),
            ItemData(
                id = 7,
                title = "Tecnología Móvil",
                description = "Dispositivo moderno con interfaz minimalista.",
                imageUrl = "https://picsum.photos/id/160/600/400"
            ),
            ItemData(
                id = 8,
                title = "Programación Nocturna",
                description = "Portátil con líneas de código en un ambiente tenue.",
                imageUrl = "https://picsum.photos/id/180/600/400"
            ),
            ItemData(
                id = 9,
                title = "Fauna Salvaje",
                description = "Búfalo americano en medio de la naturaleza abierta.",
                imageUrl = "https://picsum.photos/id/200/600/400"
            ),
            ItemData(
                id = 10,
                title = "Mascota Leal",
                description = "Retrato de un perro labrador negro.",
                imageUrl = "https://picsum.photos/id/237/600/400"
            ),
            ItemData(
                id = 11,
                title = "Fotografía Analógica",
                description = "Cámara réflex clásica sobre superficie de madera.",
                imageUrl = "https://picsum.photos/id/250/600/400"
            ),
            ItemData(
                id = 12,
                title = "Jardín Botánico",
                description = "Flores de colores contrastados en plena primavera.",
                imageUrl = "https://picsum.photos/id/319/600/400"
            ),
            ItemData(
                id = 13,
                title = "Cultura del Café",
                description = "Molienda artesanal e infusiones de especialidad.",
                imageUrl = "https://picsum.photos/id/425/600/400"
            ),
            ItemData(
                id = 14,
                title = "Rascacielos Urbano",
                description = "Perspectiva contrapicada de grandes torres metálicas.",
                imageUrl = "https://picsum.photos/id/532/600/400"
            ),
            ItemData(
                id = 15,
                title = "Textura Acuática",
                description = "Patrones de luz y sombra sobre el agua en movimiento.",
                imageUrl = "https://picsum.photos/id/659/600/400"
            )
        )
    }
}