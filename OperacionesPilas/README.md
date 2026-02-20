# OperacionesPilas - Stack Operations

A stack operations application in Java that demonstrates MVC architecture and a custom stack data structure. Implements three exercises using a stack (LIFO) built from scratch with char-type nodes.

## Exercises

1. **Palindromo** *(pending implementation)* - Reads a phrase, stores it character by character in a stack, and determines if the phrase reads the same forwards and backwards.
2. **Comparar dos pilas** *(pending implementation)* - Reads two stacks from user input and determines if they are identical (same size and same content in each position).
3. **Verificar parentesis** - Reads an arithmetic expression and determines if its parentheses are balanced, i.e., every opening parenthesis has a corresponding closing one.

## Structure

```
OperacionesPilas/
├── src/
│   └── operacionespilas/
│       ├── controlador/
│       │   ├── ControladorPalindromo.java      # Exercise 1 controller
│       │   ├── ControladorCompararPilas.java   # Exercise 2 controller
│       │   ├── ControladorParentesis.java      # Exercise 3 controller
│       │   └── ControladorMenu.java            # Main menu loop
│       ├── modelo/
│       │   ├── Pila.java                       # Custom stack (LIFO)
│       │   └── Nodo.java                       # Stack node (char value)
│       ├── runner/
│       │   └── Runner.java                     # Entry point
│       └── vista/
│           └── Vista.java                      # User input and output
└── README.md
```

## How to Run

### Using command line:

```bash
# Navigate to the project directory
cd /path/to/OperacionesPilas

# Compile the project
javac -d bin $(find src -name "*.java")

# Run the project
java -cp bin operacionespilas.runner.Runner
```
