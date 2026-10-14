# Test Report

Entorno reportado para actividad: Ubuntu, JDK 21, Maven 3.8.7. Ejecución local: `mvn clean verify`, BUILD SUCCESS, 5 pruebas, 0 fallos/errores/omitidas (2026-10-14).

| Caso | Entrada | Esperado | Real | Resultado |
|---|---|---:|---:|---|
| T1 | x=1.1,dof=9 | 0.35006 | 0.3500586369 | Pasa (±0.0000051) |
| T2 | x=1.1812,dof=10 | 0.36757 | 0.3675734052 | Pasa (±0.0000051) |
| T3 | x=2.750,dof=30 | 0.49500 | 0.4949998582 | Pasa (±0.0000051) |

También se probaron x=0, refinamiento, parámetros inválidos y valores conocidos de Gamma.
