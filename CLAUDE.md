# CLAUDE.md — Java OOP & Data Structures

## Java Environment

Java is managed via **sdkman**. The active version is **Amazon Corretto 17.0.13**.

`java` and `javac` are not in the shell PATH by default in non-login sessions (e.g. Claude Code). Always use the full path:

```
~/.sdkman/candidates/java/current/bin/javac
~/.sdkman/candidates/java/current/bin/java
```

## Compile and Run

Each project is compiled independently from its own root folder into a `bin/` directory.

### Pattern (applies to all projects except ListaSimple)

```bash
cd <ProjectName>
mkdir -p bin
~/.sdkman/candidates/java/current/bin/javac -d bin $(find src -name "*.java")
~/.sdkman/candidates/java/current/bin/java -cp bin <package>.runner.Runner
```

### Per-project commands

| Project | Compile from | Main class |
|---------|-------------|------------|
| `ListaSimple` | `ListaSimple/` | `listasimple.aplicacion.Main` |
| `OperacionesPilas` | `OperacionesPilas/` | `operacionespilas.runner.Runner` |
| `OperacionesLIstas` | `OperacionesLIstas/` | `operacioneslistas.runner.Runner` |
| `GestionEstudiantes` | `GestionEstudiantes/` | `gestionestudiantes.runner.Runner` |
| `TurnosBanco` | `TurnosBanco/` | `turnosbanco.runner.Runner` |
| `PeajeInteligente` | `PeajeInteligente/` | `peajeinteligente.runner.Runner` |

## Repository Structure

```
java-oop-data-structures/
├── ListaSimple/               # Singly linked list basics (int nodes)
├── OperacionesPilas/          # Stack operations: palindrome, compare, parentheses
├── OperacionesLIstas/         # List operations: count chars, compare, remove duplicates
├── GestionEstudiantes/        # Queue: search student by name and replace age
├── TurnosBanco/               # Two queues with priority: bank client service order
├── ArbolesBinarios/           # Binary search tree: insert, search, traversals
├── PeajeInteligente/          # Toll booth system: 4 queues, stack undo, list history
└── CLAUDE.md
```

Each project follows this internal layout:

```
<ProjectName>/
├── src/
│   └── <packagename>/
│       ├── runner/         # Entry point (Runner.java or Main.java)
│       ├── modelo/         # Data structures and domain classes
│       ├── vista/          # User I/O (Scanner + System.out)
│       └── controlador/    # Business logic and flow orchestration
├── bin/                    # Compiled .class files (git-ignored)
└── README.md               # Exercise description + Mermaid class diagram
```

> **Exception:** `ListaSimple` uses `aplicacion/` instead of `runner/`, and has a single `Controlador.java` instead of a menu controller.

## Architecture — MVC Conventions

| Layer | Class name pattern | Responsibility |
|-------|--------------------|----------------|
| **Runner** | `Runner` / `Main` | Instantiates Vista and Controller(s), calls `ejecutar()` |
| **Modelo** | `Nodo`, data structure class, domain class | Linked node, structure (Pila/Cola/Lista), business entity |
| **Vista** | `Vista` | All `System.out` output and `Scanner` input. Never contains business logic. |
| **Controlador** | `ControladorXxx` | Reads from Vista, operates on Modelo, sends results back to Vista |
| **ControladorMenu** | `ControladorMenu` | Present in multi-exercise projects. Dispatches to individual controllers via `switch`. |

## Code Conventions

- **Language:** Java, no external libraries. All data structures built from scratch with `Nodo`.
- **Naming:** Spanish throughout — class names, method names, variable names, and Javadoc.
- **Node type:** Each project defines its own `Nodo` class typed to the value it stores (e.g. `char`, `String`, `Estudiante`). No generics.
- **Nodo API:** Always `obtenerValor()` / `obtenerNombre()`, `obtenerSiguiente()`, `asignarSiguiente(Nodo)`.
- **Method names:** `encolar`/`desencolar` for queues, `apilar`/`desapilar` for stacks, `insertarAlFinal`/`insertarAlInicio` for lists.
- **Visibility guard:** `estaVacia()` is always checked before `desencolar()` / `desapilar()`.
- **Input termination:** Loops that read elements from the user end when the input equals `"end"` (case-insensitive).
- **Javadoc:** Every public and private method has a Javadoc comment. No inline comments unless the logic is non-obvious.
- **Team header:** Every `Runner` class includes the team member list in the class-level Javadoc.

## README Convention

Each project README contains:
1. One-line description
2. Exercise explanation (what the program does, not how)
3. Mermaid class diagram (under `## Class Diagram`)
4. ASCII directory tree (under `## Structure`)
5. Compile and run commands (under `## How to Run`)

## IntelliJ IDEA Setup

> `.idea/` and `*.iml` are in `.gitignore`. These changes are local only and must be repeated on each machine.

When creating a new project, two files must be updated so IntelliJ recognizes the sources and shows a run configuration.

### 1. `java-oop-data-structures.iml` — register the source folder

Add a `<sourceFolder>` entry inside the `<content>` block:

```xml
<sourceFolder url="file://$MODULE_DIR$/<ProjectName>/src" isTestSource="false" />
```

### 2. `.idea/workspace.xml` — add the run configuration

Inside `<component name="RunManager">`, add a `<configuration>` block before `<list>`:

```xml
<configuration name="<package>.runner.Runner" type="Application" factoryName="Application">
  <option name="MAIN_CLASS_NAME" value="<package>.runner.Runner" />
  <module name="java-oop-data-structures" />
  <method v="2">
    <option name="Make" enabled="true" />
  </method>
</configuration>
```

And add an `<item>` inside `<list>`:

```xml
<item itemvalue="Application.<package>.runner.Runner" />
```

### 3. Reload the project in IntelliJ

`File → Reload All from Disk` (or close and reopen the project).

After reloading, the `src/` folder will show the blue source icon and the run configuration will appear in the run selector.
