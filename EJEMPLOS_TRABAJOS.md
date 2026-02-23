# Ejemplos correctos para crear Trabajos

## ✅ Validaciones actuales

### TrabajoDTO - Campos y validaciones

| Campo | Validación | Ejemplo |
|-------|-----------|---------|
| `titulo` | 3-200 caracteres, obligatorio | `"Reparación de equipo"` |
| `descripcion` | 0-5000 caracteres, opcional | `"Revisión completa del sistema"` |
| `fechaProgramada` | Obligatorio, formato ISO | `"2026-03-01"` |
| `estado` | Obligatorio (enum) | `"PENDIENTE"` |
| `prioridad` | Obligatorio (enum) | `"ALTA"` |
| `clienteId` | Obligatorio, debe existir | `1` |
| `trabajadorId` | Opcional, si existe debe ser válido | `null` o `1` |

## ❌ Ejemplos INCORRECTOS (causarán error 400)

### Error 1: Título muy corto
```json
{
  "titulo": "sa",
  "descripcion": "Descripción",
  "fechaProgramada": "2026-03-01",
  "estado": "PENDIENTE",
  "prioridad": "ALTA",
  "clienteId": 1
}
```
**Error:** `Size.titulo` - El título debe tener entre 3 y 200 caracteres

### Error 2: clienteId es null
```json
{
  "titulo": "Reparación de equipo",
  "descripcion": "Revisión completa",
  "fechaProgramada": "2026-03-01",
  "estado": "PENDIENTE",
  "prioridad": "ALTA",
  "clienteId": null
}
```
**Error:** `NotNull.clienteId` - El cliente es obligatorio

### Error 3: Estado inválido
```json
{
  "titulo": "Reparación de equipo",
  "fechaProgramada": "2026-03-01",
  "estado": "INVALIDO",
  "prioridad": "ALTA",
  "clienteId": 1
}
```
**Error:** `IllegalArgumentException` - No es un valor válido de EstadoTrabajo

## ✅ Ejemplos CORRECTOS (funcionarán)

### Ejemplo 1: Trabajo completo con todos los datos
```json
{
  "titulo": "Reparación de equipo",
  "descripcion": "Revisión completa del sistema eléctrico",
  "fechaProgramada": "2026-03-01",
  "estado": "PENDIENTE",
  "prioridad": "ALTA",
  "clienteId": 1,
  "trabajadorId": 1
}
```

### Ejemplo 2: Trabajo sin trabajador asignado
```json
{
  "titulo": "Instalación de nueva red",
  "descripcion": "Instalación de infraestructura de red",
  "fechaProgramada": "2026-03-05",
  "estado": "PENDIENTE",
  "prioridad": "MEDIA",
  "clienteId": 1
}
```

### Ejemplo 3: Trabajo sin descripción
```json
{
  "titulo": "Mantenimiento preventivo",
  "fechaProgramada": "2026-03-10",
  "estado": "EN_PROCESO",
  "prioridad": "BAJA",
  "clienteId": 2,
  "trabajadorId": 2
}
```

### Ejemplo 4: Trabajo urgente
```json
{
  "titulo": "Reparación urgente de sistema",
  "descripcion": "Fallo crítico en el servidor principal",
  "fechaProgramada": "2026-02-24",
  "estado": "PENDIENTE",
  "prioridad": "URGENTE",
  "clienteId": 1,
  "trabajadorId": 1
}
```

## 🧪 Pruebas con PowerShell

### Crear trabajo correcto
```powershell
$trabajo = @{
    titulo = "Reparación de equipo"
    descripcion = "Revisión completa del sistema"
    fechaProgramada = "2026-03-01"
    estado = "PENDIENTE"
    prioridad = "ALTA"
    clienteId = 1
    trabajadorId = 1
} | ConvertTo-Json

Invoke-RestMethod -Uri "http://localhost:8081/api/trabajos" `
  -Method Post `
  -Body $trabajo `
  -ContentType "application/json"
```

### Crear trabajo sin trabajador
```powershell
$trabajo = @{
    titulo = "Consultoría técnica"
    descripcion = "Evaluación de infraestructura"
    fechaProgramada = "2026-03-15"
    estado = "PENDIENTE"
    prioridad = "MEDIA"
    clienteId = 1
} | ConvertTo-Json

Invoke-RestMethod -Uri "http://localhost:8081/api/trabajos" `
  -Method Post `
  -Body $trabajo `
  -ContentType "application/json"
```

## 🧪 Pruebas con cURL

