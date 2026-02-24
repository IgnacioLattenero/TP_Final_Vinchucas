A la caza de las Vinchucas
Desarrollo del backend y lógica de negocio para un sistema de mapeo y detección de vinchucas en el territorio argentino. Proyecto integrador desarrollado para la cátedra de Programación con Objetos II.

🚀 Sobre el proyecto
El sistema procesa muestras recolectadas por usuarios a través de una aplicación móvil, gestionando la verificación colaborativa para identificar especies transmisoras del mal de Chagas. Se implementaron reglas de negocio utilizando fuertemente el Diseño Orientado a Objetos y patrones de diseño.

⚙️ Funcionalidades implementadas
Sistema de Votación y Verificación: Motor de validación de muestras mediante la opinión de la comunidad, requiriendo coincidencia de expertos para verificar una muestra.

Roles Dinámicos de Usuario: Promoción automática de usuarios Básicos a Expertos basada en su nivel de participación en los últimos 30 días.

Geolocalización: Cálculo de distancias y solapamiento de áreas geográficas para determinar la proximidad de las muestras.

Alertas a Organizaciones: Sistema de notificaciones (basado en el patrón Observer) para informar a ONGs sobre nuevas muestras o validaciones.

Búsquedas Avanzadas: Motor de filtrado combinable mediante operadores lógicos (AND/OR) para buscar muestras por fecha, tipo de insecto o nivel de validación.

🛠️ Stack Tecnológico
Lenguaje: Java.

Diseño: Programación Orientada a Objetos, Patrones de Diseño (Gamma et al.), UML.

Testing: Pruebas unitarias exhaustivas (95% de cobertura del código).
