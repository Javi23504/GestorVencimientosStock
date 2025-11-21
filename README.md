Sistema de gestión de inventario con control de fechas de vencimiento desarrollado en Java SE y MySQL.

## Equipo
- Javiera Valle
- Pavel Olmedo
- Nitsi Sepúlveda

## Descripción
Aplicación de escritorio que permite:
- Gestionar productos, categorías y lotes
- Controlar fechas de vencimiento y stock
- Generar alertas de productos próximos a vencer
- Automatizar el control de inventario

## Tecnologías
- Java SE 8+
- MySQL 8.0
- JDBC
- Swing (interfaz gráfica)
- NetBeans IDE

## Instalación

### Requisitos previos
- Java JDK 8 o superior
- MySQL Server 8.0 o superior
- MySQL Connector/J (incluido en el proyecto)

### Pasos de instalación

1. **Clonar el repositorio**
   git clone https://github.com/Jsvi230504/GestorVencimientosStock.git
   cd GestorVencimientosStock

2. **Crear la base de datos**
   - Abrir MySQL Workbench
   - Ejecutar el script `database/gvsdb.sql`

3. **Configurar conexión**
   - Abrir `src/gvs/util/ConexionBaseDeDatos.java`
   - Modificar USER y PASSWORD con tus credenciales MySQL

4. **Ejecutar el proyecto**
   - Opción 1: Abrir en NetBeans y ejecutar (F6)
   - Opción 2: Ejecutar el .jar en tu terminal
     java -jar dist/GestorVencimientosStock.jar
