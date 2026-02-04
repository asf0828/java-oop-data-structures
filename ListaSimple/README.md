# ListaSimple - Singly Linked List

A singly linked list implementation in Java that demonstrates fundamental data structure concepts.

## Structure

```
ListaSimple/
├── src/
│   ├── Lista.java    # Linked list class
│   ├── Nodo.java     # Node class
│   └── Main.java     # Demo program for student grades
```

## Implementation Details

This implementation demonstrates:
- Node-based data structure
- Dynamic insertion at the end
- Position-based search (0-indexed)
- List traversal and display
- Average calculation

## Features

- **Insert**: Add values at the end of the list
- **Search**: Find nodes by position
- **Display**: Show all list contents with positions
- **Calculate Average**: Compute the mean of all stored values

## Example Use Case

The current demo program allows users to:
1. Input a series of student grades
2. Display all grades with their positions
3. Search for a specific grade by position
4. Calculate the final average grade

## How to Run

### Using command line:
```bash
cd src
javac *.java
java Main
```

### Using an IDE:
Open the project in IntelliJ IDEA, Eclipse, or VS Code and run `Main.java`.

## Sample Execution

```
Ingrese la cantidad de notas: 3
Ingrese la nota 1: 85
Ingrese la nota 2: 90
Ingrese la nota 3: 78

Contenido de la lista:
Posición 0: 85
Posición 1: 90
Posición 2: 78

Ingrese la posición a buscar: 1
Valor encontrado: 90

Nota final del estudiante (promedio): 84.33
```

## Classes

### Nodo (Node)
Represents a single node in the linked list containing:
- `int valor`: The stored value
- `Nodo siguiente`: Reference to the next node

### Lista (List)
Manages the linked list with methods:
- `insertar(int valor)`: Add a new value to the end
- `buscarPorPosicion(int posicion)`: Find node at given position
- `mostrarContenido()`: Display all values
- `calcularPromedio()`: Calculate average of all values
- `getTamaño()`: Get the list size
