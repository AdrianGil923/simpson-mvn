# Simpson MVN — distribución t de Student

## Problema y objetivo

El programa aproxima la integral de la densidad t de Student desde 0 hasta un límite superior `x`, para grados de libertad `dof`, usando Simpson compuesto 1/3. Representa la probabilidad acumulada entre 0 y `x`.

## Requisitos

JDK 21 y Maven 3.8 o posterior. JUnit Jupiter 5 se usa únicamente en pruebas.

## Arquitectura

- `App`: CLI que valida y presenta argumentos.
- `Logic`: coordina cálculo y formato de salida.
- `SimpsonIntegration`: suma ponderada de Simpson y refinamiento de segmentos.
- `GammaFunction`: aproximación Lanczos para los coeficientes de la densidad.

No se recibió PDF ni diagrama UML; se siguieron las responsabilidades indicadas en el encargo. `Logic` es una capa pequeña de coordinación.

## Método numérico

La densidad usada es `Gamma((dof+1)/2)/(sqrt(dof*pi)*Gamma(dof/2)) * (1+x²/dof)^(-(dof+1)/2)`. Gamma se calcula con la aproximación Lanczos de nueve coeficientes (g=7), evaluada en escala logarítmica al formar la constante de normalización.

Para `n` par, `h=x/n` y Simpson compuesto calcula `h/3 * (f(0)+f(x)+4Σf(x_i impares)+2Σf(x_i pares interiores))`. Se inicia en 10 segmentos, se duplica y se detiene cuando dos aproximaciones consecutivas difieren menos de `1e-5`. Se limita a 2^22 segmentos. `x` debe ser finito y no negativo; `dof` entero positivo.

## Compilar, ejecutar y probar

```sh
mvn clean verify
java -jar target/simpson-mvn-1.0-SNAPSHOT.jar 1.1 9
mvn test
```

Argumentos: `x dof`. Sin argumentos válidos, el programa informa el uso o error en stderr.

## Casos académicos y resultados locales

Obtenidos ejecutando el JAR tras `mvn clean verify`:

| x | dof | Esperado (5 decimales) | Resultado |
|---:|---:|---:|---:|
| 1.1 | 9 | 0.35006 | 0.3500586369 |
| 1.1812 | 10 | 0.36757 | 0.3675734052 |
| 2.750 | 30 | 0.49500 | 0.4949998582 |

Las pruebas automatizadas comparan con tolerancia `0.0000051`, acorde al redondeo a cinco decimales, además de entradas inválidas, convergencia, x=0 y Gamma.

## Estructura

```text
.github/workflows/ci-cd.yml
src/main/java/mx/edu/psp/{App,Logic,GammaFunction,SimpsonIntegration}.java
src/test/java/mx/edu/psp/SimpsonIntegrationTest.java
docs/psp/ (11 borradores PSP2)
```

## Integración continua

El workflow oficial `Java CI` se ejecuta en push hacia `master` o `develop` y en pull request hacia `master`. Configura Temurin Java 21, usa caché Maven y ejecuta `mvn --batch-mode verify`. JaCoCo genera `target/site/jacoco/`, que se carga como artefacto `jacoco-report`, incluso si falla un paso anterior. Se confirmó la ejecución remota con resultado satisfactorio en ambas ramas y se descargó el artefacto desde GitHub Actions.

## Flujo de ramas y GitHub

El repositorio GitHub es `AdrianGil923/simpson-mvn`. La implementación se encuentra en `master` y `develop`; ambas ramas ejecutaron correctamente `Java CI`. La rama `master` está protegida para exigir pull request, una aprobación, el check `ci-cd`, que la rama esté actualizada y resolver conversaciones; no permite saltarse las reglas.

```sh
git switch develop
git add .
git commit -m "Document verified GitHub workflow and branch protection"
git push origin develop
```

Para abrir el pull request, seleccione `master` como base y `develop` como comparación. Espere que `Java CI / ci-cd` termine en verde antes de fusionar. La protección está configurada desde **Settings → Branches** con patrón `master`.
