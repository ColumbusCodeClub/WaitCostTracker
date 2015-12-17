To launch the app:
```language-bash
cd app
./gradlew run
```

To get things to work properly in Eclipse, enable script folder support under Groovy->Compiler preferences. Until you do this, Eclipse will compile your ratpack.groovy file, but ratpack needs the script, not a compiled class.
