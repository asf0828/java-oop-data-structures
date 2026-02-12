# OperacionesLIstas - List Operations

A list operations application in Java that demonstrates MVC architecture and custom data structure usage. Implements three exercises using a singly linked list built from scratch.

## Exercises

1. **Contar letra 'a'** *(pending implementation)* - Reads a phrase, stores it character by character in a list, and counts how many times the letter 'a' appears.
2. **Comparar dos listas** - Reads two lists from user input and determines if they are identical (same size and same content in each position).
3. **Eliminar repetidos** - Reads a list from user input and generates a new list with duplicate elements removed.

## Structure

```
OperacionesLIstas/
├── src/
│   └── operacioneslistas/
│       ├── controlador/
│       │   ├── ControladorContarLetras.java     # Exercise 1 controller
│       │   ├── ControladorCompararListas.java   # Exercise 2 controller
│       │   ├── ControladorEliminarRepetidos.java # Exercise 3 controller
│       │   └── ControladorMenu.java             # Main menu loop
│       ├── modelo/
│       │   ├── Lista.java                       # Custom singly linked list
│       │   └── Nodo.java                        # List node
│       ├── runner/
│       │   └── Runner.java                      # Entry point
│       └── vista/
│           └── Vista.java                       # User input and output
└── README.md
```

## How to Run

### Using command line:

```bash
# Navigate to the project directory
cd /path/to/OperacionesLIstas

# Compile the project
javac -d bin $(find src -name "*.java")

# Run the project
java -cp bin operacioneslistas.runner.Runner
```
