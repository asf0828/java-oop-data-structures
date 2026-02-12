# ListaSimple - Singly Linked List

A singly linked list implementation in Java that demonstrates fundamental data structure concepts using MVC architecture.

## Structure

```
ListaSimple/
├── src/
│   └── listasimple/
│       ├── aplicacion/
│       │   └── Main.java           # Entry point
│       ├── controlador/
│       │   └── Controlador.java    # Business logic and list operations
│       ├── modelo/
│       │   ├── Lista.java          # Custom singly linked list
│       │   └── Nodo.java           # List node
│       └── vista/
│           └── Vista.java          # User input and output
└── README.md
```

## How to Run

### Using command line:

```bash
# Navigate to the project directory
cd /path/to/ListaSimple

# Compile the project
javac -d bin $(find src -name "*.java")

# Run the project
java -cp bin listasimple.aplicacion.Main
```
