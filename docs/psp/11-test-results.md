# Test Results

Comando ejecutado: `mvn clean verify`. Resultado Maven: **BUILD SUCCESS**, 5 tests, 0 failures, 0 errors, 0 skipped.

| Prueba | Observado | Estado |
|---|---:|---|
| T1 x=1.1,dof=9 | 0.3500586369 | Pasa |
| T2 x=1.1812,dof=10 | 0.3675734052 | Pasa |
| T3 x=2.750,dof=30 | 0.4949998582 | Pasa |
| x=0 | 0 | Pasa |
| Validaciones, convergencia y Gamma | Assertions JUnit | Pasa |

Los resultados anteriores provienen de ejecución local. Verificación remota posterior: workflow oficial `Java CI` pasó en `develop` (ejecución #1) y `master` (ejecución #2); el paso de carga de JaCoCo también terminó correctamente y dejó un artefacto. Se observó en la interfaz de GitHub el 14-10-2026. No se reporta aquí cobertura porcentual porque no se registró ese valor.
