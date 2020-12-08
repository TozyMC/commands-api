[originalLicense]: https://opensource.org/licenses/MIT "MIT License"

[license]: https://git.io/JTBhQ "MIT License"

[github]: https://git.io/JTBhc "GitHub project"

[release]: https://git.io/JTBho "GitHub Release"

[javadoc]: https://www.javadoc.io/doc/xyz.tozymc.spigot/commands-api/ "commands-api Javadoc"

[bintray]: https://bintray.com/tozymc/public/commands-api "Bintray"

[spigot]: https://www.spigotmc.org/resources/84992/ "Spigot Resources"

[release2.0]: https://github.com/TozyMC/commands-api/releases/tag/v2.0 "Release 2.0"

<div style="align-content: center">
  <h1>commands-api</h1>
  <p><i>Command libraries for spigot plugin</i></p>
  <a href="https://bit.ly/31lxtLJ"><img alt="Bintray" src="https://img.shields.io/bintray/v/tozymc/public/commands-api?style=flat-square&logo=jfrog-bintray"></a>
  <a href="https://www.javadoc.io/doc/xyz.tozymc.spigot/commands-api/"><img alt="Javadoc" src="https://img.shields.io/badge/javadoc-2.0-brightgreen.svg?style=flat-square"></a>
  <a href="https://git.io/JTBho"><img alt="GitHub release" src="https://img.shields.io/github/v/release/TozyMC/commands-api?style=flat-square"></a>
  <a href="https://git.io/JTRUf"><img alt="GitHub issues" src="https://img.shields.io/github/issues/TozyMC/commands-api?style=flat-square"></a>
  <a href="https://git.io/JTBhQ"><img alt="MIT License" src="https://img.shields.io/github/license/TozyMC/commands-api?style=flat-square"></a>
</div>
<br>

Command, useful method for interacting with players in Spigot plugin. I have realized that the
Spigot Command's Api is not functional enough for larger projects. So the *commands-api* was created
to replace it.

The *commands-api* is very easy to implement, deploy and use. It's compatible with all projects from
small to large and any version of Spigot. Working with the root-child model, the root command
equivalent to the spigot command and registered as `PluginCommand`. Reduce pass parameters for
simplicity, custom command results, and more.

***Notes:*** *This is a library,* ***not*** *a spigot plugin.*

## Table of Contents

- [Requirements](#requirements)
- [Installation](#installation)
  - [Maven](#maven)
  - [Gradle](#gradle)
  - [Manual](#manual)

- [How to use](#how-to-use)
- [Important Changes](#important-changes)
- [License](#license)
- [External Links](#external-links)

## Requirements

The source code used is *java 8* and built under `jdk8`. Any java version lower than *java 8* will
cause an error.

- **Java 8** or later

## Installation

There are many ways to install libraries to your plugin dependencies. You can follow this
instruction.

### Maven

Add this section inside `<dependencies>` tag in your `pom.xml`.

``` xml
<dependency>
  <groupId>xyz.tozymc.spigot</groupId>
  <artifactId>commands-api</artifactId>
  <version>2.0</version>
</dependency>
```

### Gradle

Follow this instruction if your build tool is Gradle.

```gradle
repositories {
    jcenter()
}

dependencies {
    compile 'xyz.tozymc.spigot.api:commands-api:2.0'
}
```

### Manual

If your project doesn't have any build tools, you can install it manually.

1. [Download][release] the library in github release.
2. Add `jar` file to your project.

## How to use

1. Implement command default abstract classes and design your command.

   ***Recommend:*** *Class implementation should be implemented from the `Command` interface,
   e.g, `AbstractCommand`,`CombinedCommand`,...*

2. Declare new `CommandController` instance and register implemented commands in the section 1 in
   the order to the controller declared.

   ***Notes:*** *The root command must be registered first before the child.*

3. Let's start server and enjoy.

## Important Changes

Since version [`2.0`][release2.0], you don't need to add command to `plugin.yml`. Why? The major
feature update in version [`2.0`][release2.0] is ***dynamic command registration***. What is it?
Simply, you can register commands anywhere and at any time. Unregistration is similar. So adding
command to `plugin.yml`
isn't necessary.

## License

Distributed under the [MIT License][originalLicense]. See [`LICENSE`][license] for more information.

## External Links

- Javadoc: [javadoc.io][javadoc]
- GitHub: [github.com][github]
- Bintray: [bintray.com][bintray]
- Spigot Resources: [spigotmc.org][spigot]