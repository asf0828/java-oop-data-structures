# PeajeInteligente - Sistema de Peaje Inteligente

Sistema de gestion de peaje en Java con arquitectura MVC y estructuras de datos personalizadas. Administra cuatro casetas de cobro mediante colas FIFO, asignando cada vehiculo a la caseta con menos carga. Permite revertir la ultima atencion con una pila, consultar el historial por caseta con listas, y generar reportes diarios y semanales mediante registros diarios por caseta.

## Exercise

**Peaje Inteligente** - Registra vehiculos (placa, categoria, hora) de forma manual o automatica y los distribuye a la caseta con menos vehiculos en espera. La atencion desencola todos los vehiculos de la caseta seleccionada en orden FIFO, guardando cada uno en una pila de deshacer y en el historial de su caseta. Revertir extrae el ultimo vehiculo de la pila y lo elimina del historial. Al cerrar el dia se genera un arqueo de caja por caseta (DailyRecord) y el sistema queda vacio para el siguiente dia. Cada siete dias el supervisor consulta el historico semanal por caseta en orden LIFO (ultimo vehiculo primero), con total por dia y total general de la semana.

## Class Diagram

```mermaid
classDiagram
    class Runner {
        +main(String[] args)
    }
    class Controller {
        -IOManager io
        -Queue~Vehicle~ booth1
        -Queue~Vehicle~ booth2
        -Queue~Vehicle~ booth3
        -Queue~Vehicle~ booth4
        -Stack~Vehicle~ undoStack
        -Stack~Integer~ undoBooth
        -List~Vehicle~ histCaseta1
        -List~Vehicle~ histCaseta2
        -List~Vehicle~ histCaseta3
        -List~Vehicle~ histCaseta4
        -List~DailyRecord~ week
        -int currentDay
        -Random random
        -DateTimeFormatter FORMATO_HORA
        +Controller(IOManager io)
        +ejecutar()
        -menuReportes()
        -reporteRecaudoDia()
        -cerrarDia()
        -reporteSemanal()
        -buildRecord(int boothNum, int day, List hist) DailyRecord
        -registrar()
        -registrarAleatorio()
        -mostrarEstado()
        -atender()
        -revertir()
        -mostrarHistorial()
        -mostrarListaCaseta(int num, List hist)
        -findShortestBooth() Queue
        -boothByNumber(int number) Queue
        -histByNumber(int number) List
        -generarVehiculo(String timestamp) Vehicle
        -calcularPeaje(int category) double
    }
    class IOManager {
        -BufferedReader reader
        +showMenu() int
        +showReportMenu(int currentDay) int
        +getString(String prompt) String
        +getInt(String prompt) int
        +showMessage(String message)
        +showState(int s1, int s2, int s3, int s4, int undoSize, int histSize)
        +showDayReport(int day, ...)
        +showWeeklyReportHeader(int diasCerrados)
        +showBoothWeeklyHeader(int boothNum, int dayNumber, int count)
        +showBoothWeeklyTotal(int boothNum, double total)
        +showWeeklyGrandTotal(double total)
    }
    class DailyRecord {
        -int dayNumber
        -int boothNumber
        -double total
        -Stack~Vehicle~ vehicles
        +DailyRecord(int boothNumber, int dayNumber)
        +addVehicle(Vehicle v)
        +getDayNumber() int
        +getBoothNumber() int
        +getTotal() double
        +getVehicles() Stack
        +getVehicleCount() int
    }
    class Vehicle {
        -String plate
        -int category
        -double toll
        -String timestamp
        +Vehicle(String plate, int category, double toll, String timestamp)
        +getPlate() String
        +getCategory() int
        +getToll() double
        +getTimestamp() String
        +toString() String
    }
    class Queue~T~ {
        -Node~T~ front
        -Node~T~ rear
        -int size
        +enqueue(T data)
        +dequeue() T
        +isEmpty() boolean
        +getSize() int
    }
    class Stack~T~ {
        -Node~T~ top
        -int size
        +push(T data)
        +pop() T
        +isEmpty() boolean
        +getSize() int
    }
    class List~T~ {
        -Node~T~ head
        -int size
        +add(T data)
        +get(int index) T
        +removeLast() T
        +getSize() int
    }
    class Node~T~ {
        -T data
        -Node~T~ next
        +Node(T data)
        +getData() T
        +getNext() Node
        +setNext(Node next)
    }

    Runner --> Controller
    Runner --> IOManager
    Controller --> IOManager
    Controller --> Queue
    Controller --> Stack
    Controller --> List
    Controller --> DailyRecord
    DailyRecord --> Stack
    DailyRecord --> Vehicle
    Queue --> Node
    Stack --> Node
    List --> Node
    Queue --> Vehicle
    Stack --> Vehicle
    List --> Vehicle
    List --> DailyRecord
```

## Structure

```
PeajeInteligente/
├── src/
│   └── peajeinteligente/
│       ├── runner/
│       │   └── Runner.java           # Punto de entrada
│       ├── controller/
│       │   └── Controller.java       # Logica de negocio, menu y reportes
│       ├── view/
│       │   └── IOManager.java        # Entrada/salida con BufferedReader
│       └── model/
│           ├── Vehicle.java          # Dominio: placa, categoria, peaje, timestamp
│           ├── DailyRecord.java      # Registro diario por caseta con pila LIFO
│           ├── Node.java             # Nodo generico enlazado
│           ├── Queue.java            # Cola FIFO enlazada con contador de tamano
│           ├── Stack.java            # Pila LIFO enlazada con contador de tamano
│           └── List.java             # Lista enlazada simple con acceso por indice
├── bin/
└── README.md
```

## How to Run

```bash
cd PeajeInteligente

~/.sdkman/candidates/java/current/bin/javac -d bin $(find src -name "*.java")

~/.sdkman/candidates/java/current/bin/java -cp bin peajeinteligente.runner.Runner
```
