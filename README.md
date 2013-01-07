# LeaCompiler

Lea is a procedural, object-oriented language designed to meet the needs and requirements of novice coders.

### Compiling LeaCompiler

* LeaCompiler will automatically compile if it needs to, before compiling tests. It is possible to compile LeaCompiler by itself by running:

```
$ ant project
```

* We highly recommend you run a cleanup beforehand:

```
$ ant clean-code
```


### Compiling with LeaCompiler

* In order to ask LeaCompiler to compile the default set of tests run the following:

```
$ ant
```

* In order to compile your personnal tests run the following command:

```
$ ant -Dprog_name=<testName>
```


### Executing Tests

It is possible to use LeaCompiler to compile and run your own tests.
It is necessary to place the .lea file in the tests/input/ folder.

* To run the default set of tests (LeaCompiler will automatically compile the default tests):

```
$ ant run
```

* You can also ask LeaCompiler to run your programs after compiling them (LeaCompiler will automatically compile the tests):

```
$ ant run -Dprog_name=<testName>
```

* LeaCompiler will also run tests that have been previously compiled (ommit the -Dprog_name argument in order to run the default tests):

```
$ ant run-output -Dprog_name=<testName>
```


### Cleaning Code

You can clean the code of the LeaCompiler simply by running :

```
$ ant clean
```

You can also choose to clean a particular set of compiled tests :

```
$ ant clean-output -Dprog_name=<testName>
```

Or you can simply clean the entire project, including tests and compiler :

```
$ ant clean-all
```

### Notes

Please note that all test files must have a .lea extension, and whenever using the ``` -Dprog_name=<testName> ```option, ```<testName>``` should be the filename of the test (omitting the ".lea" suffix).

Also, due to the nature of the buildfile, whenever using ``` ant run-output ``` on a Lea file containing a call to *read()*, the instruction ``` System.console() ``` will return null, which will cause *read()* to be skipped.
If you want to execute the compiled output in this case, please run the following: 

```
$ ant run -Dprog_name=<testName>
$ java -jar tests/output/<testName>/lib/<testName>.jar
``` 

