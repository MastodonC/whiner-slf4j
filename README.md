# whiner-log4j

This app is a small toy app to show and test how logging will work on our kixi stack.
It's basically a small API that triggers nothing but logging (info, warn, error, exception, runtime, async).

The idea is to make sure that all of these logs get stored appropriately in the elk stack eventually.

## Prerequisites

You will need [Leiningen][] 2.0.0 or above installed.

[leiningen]: https://github.com/technomancy/leiningen

## Running

To start a web server for the application, run:

    lein ring server-headless


## Things to pay attention to when implementing own apps with slf4j

*This bit of code*:

```
    (Thread/setDefaultUncaughtExceptionHandler
     (reify Thread$UncaughtExceptionHandler
       (uncaughtException [_ thread ex]
         (log/error ex))))
```
from (https://stuartsierra.com/2015/05/27/clojure-uncaught-exceptions) needs to be triggered once at the start of the app.  It makes sure that all the errors on other threads (when using async for instance) are logged.

The wrap-catch-exception function needs to be present in one form or the other - a *try-catch block around the main function*. the aim is that ALL errors (including runtime) are caught and logged.  The default is that runtime exceptions go to stderr, which means they would never arrive on the main logstream, which would mean we would miss crucial information for failures!!!

The logback.xml is designed to stream errors to the console.  The line layout starts with a *logback timestamp format* (ISO8601, which effectively means yyyy-MM-dd HH:mm:ss,SSS) and is followed by the log level, then the information.
The timestamp at the start of the line is important since it's used to group multiline messages: the first line has a timestamp and all the following lines are grouped under that first line.
The *log level* is parsed by a logstash grok to be added to the error information.

Some of the lines in logback.xml are not necessary for all apps, it really depends on the library used: lines of type

```  <logger name="com" level="INFO" /> ```

Are merely designed to *reduce the noise to bearable levels*.  The default level to console is debug, but we don't need all the debug information of everything.

## License

Copyright © 2016 FIXME
