To launch the app:
```language-bash
cd app
./gradlew run
```

# Eclipse

- Add the groovy-eclipse update site (http://dist.springsource.org/snapshot/GRECLIPSE/e4.5/) to "Available Software Sites" in Eclipse's Preferences, install the Groovy-Eclipse plugin as well as the 2.4 compiler. (I installed all of them.) 
- Enable script folder support under Groovy->Compiler preferences. Until you do this, Eclipse will compile your ratpack.groovy file, but ratpack needs the script, not a compiled class.
