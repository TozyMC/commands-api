[originalLicense]: https://opensource.org/licenses/MIT "MIT License"

[license]: https://git.io/JTBhQ "MIT License"

[github]: https://git.io/JTBhc "GitHub project"

[release]: https://git.io/JTBho "GitHub Release"

[javadoc]: https://www.javadoc.io/doc/xyz.tozymc.spigot/commands-api/ "commands-api Javadoc"

[ossIndex]: https://ossindex.sonatype.org/component/pkg:maven/xyz.tozymc.spigot/commands-api "OSS Index"

[spigot]: https://www.spigotmc.org/resources/84992/ "Spigot Resources"

[release2.0]: https://github.com/TozyMC/commands-api/releases/tag/v2.0 "Release 2.0"

<div align="center">
  <h1>commands-api</h1>
  <p><i>Command libraries for spigot plugin</i></p>
  <a href="https://search.maven.org/artifact/xyz.tozymc.spigot/commands-api/2.2/jar"><img alt="Maven Central" src="https://img.shields.io/maven-central/v/xyz.tozymc.spigot/commands-api?label=Maven%20Central&logo=apache-maven&style=flat-square"></a>
  <a href="https://git.io/JTBho"><img alt="GitHub release" src="https://img.shields.io/github/v/release/TozyMC/commands-api?logo=github&style=flat-square"></a>
  <a href="https://www.javadoc.io/doc/xyz.tozymc.spigot/commands-api/"><img alt="Javadoc" src="https://javadoc.io/badge2/xyz.tozymc.spigot/commands-api/javadoc.svg?style=flat-square&label=Javadoc&color=brightgreen&logo=java"></a>
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

The source code used is *java 8*. Any java version lower than *java 8* will cause an error.

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
  <version>2.2</version>
</dependency>
```

### Gradle

Follow this instruction if your build tool is Gradle.

```gradle
dependencies {
    implementation 'xyz.tozymc.spigot:commands-api:2.2'
}
```

### Manual

If your project doesn't have any build tools, you can install it manually.

1. [Download][release] the library in github release.
2. Add `jar` file to your project.

## How to use

1. Implement command default abstract classes and design your command.

   ***Recommend:*** *Class implementation should be implemented from the implemented `Command`
   interface, e.g, `AbstractCommand`,`CombinedCommand`,...*

2. Declare new `CommandController` instance and register implemented commands in the section 1 in
   the order to the controller declared.

   ***Notes:*** *The root command must be registered first before the child.*

3. Let's start server and enjoy.

## Important Changes

Since version [`2.0`][release2.0], you don't need to add command to `plugin.yml`. Why? The major
feature update in version [`2.0`][release2.0] is ***dynamic command registration***. What is it?
Simply, you can register commands anywhere and at any time. Unregistration is similar. So adding
command to `plugin.yml` isn't necessary.

***Notes:*** Register commands in `plugin.yml` can cause
errors. ([#5](https://github.com/TozyMC/commands-api/issues/5#issuecomment-780289790))

## License

Distributed under the [MIT License][originalLicense]. See [`LICENSE`][license] for more information.

## External Links

- Javadoc: [javadoc.io][javadoc]
- GitHub: [github.com][github]
- Bintray: [ossindex.sonatype.org][ossIndex]
- Spigot Resources: [spigotmc.org][spigot]
