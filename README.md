[example]: https://github.com/TozyMC/CommandHandler/wiki/Example "Example usages"

# CommandHandler

The simple way to use commands in your spigot plugin.

Supports Spigot version from 1.8 to later.

**Notes:** This is a library, not spigot plugin.

## Usages

1. Add library as maven or gradle repository.
2. Implement `Command` interface (Recommend implement `AbstractCommand` and sub class of it).
3. Create `CommandController` instance and register implemented command.
4. Register root command in `plugin.yml`.
5. Compile and use.

*See more at [Example][example] wiki page.*

## Dependencies

### Maven

```xml 
<dependency>
  <groupId>xyz.tozymc.spigot</groupId>
  <artifactId>commands-api</artifactId>
  <version>1.0</version>
</dependency>
```

### Gradle

```gradle
repositories {
    mavenCentral()
}

dependencies {
    compile 'xyz.tozymc.spigot.api:commands-api:1.0'
}
```

## References

- **JavaDocs:** *coming soon*
- **Spigot Resources:**: *coming soon*