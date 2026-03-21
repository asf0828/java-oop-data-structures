# PeajeInteligente - Sistema de Peaje Inteligente

Sistema de gestion de peaje en Java que aplica arquitectura MVC y estructuras de datos personalizadas. Administra cuatro casetas de cobro mediante colas enlazadas, asignando cada vehiculo a la caseta con menos carga. Permite revertir la ultima atencion con una pila y consultar el historial cronologico con una lista.

## Exercise

**Peaje Inteligente** - Registra vehiculos (placa y categoria) de forma manual o automatica y los distribuye a la caseta con menos vehiculos en espera. La atencion desencola todos los vehiculos de la caseta seleccionada en orden FIFO, guarda cada uno en una pila de deshacer y en el historial. La opcion de revertir extrae el ultimo vehiculo atendido de la pila. El historial muestra todos los vehiculos atendidos en orden cronologico. El estado actual muestra la cantidad de vehiculos por caseta, operaciones reversibles e historial acumulado.

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
        -List~Vehicle~ history
        -Random random
        -DateTimeFormatter FORMATO_HORA
        +Controller(IOManager io)
        +ejecutar()
        -registrar()
        -registrarAleatorio()
        -mostrarEstado()
        -atender()
        -revertir()
        -mostrarHistorial()
        -findShortestBooth() Queue
        -boothByNumber(int number) Queue
        -generarVehiculo(String timestamp) Vehicle
        -calcularPeaje(int category) double
    }
    class IOManager {
        -BufferedReader reader
        +showMenu() int
        +getString(String prompt) String
        +getInt(String prompt) int
        +showMessage(String message)
        +showState(int size1, int size2, int size3, int size4, int undoSize, int historySize)
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
        +show()
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
    Queue --> Node
    Stack --> Node
    List --> Node
    Queue --> Vehicle
    Stack --> Vehicle
    List --> Vehicle
```

## Structure

```
PeajeInteligente/
├── src/
│   └── peajeinteligente/
│       ├── runner/
│       │   └── Runner.java           # Punto de entrada
│       ├── controller/
│       │   └── Controller.java       # Logica de negocio y menu principal
│       ├── view/
│       │   └── IOManager.java        # Entrada/salida con BufferedReader
│       └── model/
│           ├── Vehicle.java          # Dominio: placa, categoria, peaje, timestamp
│           ├── Node.java             # Nodo generico enlazado
│           ├── Queue.java            # Cola FIFO enlazada con contador de tamano
│           ├── Stack.java            # Pila LIFO enlazada con contador de tamano
│           └── List.java             # Lista enlazada simple con contador de tamano
├── bin/
└── README.md
```

## How to Run

```bash
# Navigate to the project directory
cd PeajeInteligente

# Compile the project
~/.sdkman/candidates/java/current/bin/javac -d bin $(find src -name "*.java")

# Run the project
~/.sdkman/candidates/java/current/bin/java -cp bin peajeinteligente.runner.Runner
```
