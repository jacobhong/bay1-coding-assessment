- Requires with java 11
- copy below command to build and run application, uses included test file. Add new file
  under the resources folder to test something else

```
 ./gradlew clean build fileNameArg -Pargs="file.txt"
```

- assume file is txt and in resources dir
- assume can just print out results to console
- assume date ok with including day
- assume need unit test

- some decisions I made was to google logic I didn't know off hand to save time for the BitmapUtil class
- I also refactored a few times as I saw I can improve some things even though I exceeded one hour