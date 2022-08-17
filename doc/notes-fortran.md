# Subroutines

- Subroutines können Funktionen und Subroutinen enthalten.

# Scope and Access

- Variablen in modulen können private, protected and public sein
  - private lese und schreibzugriff nur im container (module, sub routine)
  - protected lesen erlaubt schreiben verboten (wahrscheinlich) von außen
  - public alles erlaubt
- Datentypen können public sein aber ihre attribute sind private [p 113ff, 1].
- Datentypen können aus einem Modul importiert werden und dann umbenannt werden [p 122, 1].


# Variables

- Konstanten werden mit TYPE, parameter :: NAME = VALUE definiert [2]
- Allocated data is done with allocate, deallocate

# Interfaces

- Generic interfaces (p 248, 1]
interface name
   procedure a
   module procedure b
end interface name
- Can be used in modules
- Explicit interface [p 97, 1]

[1] https://doku.lrz.de/dyn/Doku_Kurse/Fortran/basics/Fortran_3days.pdf
[2] https://www.tutorialspoint.com/fortran/fortran_constants.htm
[3] https://fortran-lang.org/learn/quickstart/organising_code
[4] Fortran 77: https://docs.oracle.com/cd/E19957-01/805-4939/6j4m0vn7l/index.html
[5] Fortran 90/95: https://www.icl.utk.edu/~mgates3/docs/fortran.html
