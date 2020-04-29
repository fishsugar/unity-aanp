# Unity auto Android native plugin

Generate C# wrapper classes for Android plugins, make it easy to use Android SDKs directly in Unity without extra coding.

## Usage
```
java -jar unity-aanp-<version>.jar <inputJar> <outFile>
```

## Features
- [x] JAR input
- [ ] AAR input
- [x] Parse bytecode with [javassist](https://www.javassist.org/)
- [x] Code generation with [freemaker](https://freemarker.apache.org/)
- [x] Grammar translation
    - [x] Static members
    - [x] Instance members
    - [ ] Constructors
    - [ ] Enums
    - [ ] Interfaces
    - [ ] Static nested classes
    - [ ] Non-static nested classes
- [x] Type translation
    - [ ] Collection types
    - [ ] Known classes list
    - [ ] Types with extra conversion code required
    - [ ] Auto delegate generation
- [ ] Option to support multi thread

## Grammar Translation

Java|C#
---|---
package|namespace
class|class
static inner class|nested class
non-static inner class|nested class with extra parent argument in constructor
field variable|property
final field variable|readonly property
field method|field method

## Examples

Example project: [unity-aanp-test](https://github.com/fishsugar/unity-aanp-test)
