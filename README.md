# Filbaserat Studenthanteringssystem

## Funktioner

### - Lägga till elever
### - Se alla elever
### - Välja ut specifik Elev efter ID
### - Spara elever till en Fil

## Arkitektur

### - Student
- Representerar en student i systemet.
  - Hanterar studentens egenskaper
### - StudentController
- Hanterar all logik för interagera med studenter och data, mellanhand mellan UserInterface och FileDirectory.
### - UserInterface
- Interfacet som användaren interagerar med.
### - FileDirectory
- Här sköts all filhantering.