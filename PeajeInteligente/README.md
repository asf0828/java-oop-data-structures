# PeajeInteligente - Sistema de Peaje Inteligente

Sistema de gestion de peaje en Java que aplica arquitectura MVC y estructuras de datos personalizadas. Administra cuatro casetas de cobro mediante colas enlazadas, asignando cada vehiculo a la caseta con menos carga. Permite revertir la ultima atencion con una pila y consultar el historial cronologico con una lista.

## Exercise

**Peaje Inteligente** - Registra vehiculos (placa y categoria) y los distribuye automaticamente a la caseta con menos vehiculos en espera. La atencion desencola al primer vehiculo de la caseta seleccionada, lo guarda en una pila de deshacer y en el historial. La opcion de revertir extrae el ultimo vehiculo de la pila. El historial muestra todos los vehiculos atendidos en orden cronologico.

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
        +Controller(IOManager io)
        +ejecutar()
        -registrar()
        -atender()
        -revertir()
        -mostrarHistorial()
        -findShortestBooth() Queue
        -boothByNumber(int number) Queue
        -calcularPeaje(int category) double
    }
    class IOManager {
        -BufferedReader reader
        +showMenu() int
        +getString(String prompt) String
        +getInt(String prompt) int
        +showMessage(String message)
    }
    class Vehicle {
        -String plate
        -int category
        -double toll
        +Vehicle(String plate, int category, double toll)
        +getPlate() String
        +getCategory() int
        +getToll() double
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
        +push(T data)
        +pop() T
        +isEmpty() boolean
    }
    class List~T~ {
        -Node~T~ head
        +add(T data)
        +show()
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
│           ├── Vehicle.java          # Dominio: placa, categoria, peaje
│           ├── Node.java             # Nodo generico enlazado
│           ├── Queue.java            # Cola FIFO enlazada con contador de tamano
│           ├── Stack.java            # Pila LIFO enlazada
│           └── List.java             # Lista enlazada simple
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
