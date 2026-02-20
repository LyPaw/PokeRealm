
```
                                            ,'\
              _.----.        ____         ,'  _\   ___    ___     ____
          _,-'       `.     |    |  /`.   \,-'    |   \  /   |   |    \  |`.
          \      __    \    '-.  | /   `.  ___    |    \/    |   '-.   \ |  |
           \.    \ \   |  __  |  |/    ,','_  `.  |          | __  |    \|  |
             \    \/   /,' _`.|      ,' / / / /   |          ,' _`.|     |  |
              \     ,-'/  /   \    ,'   | \/ / ,`.|         /  /   \  |     |
               \    \ |   \_/  |   `-.  \    `'  /|  |    ||   \_/  | |\    |
                \    \ \      /       `-.`.___,-' |  |\  /| \      /  | |   |
                 \    \ `.__,'|  |`-._    `|      |__| \/ |  `.__,'|  | |   |
                  \_.-'       |__|    `-._ |              '-.|     '-.| |   |
                                          `'                            '-._|
                         ."-,.__
                         `.     `.  ,
                      .--'  .._,'"-' `.
                     .    .'         `'
                     `.   /          ,'
                       `  '--.   ,-"'
                        `"`   |  \
                           -. \, |
                            `--Y.'      ___.
                                 \     L._, \
                       _.,        `.   <  <\                _
                     ,' '           `, `.   | \            ( `
                  ../, `.            `  |    .\`.           \ \_
                 ,' ,..  .           _.,'    ||\l            )  '".
                , ,'   \           ,'.-.`-._,'  |           .  _._`.
              ,' /      \ \        `' ' `--/   | \          / /   ..\
            .'  /        \ .         |\__ - _ ,'` `        / /     `.`.
            |  '          ..         `-...-"  |  `-'      / /        . `.
            | /           |L__           |    |          / /          `. `.
           , /            .   .          |    |         / /             ` `
          / /          ,. ,`._ `-_       |    |  _   ,-' /               ` \
         / .           \"`_/. `-_ \_,.  ,'    +-' `-'  _,        ..,-.    \`.
        .  '         .-f    ,'   `    '.       \__.---'     _   .'   '     \ \
        ' /          `.'    l     .' /          \..      ,_|/   `.  ,'`     L`
        |'      _.-""` `.    \ _,'  `            \ `.___`.'"`-.  , |   |    | \
        ||    ,'      `. `.   '       _,...._        `  |    `/ '  |   '     .|
        ||  ,'          `. ;.,.---' ,'       `.   `.. `-'  .-' /_ .'    ;_   ||
        || '              V      / /           `   | `   ,'   ,' '.    !  `. ||
        ||/            _,-------7 '              . |  `-'    l         /    `||
        . |          ,' .-   ,' ||               | .-.        `.      .'     ||
         `'        ,'    `".'    |               |    `.        '. -.'       `'
                  /      ,'      |               |,'    \-.._,.'/'
                  .     /        .               .       \    .''
                .`.    |         `.             /         :_,'.'
                  \ `...\   _     ,'-.        .'         /_.-'
                   `-.__ `,  `'   .  _.>----''.  _  __  /
                        .'        /"'          |  "'   '_
                       /_|.-'\ ,".             '.'`__'-( \
                         / ,"'"\,'               `/  `-.|" mh
```

<div align="center">


# 🎮 Proyecto Pokémon

*Un juego Pokémon desarrollado en Java, con transición a JavaFX*

[![License: CC BY-NC 4.0](https://img.shields.io/badge/License-CC%20BY--NC%204.0-lightgrey)](https://creativecommons.org/licenses/by-nc/4.0/)
![Java](https://img.shields.io/badge/Java-25-orange?logo=openjdk&logoColor=white)
![JavaFX](https://img.shields.io/badge/JavaFX-23-blue?logo=java&logoColor=white)
![Estado](https://img.shields.io/badge/Estado-En%20desarrollo-yellow)

</div>

---

## 📖 Descripción

**Proyecto Pokémon** es un juego por consola desarrollado en Java puro, actualmente en transición hacia una interfaz gráfica con **JavaFX**. El proyecto simula mecánicas clásicas de la saga Pokémon: elección de Pokémon inicial, sistema de ataques con PP, gestión del equipo del entrenador, inventario de ítems y captura de Pokémon salvajes.

El objetivo es recrear la experiencia de los juegos principales de Pokémon de forma progresiva, empezando por la selección de iniciales de las **Generaciones 1, 3 y 5**.

---

## 🏗️ Estructura del proyecto

```
PokemonProject/
├── src/
│   ├── MainApp.java           ← Punto de entrada JavaFX
│   ├── Pokémon.java           ← Modelo base de Pokémon
│   ├── Ataque.java            ← Sistema de ataques y PP
│   ├── Entrenador.java        ← Lógica del entrenador
│   ├── EquipoPokemon.java     ← Gestión del equipo (máx. 6)
│   ├── Inventario.java        ← Inventario de ítems
│   ├── Item.java              ← Modelo de ítem
│   ├── Pokeball.java          ← Lógica de captura
│   ├── CreadorPokemons.java   ← Inicialización de Pokémon por generación
│   ├── TipoPokemon.java       ← Enum de tipos
│   ├── TipoItem.java          ← Enum de tipos de ítem
│   └── Generacion.java        ← Enum de generaciones
```

---

## 👾 Pokémon disponibles

| Generación | Pokémon |
|---|---|
| Gen 1 | Bulbasaur 🌿 · Charmander 🔥 · Squirtle 💧 |
| Gen 3 | Treecko 🌿 · Torchic 🔥 · Mudkip 💧 |
| Gen 5 | Snivy 🌿 · Tepig 🔥 · Oshawott 💧 |

---

## 👥 Equipo

<div align="center">

| | Usuario | Rol |
|---|---|---|
| 🧑‍💻 | [LyPaw](https://github.com/LyPaw) | Dev |
| 🧑‍💻 | [CodeDiegoF](https://github.com/CodeDiegoF) | Dev |
| 🧑‍💻 | [EvoXgamer](https://github.com/EvoXgamer) | Dev |
| 🧑‍💻 | [FranJFM](https://github.com/FranJFM) | Dev |

</div>

---

## 📋 Roadmap

- [x] Modelo base de Pokémon, Ataque y Entrenador
- [x] Sistema de generaciones y selección de inicial
- [x] Sistema de PP en ataques
- [x] Inventario y captura de Pokémon
- [ ] Interfaz gráfica con JavaFX
- [ ] Pantalla de selección de Pokémon inicial
- [ ] Sistema de combate por turnos
- [ ] Mapa y navegación entre zonas

---

<div align="center">

*Proyecto sin fines comerciales · Pokémon es propiedad de Nintendo / Game Freak / Creatures Inc.*

[![License: CC BY-NC 4.0](https://img.shields.io/badge/License-CC%20BY--NC%204.0-lightgrey)](https://creativecommons.org/licenses/by-nc/4.0/)

</div>
