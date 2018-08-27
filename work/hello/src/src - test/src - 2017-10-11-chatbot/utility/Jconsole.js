/*
* This class will be used in place of using console.log().
* We will manage the log with a flag. If the flag is disabled then no logs
* will be printed.
*/

let shouldPrintLogs = false;

function Jconsole() {};

Jconsole.log = function(logData) {
  if (shouldPrintLogs) console.log(logData);
};

export default Jconsole;
