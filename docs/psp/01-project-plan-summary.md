# PSP2 Project Plan Summary

**Producto:** integración t de Student por Simpson 1/3. **Estado:** ejecución local completada.

## Requisitos
CLI recibe x≥0 finito y dof entero positivo; aproxima integral 0..x; inicia n=10 par y refina duplicando hasta diferencia <1e-5, con techo; Gamma Lanczos; pruebas JUnit 5; documentación y CI Java 21.

## Diseño y plan
App → Logic → SimpsonIntegration; esta usa GammaFunction para la densidad. Fases previstas: planificación, diseño, implementación, revisión, pruebas, postmortem. UML/PDF del curso no estuvo disponible en los archivos inspeccionados.

## Estimaciones frente a mediciones
Tamaño estimado: pendiente; contar LOC por lenguaje/convención del curso. Tamaño real: pendiente de registrar con la herramienta/definición de PSP del curso. Tiempo por fase y esfuerzo: no medidos por fase; completar desde el registro personal. Sin datos históricos no se fabricó predicción PROBE.

## Criterios de salida
`mvn clean verify` verde; tres casos académicos dentro del redondeo; documentación y artefactos PSP presentes.
