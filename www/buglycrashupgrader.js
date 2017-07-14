var exec = require("cordova/exec");
module.exports = {
	checkUpgrade: function(content){
		exec(
		function(message){
			console.log(message);
			alert(message);
		},
		function(errorMessage){
			console.log(errorMessage);
			alert(errorMessage);
		},
		"CrashUpgrader",
		"checkUpgrade",
		[content]);
	},
	getVersionName: function(content){
      		exec(
      		function(message){
      			console.log(message);
      			alert(message);
      		},
      		function(errorMessage){
      			console.log(errorMessage);
      			alert(errorMessage);
      		},
      		"CrashUpgrader",
      		"getVersionName",
      		[content]);
      	},
      	initBugly: function(content){
             exec(
             function(message){
              	console.log(message);
              	alert(message);
              },
              function(errorMessage){
              	console.log(errorMessage);
              	alert(errorMessage);

              },
              "CrashUpgrader",
              "initBugly",
              [content]);
        }
}

