# Gestor de Pedidos para Comercio Electrónico

¡Bienvenido a la documentación del Gestor de Pedidos para Comercio Electrónico! Esta aplicación te permite gestionar tus pedidos de manera eficiente y escalable. Aquí encontrarás todo lo que necesitas saber para comprender y utilizar este sistema.

## Requisitos Técnicos

El Gestor de Pedidos utiliza tecnologías avanzadas para asegurar su eficacia y escalabilidad:

- **Bases de Datos**:
  - **Relacional (PostgreSQL)**: Almacena información de clientes y productos.
  - **No Relacional (MongoDB)**: Almacena información de pedidos.
- **Transacciones**: Garantizamos la consistencia entre las bases de datos relacional y no relacional.
- **Patrones de Diseño**: Implementamos el patrón de diseño DDD con arquitectura hexagonal para una mejor organización y escalabilidad del código.
- **Reactividad**: Utilizamos Spring WebFlux para una implementación reactiva, lo que asegura alta concurrencia y eficiencia en el procesamiento de solicitudes.
- **Buenas Prácticas**: Seguimos las mejores prácticas de desarrollo de software para garantizar calidad y mantenibilidad del proyecto.
- **Manejo de Errores**: Implementamos un manejo adecuado de errores para mejorar la robustez y confiabilidad del sistema.
- **Validación de Entradas de Usuario**: Realizamos validaciones de entradas para prevenir vulnerabilidades y garantizar la integridad de los datos.
- **Tests Unitarios**: Incluimos pruebas exhaustivas para validar el correcto funcionamiento del código.
- **Docker**: Utilizamos Docker para facilitar la implementación y el despliegue en diferentes entornos: `docker pull martinmt/order-managment:1.0`

## Endpoints

La aplicación ofrece los siguientes endpoints para interactuar con el sistema:

- **GET /orders/list**: Lista todos los pedidos almacenados en la base de datos.
- **GET /orders/listP**: Lista los pedidos pendientes de confirmación.
- **GET /orders/listC**: Lista los pedidos confirmados.
- **GET /orders/find/{id}**: Obtiene detalles de un pedido específico.
- **POST /orders/create**: Crea un nuevo pedido en el sistema.
- **PATCH /orders/updateOrder/{id}**: Actualiza los detalles de un pedido existente.
- **PATCH /orders/confirm/{id}**: Confirma un pedido pendiente.

## Uso

Para utilizar la aplicación, simplemente realiza solicitudes HTTP a los endpoints mencionados utilizando los métodos correspondientes (GET, POST, PATCH). La aplicación responderá con los datos solicitados o con el resultado de la operación realizada.