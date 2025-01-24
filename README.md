# 🍗 Gestión de Reservas en el Asador "Los Pollos Hermanos" 🍟  

El asador **"Los Pollos Hermanos"** tiene una capacidad diaria de **100 pollos** 🐔 y **100 porciones de patatas fritas** 🍟.  
La demanda de reservas es alta, y las solicitudes llegan de forma **concurrente**.  
Es necesario implementar una solución **cliente-servidor** para gestionar estas reservas de manera eficiente, utilizando **hilos y sincronización**.  

---

## 📌 Requisitos del Proyecto  

### 📂 1. Ficheros principales (como mínimo):  
📄 `Servidor.java`  
📄 `Cliente.java`  
📄 `SimuladorClientes.java`  

---

### 🖥️ 2. `Servidor.java`  
🔹 Gestionará las **reservas** de **pollos** y **patatas**.  
🔹 Cada cliente tendrá un **hilo servidor** que procesará su reserva.  
🔹 El servidor preguntará al cliente:  
   1️⃣ **¿Cuántos pollos desea reservar?** (entre **1 y 3**).  
   2️⃣ **¿Cuántas porciones de patatas desea reservar?** (entre **0 y 4**).  
🔹 Verificará de manera **sincronizada** la disponibilidad de pollos y patatas:  
   - ❌ **Si no hay suficientes pollos**, el pedido no se procesa y se informa al cliente.  
   - ⚠️ **Si hay suficientes pollos pero no todas las patatas solicitadas**, el cliente acepta las patatas disponibles.  
🔹 Mostrará por **consola**:  
   - 👤 **Qué cliente realizó la reserva.**  
   - 🍗 **Cantidad de pollos y patatas solicitados y la cantidad finalmente entregada.**  
   - 📉 **Cantidad de pollos y patatas restantes tras la reserva.**  
🔹 **Si no hay disponibilidad de pollos**, se informará que **no fue posible procesar la reserva** ❌.  

---

### 🏷️ 3. `Cliente.java`  
🔹 Cada cliente generará de forma **aleatoria**:  
   - 🐔 **Pollos solicitados:** número aleatorio entre **1 y 3**.  
   - 🍟 **Porciones de patatas solicitadas:** número aleatorio entre **0 y 4**.  
🔹 Mostrará por pantalla su **nombre** y los **valores generados** (pollos y patatas).  
🔹 Esperará **despedida del servidor** 👋.  

---

### 🎛️ 4. `SimuladorClientes.java`  
🔹 Proveerá un **menú** con dos opciones:  
   0️⃣ **Simular peticiones de clientes:** Se pedirá al usuario cuántos clientes lanzar de forma **concurrente**.  
   1️⃣ **Finalizar programa:** Termina la simulación ❌.  
🔹 Una vez **todos los hilos clientes** hayan completado sus reservas, el **menú volverá a mostrarse** 🔄.  
