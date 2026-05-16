# Documentación: Fiber Connection
## Programación III — UNGS (TP2)

## 1. Contexto del Proyecto
Aplicación en **Java (Swing + Eclipse)** para planificar el tendido de redes de fibra óptica interprovinciales en zonas despobladas de forma eficiente, minimizando los costos de infraestructura mediante la teoría de grafos.

---

## 2. Reglas de Negocio (Cálculo de Costos)
El costo total de cada conexión (arista) se calcula mediante una función polinómica basada en tres variables que ingresa el usuario:

* **Distancia Geográfica:** Se calcula en kilómetros utilizando la **Fórmula de Haversine** sobre la latitud y longitud de las localidades:
* **Costo Base:** Precio fijo por kilómetro de fibra.
* **Penalización por Larga Distancia:** Si la conexión supera los **300 km**, se aplica un recargo porcentual sobre el costo base.
* **Costo Interprovincial:** Si las dos localidades pertenecen a provincias distintas, se suma un costo fijo extra.

---

## 3. Arquitectura del Software
El sistema está estrictamente desacoplado en tres capas independientes:

### Capa de Negocio (Model)
* `Localidad`: Representa los nodos (Nombre, Provincia, Latitud, Longitud).
* `Conexion`: Representa las aristas, calcula los costos polinómicos e implementa `Comparable` para ordenarlas.
* `PlanificadorRed`: Contiene la lógica del grafo completo y el motor de optimización.

### Capa de Interfaz (GUI)
* Ventana principal en **Java Swing** administrada para la carga de datos.
* `JTable` dinámica para listar y eliminar localidades.
* Panel visual (`paintComponent`) que proyecta las coordenadas para dibujar el mapa de la red resultante.

### Capa de Persistencia
* Almacenamiento de datos mediante archivos de texto plano (`localidades.txt`).
* Lectura y escritura mediante `BufferedReader` y `BufferedWriter` sin usar bases de datos pesadas.

---

## 4. Resolución del Árbol Generador Mínimo (AGM)
Como el problema plantea un **grafo completo** (todas las localidades se pueden conectar entre sí), se implementa el **Algoritmo de Kruskal**:

1. Se generan todas las conexiones posibles entre las localidades cargadas.
2. Se ordenan todas las aristas de menor a mayor costo.
3. Utilizando la estructura **Union-Find** (con compresión de caminos), se seleccionan las aristas más baratas evitando estrictamente la formación de ciclos.
4. Al finalizar el algoritmo, devuelve la red óptima y el costo total de la instalación.

---

## 5. Estrategia de Testing (JUnit)
Pruebas automatizadas en la capa de negocio aisladas de la interfaz gráfica:
* **Test de Haversine:** Verificación de precisión del cálculo de distancias.
* **Test de Costos:** Casos testigo para chequear el recargo de $>300\text{ km}$ y el extra interprovincial.
* **Test del AGM:** Armado de escenarios pequeños con soluciones conocidas para asegurar que Kruskal devuelva el costo mínimo correcto y no genere ciclos.