### Crear trabajo
```bash
curl -X POST http://localhost:8081/api/trabajos \
  -H "Content-Type: application/json" \
  -d '{
    "titulo": "Reparación de equipo",
    "descripcion": "Revisión completa del sistema",
    "fechaProgramada": "2026-03-01",
    "estado": "PENDIENTE",
    "prioridad": "ALTA",
    "clienteId": 1,
    "trabajadorId": 1
  }'
```

### Listar trabajos
```bash
curl http://localhost:8081/api/trabajos
```

### Obtener trabajo específico
```bash
curl http://localhost:8081/api/trabajos/1
```

### Actualizar trabajo
```bash
curl -X PUT http://localhost:8081/api/trabajos/1 \
  -H "Content-Type: application/json" \
  -d '{
    "titulo": "Reparación de equipo - ACTUALIZADO",
    "descripcion": "Revisión completa del sistema",
    "fechaProgramada": "2026-03-05",
    "estado": "EN_PROCESO",
    "prioridad": "MEDIA",
    "clienteId": 1,
    "trabajadorId": 1
  }'
```

### Eliminar trabajo
```bash
curl -X DELETE http://localhost:8081/api/trabajos/1
```

## 📋 Valores válidos para Enums

### Estado (EstadoTrabajo)
```
"PENDIENTE"
"EN_PROCESO"
"COMPLETADO"
"CANCELADO"
```

### Prioridad (PrioridadTrabajo)
```
"BAJA"
"MEDIA"
"ALTA"
"URGENTE"
```

## 🔍 Respuestas de éxito

### POST /api/trabajos (201 Created)
```json
{
  "id": 1,
  "titulo": "Reparación de equipo",
  "descripcion": "Revisión completa del sistema",
  "fechaProgramada": "2026-03-01",
  "estado": "PENDIENTE",
  "prioridad": "ALTA",
  "clienteId": 1,
  "trabajadorId": 1
}
```

### GET /api/trabajos (200 OK)
```json
[
  {
    "id": 1,
    "titulo": "Reparación de equipo",
    "descripcion": "Revisión completa del sistema",
    "fechaProgramada": "2026-03-01",
    "estado": "PENDIENTE",
    "prioridad": "ALTA",
    "clienteId": 1,
    "trabajadorId": 1
  },
  {
    "id": 2,
    "titulo": "Instalación de red",
    "descripcion": null,
    "fechaProgramada": "2026-03-05",
    "estado": "PENDIENTE",
    "prioridad": "MEDIA",
    "clienteId": 1,
    "trabajadorId": null
  }
]
```

## 🔴 Respuestas de error

### 400 Bad Request - Validación fallida
```json
{
  "timestamp": "2026-02-23T22:06:05.723",
  "status": 400,
  "error": "Error de validación",
  "errores": {
    "titulo": "El título debe tener entre 3 y 200 caracteres",
    "clienteId": "El cliente es obligatorio"
  }
}
```

### 404 Not Found
```json
{}
```
(Cuando intentas obtener o actualizar un trabajo que no existe)

### 400 Bad Request - Cliente no encontrado
```
Cliente no encontrado con ID: 999
```

### 400 Bad Request - Trabajador no encontrado
```
Trabajador no encontrado con ID: 999
```

## 📝 Paso a paso: Crear un trabajo

### 1. Primero, verifica que existe un cliente
```bash
curl http://localhost:8081/api/clientes
```

### 2. Anota el ID del cliente (ej: 1)

### 3. (Opcional) Verifica que existe un trabajador
```bash
curl http://localhost:8081/api/trabajadores
```

### 4. Anota el ID del trabajador (ej: 1)

### 5. Crea el trabajo con los IDs verificados
```bash
curl -X POST http://localhost:8081/api/trabajos \
  -H "Content-Type: application/json" \
  -d '{
    "titulo": "Mi primer trabajo",
    "fechaProgramada": "2026-03-01",
    "estado": "PENDIENTE",
    "prioridad": "ALTA",
    "clienteId": 1,
    "trabajadorId": 1
  }'
```

## 💡 Checklist de validación

Antes de enviar una petición POST/PUT:

- [ ] ¿El título tiene al menos 3 caracteres?
- [ ] ¿El título tiene máximo 200 caracteres?
- [ ] ¿La fecha está en formato YYYY-MM-DD?
- [ ] ¿El estado es uno de: PENDIENTE, EN_PROCESO, COMPLETADO, CANCELADO?
- [ ] ¿La prioridad es una de: BAJA, MEDIA, ALTA, URGENTE?
- [ ] ¿clienteId es un número válido (no null)?
- [ ] ¿El cliente con ese ID existe?
- [ ] ¿Si proporcionas trabajadorId, existe el trabajador?

