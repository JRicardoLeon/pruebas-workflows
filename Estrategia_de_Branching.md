Estrategia de Branching
Esta estrategia de branching define cómo gestionar el flujo de trabajo en el repositorio para garantizar un desarrollo organizado y eficiente.

Ramas Principales o de vida larga.
- main
  * Contiene la versión estable y lista para producción del código.
  * No se realizan commits directos en esta rama.
  * Solo se permite la fusión de cambios aprobados y probados desde la rama develop.
  * Protegida con reglas específicas.

- develop
  * Rama principal para el desarrollo activo.
  * Aquí se integran los cambios de todas las ramas de características (feature/*).
  * Esta rama debe estar funcional y compilable en todo momento.
  * Se fusiona en main cuando el desarrollo ya sé probo y válido en ambientes previos para poder salir a producción.

- Ramas Temporales o de vida corta.
  * feature/<nombre-del-feature>
  * Usada para desarrollar nuevas características o funcionalidades.
  * Basada en la rama develop.
  * Se fusiona de vuelta en develop mediante un Pull Request (PR) al completar la tarea.
  * Ejemplo: feature/agregar-autenticacion.

-hotfix/<nombre-del-hotfix>
  * Usada para corregir errores críticos en producción.
  * Basada en la rama main.
  * Se fusiona tanto en main como en develop después de solucionar el problema.
  * Ejemplo: hotfix/corregir-bug-login.
