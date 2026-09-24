# NySkriptSA

NySkriptSA er et Skript addon plugin, der integrerer SuperAwesome API-data med Minecraft servers. Pluginnet henter og synkroniserer brugerdata for spillere og giver adgang til dette data gennem Skript expressions og events.

## Installation

Kører på Paper 1.21.10 med Java 21 og [Skript 2.14.1](https://hangar.papermc.io/SkriptLang/Skript).

Byg med `./gradlew build` (Windows: `gradlew.bat build`), og læg `build/libs/NySkriptSA-1.0.jar` i serverens `plugins`-mappe. Start serveren; pluginet opretter sin SQLite-database automatisk.

## Features

- Automatisk synkronisering af SuperAwesome brugerdata ved login
- Periodisk opdatering af data for online spillere (hvert minut)
- Skript expressions til at få brugeroplysninger
- Events når brugerdata ændrer sig
- Eventet `sa user loaded`, når spillerdata er hentet og gemt lokalt

## Skript Expressions

Alle disse expressions kan bruges på `offline player` objekter og henter data enten fra databasen (online spillere) eller API'en (offline spillere).

### Pointsystemer

```skript
%spiller's lobby points%        # Returnerer lobbyPoints som integer
%spiller's player points%       # Returnerer playerPoints som integer
```

**Eksempel:**
```skript
on join:
    send "Du har %player's lobby points% lobby points!"
    send "Du har %player's player points% player points!"
```

### VIP & Pro Status

```skript
%spiller's vip days%            # Returnerer antal VIP dage (integer)
%spiller's pro days%            # Returnerer antal Pro dage (integer)
```

**Eksempel:**
```skript
on join:
    if player's vip days > 0:
        send "Du har %player's vip days% dage VIP tilbage!"
```

### Roller & Discord

```skript
%spiller's role%                # Returnerer rolle som string (f.eks. "Admin", "Moderator")
%spiller's discordid%           # Returnerer Discord ID som string
```

**Eksempel:**
```skript
on join:
    set {_role} to player's role
    if {_role} is "Admin":
        send "Velkommen admin!" to player
```

### Badges & Ratings

```skript
%spiller's badges%              # Returnerer antal badges som integer
%spiller's rates%               # Returnerer antal ratings som integer
```

**Eksempel:**
```skript
if player's badges > 5:
    give player diamond
```

### Rating lister

```skript
%spiller's rates%               # Returnerer antal rate entries som integer
%spiller's rateliste%           # Returnerer usernames for alle rates som en liste
%spiller's plusrates%           # Returnerer antal positive rates som integer
%spiller's plusratesliste%      # Returnerer usernames for positive rates som en liste
%spiller's minusrates%          # Returnerer antal negative rates som integer
%spiller's minusratesliste%     # Returnerer usernames for negative rates som en liste
```

**Eksempel:**
```skript
command /rateinfo <offline player>:
    trigger:
        send "&eRates: &f%arg-1's rates%"
        send "&aPlus-rates: &f%arg-1's plusrates%"
        send "&cMinus-rates: &f%arg-1's minusrates%"

        loop arg-1's rateliste:
            send "&8- &f%loop-value%"

        loop arg-1's plusratesliste:
            send "&a+ &f%loop-value%"

        loop arg-1's minusratesliste:
            send "&c- &f%loop-value%"
```

## Skript Events

Events bliver trigget når brugerdata ændrer sig. Alle events har adgang til `event-player`, `old-user` og `new-user`.

### Pointer Events

```skript
on lobby points change:
    send "Dine lobby points ændrede fra %old-user's lobby points% til %new-user's lobby points%"

on player points change:
    send "Dine player points ændrede fra %old-user's player points% til %new-user's player points%"
```

### Status Events

```skript
on vip days change:
    send "Din VIP status ændrede!"

on pro days change:
    send "Din Pro status ændrede!"

on role change:
    send "Din rolle blev ændret fra %old-user's role% til %new-user's role%"
```

### Rating Events

```skript
on rate received:
    send "Du modtog en ny rating!"

on rate changed:
    send "En af dine ratings blev ændret!"
```

### Badge & Server Events

```skript
on badges change:
    send "Dine badges ændrede sig!"

on server boosts change:
    send "Server boosts ændrede sig!"

on servers change:
    send "Dine servere ændrede sig!"
```

`badges.sk` bruger `on sa user loaded:` sammen med spillerens `vip days`, `role` og `server booster` properties til at tildele badges, når data først hentes.

### User Update Event

```skript
on superawesome user update:
    send "Brugerdata blev opdateret!"
```

## Komplekse Eksempler

### Automatisk rang ved login

```skript
on join:
    set {_role} to player's role
    
    if {_role} is "Admin":
        add "admin" to player's groups
    else if {_role} is "Moderator":
        add "mod" to player's groups
    else if player's vip days > 0:
        add "vip" to player's groups
```

### Reward system

```skript
on player points change:
    set {_old.points} to old-user's player points
    set {_new.points} to new-user's player points
    
    if {_new.points} > {_old.points}:
        set {_diff} to {_new.points} - {_old.points}
        give event-player diamond * {diff}
        send "Du fik %{_diff}% diamanter for dine pointslove!"
```

### Notifikationer ved Pro/VIP opgradering

```skript
on vip days change:
    set {_old.vip} to old-user's vip days
    set {_new.vip} to new-user's vip days
    
    if {_new.vip} > {o_ld.vip}:
        broadcast "&6%event-player%&r blev VIP med %{_new.vip}% dage tilbage!"
        give event-player golden apple
```

### Log brugeraktivitet

```skript
on superawesome user update:
    set {_uuid} to event-player's uuid
    set {_name} to event-player's name
    
    log "[NySkriptSA] Updated user: %{_name}% (%{_uuid}%)"
```

## Data Struktur

### Lagrede Data pr. Spiller

```
- UUID
- Username
- Lobby Points
- Player Points
- VIP Days
- Pro Days
- Role
- Discord ID
- Rates (liste af ratings)
- Badges (liste af badges)
- Servers (liste af servere)
- Server Boosts (liste af server boosts)
```

## Tekniske Detaljer

- **Database**: SQLite (gemmes i `plugins/NySkriptSA/playerdata.db`)
- **API**: https://api.superawesome.dk/players/[username]
- **Opdateringsfrekvens**: Hvert minut for online spillere
- **Tidsout**: 5 sekunder for API-kald

## Fejlfinding

### Plugin starter ikke

Check:
- Er Skript installeret?
- Korrekt Java version (Java 8+)
- Plugin folder har skriveadgang
- Serveren bruger Java 21 eller nyere

### API-fejl

```
[NySkriptSA] Forbindelsesfejl til API: ...
```

Mulige årsager:
- Internetforbindelse
- API'en er nede
- Forkert brugernavn i forsøg

### Database fejl

```
[NySkriptSA] Kunne ikke gemme brugerdata: ...
```

Check plugin folder's skriveadgang og disk plads.

## Udvikler Info

- **Platform**: Paper 1.21.10, Java 21
- **Dependencies**: Paper API 1.21.10, Skript 2.14.1, SQLite JDBC
- **Package**: `net.nydt.nyskriptsa`
